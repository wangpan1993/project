package com.wp.project.ui.interfaces;

import com.wp.project.base.BaseView;
import com.wp.project.modle.JokeBean;

/**
 * Created by WangPan on 2018/3/12.
 */

public interface TestIView extends BaseView{
    void onShowJoke(JokeBean jokeBean);
}
