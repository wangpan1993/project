package com.wp.project.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.wp.project.application.MyApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {
	/**
	 * 根据图片路径进行压缩图片
	 * 
	 * @return
	 */
	public static void saveBitmap(String imagePath, String bitName,
                                  String savePath, int size) {
		Bitmap bitmap;
		Double fileSize = FileUtils.getFileSize(new File(imagePath));
		if (fileSize < 0.4) {
			bitmap = BitmapFactory.decodeFile(imagePath);
		} else {

			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			// 开始读入图片，此时把options.inJustDecodeBounds 设回true了,表示只返回宽高
			newOpts.inPreferredConfig = Config.RGB_565;
			newOpts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(imagePath, newOpts);// 此时返回bm为空

			// 当前图片宽高
			float w = newOpts.outWidth;
			float h = newOpts.outHeight;
			float hh = 1280f;
			float ww = 720f;
			// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
			int be = 1;// be=1表示不缩放
			if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
				be = (int) (newOpts.outWidth / ww);
				// 有时会出现be=3.2或5.2现象，如果不做处理压缩还会失败
				if ((newOpts.outWidth / ww) > be) {

					be += 1;
				}
				// be = Math.round((float) newOpts.outWidth / (float) ww);
			} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
				be = (int) (newOpts.outHeight / hh);
				if ((newOpts.outHeight / hh) > be) {

					be += 1;
				}
			}
			if (be <= 0) {

				be = 1;
			}
			newOpts.inSampleSize = be;// 设置缩放比例
			// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
			newOpts.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(imagePath, newOpts);
			int degree = readPictureDegree(imagePath);
			if (bitmap != null && degree > 0) {
				bitmap = rotaingImageView(degree, bitmap);
			}
		}
		compressImage(bitmap, bitName, savePath, size);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * 压缩图片
	 * 
	 * @param image
	 * @param size
	 * @return
	 */
	private static void compressImage(Bitmap image, String bitName,
                                      String savePath, int size) {
		try {
			if (image != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
				int options = 100;

				while ((baos.toByteArray().length / 1024) > size) { // 循环判断如果压缩后图片是否大于等于size,大于等于继续压缩
					baos.reset();// 重置baos即清空baos
					options -= 5;// 每次都减少5
					image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
				}

				File foder = new File(savePath);
				if (!foder.exists()) {
					foder.mkdirs();
				}

				File myCaptureFile = new File(savePath, bitName);
				if (!myCaptureFile.exists()) {
					myCaptureFile.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(myCaptureFile);
				fos.write(baos.toByteArray());
				fos.flush();
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 旋转图片
	 */
	public static int readPictureDegree(String imagePath) {
		int imageDegree = 0;
		if (!TextUtils.isEmpty(imagePath)) {

			try {
				ExifInterface exifInterface = new ExifInterface(imagePath);
				int orientation = exifInterface.getAttributeInt(
						ExifInterface.TAG_ORIENTATION,
						ExifInterface.ORIENTATION_NORMAL);
				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					imageDegree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					imageDegree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					imageDegree = 270;
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imageDegree;
	}

	// 然后将图片旋转回去
	public static Bitmap rotaingImageView(int angle, Bitmap mBitmap) {
		if (angle > 0 && mBitmap != null) {
			Matrix matrix = new Matrix();
			matrix.setRotate(angle);
			Bitmap rotateBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
					mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
			if (rotateBitmap != null) {
				mBitmap.recycle();
				mBitmap = rotateBitmap;
			}
		}
		return mBitmap;
	}
	
	/**
	 * 更新系统相册
	 * @param file
	 */
	public static void refreshAlbum(File file) {
		// 其次把文件插入到系统图库
	    try {
	        MediaStore.Images.Media.insertImage(MyApplication.getInstance().getContentResolver(),
					file.getAbsolutePath(), file.getName(), null);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    // 最后通知图库更新
		MyApplication.getInstance().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
	}

	/**
	 * bitmap转圆角
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCornerImage(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		// 抗锯齿
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
}
