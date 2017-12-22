package com.wp.project.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {
	public static final int SIZETYPE_B = 1;// 获取文件大小单位为B的double值
	public static final int SIZETYPE_KB = 2;// 获取文件大小单位为KB的double值
	public static final int SIZETYPE_MB = 3;// 获取文件大小单位为MB的double值
	public static final int SIZETYPE_GB = 4;// 获取文件大小单位为GB的double值
	public static final String WEBVIEW_CACHE = "webcache";
	
//	UnzipListenner mListener;
	public interface UnzipListenner {
		public void unzipCompleted();
		public void unzipFailed();
	}
	

	/**
	 * 将assets资产目录下的文件拷贝到系统目录下
	 */
	public static void copyAssetsFile(final Context context,
                                      final String fileFullName, String saveDir) {
		final File file = new File(saveDir + File.separator +fileFullName);
		if (file.exists()) {// 文件存在了就不需要拷贝了
			return;
		}
		new Thread() {
			public void run() {
				try {
					// 获取资产目录管理器
					AssetManager assetManager = context.getAssets();
					InputStream is = assetManager.open(fileFullName);// 输入流
					FileOutputStream fos = new FileOutputStream(file);// 输出流
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}


	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}

	}
	
	/**
	 * 解压缩
	 * @param unzipto 解压到
	 * @param zipFilePath 要解压的压缩文件的路径
	 */
	public static void unzipFile(final String unzipto, final String zipFilePath, final UnzipListenner mListener) {
//		new Thread() {
//			public void run() {
			      try {  
			          File zipFile = new File(zipFilePath);
			          InputStream is = new FileInputStream(zipFile);
			          ZipInputStream zis = new ZipInputStream(is);
			          ZipEntry entry = null;
			          System.out.println("开始解压:" + zipFile.getName() + "...");
			          while ((entry = zis.getNextEntry()) != null) {  
			              String zipPath = entry.getName();
			              try {  
			  
			                  if (entry.isDirectory()) {  
			                      File zipFolder = new File(unzipto + File.separator
			                              + zipPath);  
			                      if (!zipFolder.exists()) {  
			                          zipFolder.mkdirs();  
			                      }  
			                  } else {  
			                      File file = new File(unzipto + File.separator
			                              + zipPath);  
			                      if (!file.exists()) {  
			                          File pathDir = file.getParentFile();
			                          pathDir.mkdirs();  
			                          file.createNewFile();  
			                      }  
			  
			                      FileOutputStream fos = new FileOutputStream(file);
			                      int bread;  
			                      while ((bread = zis.read()) != -1) {  
			                          fos.write(bread);  
			                      }  
			                      fos.close();  
			  
			                  }
			                  System.out.println("成功解压:" + zipPath);
			              } catch (Exception e) {
			            	  if(mListener != null)
			            		  mListener.unzipFailed();
			                  System.out.println("解压" + zipPath + "失败");
			                  continue;  
			              }  
			          }  
			          zis.close();  
			          is.close();  
			          if(mListener != null)
	                	  mListener.unzipCompleted();
			          System.out.println("解压结束");
			      } catch (Exception e) {
			    	  if(mListener != null)
	            		  mListener.unzipFailed();
			          e.printStackTrace();  
			      } 
//			}
//		}.start();
	  
	  }  
	
	
	/**
	 * 删除文件夹里的全部文件
	 * 
	 * @param file
	 */
	public static void delete(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}

		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}

			for (int i = 0; i < childFiles.length; i++) {
				delete(childFiles[i]);
			}
			file.delete();
		}
	}

//	/**
//	 * 获取文件夹的大小
//	 * 
//	 * @param f
//	 * @return
//	 * @throws Exception
//	 */
//	public static long getFileSize(File f) throws Exception {
//		long size = 0;
//		File flist[] = f.listFiles();
//		for (int i = 0; i < flist.length; i++) {
//			size = size + flist[i].length();
//		}
//		return size;
//	}
	
	/**
	 * fileSize 
	 * 这个方法获取的大小的单位是MB
	 * @param f
	 * @return
	 */
	public static Double getFileSize(File f) {
		double size = 0;
		try {
			if (f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				size = ((double) fis.available() / 1048576);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return size;
	}

	// 删除指定文件，比如临时文
	public static void removeFile(String filename) {
		if (new File(filename).exists())
			new File(filename).delete();
	}
	
	/**
	 * 判断文件及目录是否存在，若不存在则创建文件及目录
	 * 
	 * @param filepath
	 * @return
	 * @throws Exception
	 */
	public static File checkExist(String filepath) {
		File file = new File(filepath);

		if (file.exists()) {// 判断文件目录的存在
			// 文件夹存在
			if (file.isDirectory()) {// 判断文件的存在性
				// 文件存在
			} else {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}// 创建文件
					// 文件不存在，创建文件成功
			}
		} else {
			// 文件夹不存在
			File file2 = new File(file.getParent());
			file2.mkdirs();
			// 创建文件夹成功
			if (file.isDirectory()) {
				// 文件存在
			} else {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}// 创建文件
					// 文件不存在，创建文件成功
			}
		}
		return file;
	}
	
	public static void mkDirNotExist(String dirPath) {
		File file =new File(dirPath);
		//如果文件夹不存在则创建    
		if (!file.exists())      
//			if (!file.exists() && !file.isDirectory())      
		{       
		    file.mkdir();    
		} 
	}

	/**
	 * 文件夹是否存在
	 * 
	 * @param path
	 *            文件夹路径
	 */
	public static boolean isExist(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		return true;
	}
	
	/* 判断SD卡是否存在 返回true表示存在 */
	public static boolean avaiableSDcard() {
	  String status = Environment.getExternalStorageState();
	  if (status.equals(Environment.MEDIA_MOUNTED)) {
	   return true;
	  } else {
	   return false;
	  }
	}
	
	
	
	
	/**
	 * 调用此方法自动计算指定文件或指定文件夹的大小
	 * 
	 * @param file
	 *            文件路径
	 * @return 计算好的带B、KB、MB、GB的字符串
	 */
	public static double getAutoFileOrFilesSize(File file, int sizeType) {
		long blockSize = 0;
		try {
			if (file.isDirectory()) {
				blockSize = getFileSizes(file);
			} else {
				blockSize = getFileSizes_long(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormetFileSize(blockSize, sizeType);
	}
	
	/**
	 * 转换文件大小,指定转换的类型
	 * 
	 * @param fileS
	 * @param sizeType
	 * @return
	 */
	private static double FormetFileSize(long fileS, int sizeType) {
		DecimalFormat df = new DecimalFormat("#.00");
		double fileSizeLong = 0;
		switch (sizeType) {
		case SIZETYPE_B:
			fileSizeLong = Double.valueOf(df.format((double) fileS));
			break;
		case SIZETYPE_KB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
			break;
		case SIZETYPE_MB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
			break;
		case SIZETYPE_GB:
			fileSizeLong = Double.valueOf(df
					.format((double) fileS / 1073741824));
			break;
		default:
			break;
		}
		return fileSizeLong;
	}
	
	/**
	 * 获取指定文件夹
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static long getFileSizes(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSizes(flist[i]);
			} else {
				size = size + getFileSizes_long(flist[i]);
			}
		}
		return size;
	}

	/**
	 * 获取指定文件大小
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static long getFileSizes_long(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
			file.createNewFile();
		}
		return size;
	}
	
	/**
	 * 日志存储目录
	 */
	public static String savePath() {
		File sdRoot;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			sdRoot = Environment.getExternalStorageDirectory();
		} else {
			sdRoot = Environment.getDataDirectory();
		}
		return sdRoot.getAbsolutePath();
	}
	
	/**
	 * 保存错误信息到文件中 
	 * @param str 要保存的信息
	 * @param fileName 保存到本地的文件名
	 * @param append 是否追加
	 */
	public static void saveStrInfo2File(String str, String fileName, boolean append) {
		fileName = fileName + ".txt";
		StringBuilder sb = new StringBuilder("\n\n\n"+str);
		try {
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				
				String path = "/sdcard/业主日志/";
				File dir = new File(path);
//				if(dir.exists())
//					return;
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(path + fileName, append);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
			return;
		} catch (Exception e) {
			Log.e("FileUtils", "an error occured while writing file...", e);
		}
		return;
	}
}
