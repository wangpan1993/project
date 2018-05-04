package com.wp.project.ui.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wp.project.R;
import com.wp.project.base.BaseActivity;
import com.wp.project.ui.fragment.FirstFragment;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends BaseActivity{

    private ArrayList<Fragment> fragments;
    private FragmentManager supportFragmentManager;
    private RadioGroup rg_tab;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        supportFragmentManager = getSupportFragmentManager();
        getPersimmions();
        rg_tab = findById(R.id.rg_tab);
        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.tab_home:
                        changeFragment(0);
                        break;
                    case R.id.tab_2:
                        changeFragment(1);
                        break;
                    case R.id.tab_3:
                        changeFragment(2);
                        break;
                    case R.id.tab_mine:
                        changeFragment(3);
                        break;
                }
            }
        });
    }

    @Override
    protected void initDatas() {
        fragments = new ArrayList<>();

        fragments.add(FirstFragment.putInstance("首页"));
        fragments.add(FirstFragment.putInstance("页面二"));
        fragments.add(FirstFragment.putInstance("页面三"));
        fragments.add(FirstFragment.putInstance("我的"));
        changeFragment(0);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                break;
        }


    }

    public void changeFragment(int index) {
        supportFragmentManager.beginTransaction().replace(R.id.frag, fragments.get(index)).commit();
    }


    private RadioButton getTab(String tabText, int resId) {
        RadioButton radioButton = new RadioButton(mContext);
        ViewGroup.LayoutParams layoutParams = radioButton.getLayoutParams();
        layoutParams.width = 0;
        layoutParams.height = -1;
        radioButton.setText(tabText);
        radioButton.setTextColor(getResources().getColorStateList(R.color.selector_tab_text_color));
        Drawable drawableTop = null;
        try {
            drawableTop = Drawable.createFromXml(getResources(), Resources.getSystem().getXml(resId));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        radioButton.setCompoundDrawables(null, drawableTop, null, null);
        return radioButton;
    }

}
