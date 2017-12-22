package com.wp.project.ui.iview;


import com.wp.project.base.BaseView;
import com.wp.project.modle.beans.JokeBean;

/**
 * Created by 王攀 on 2017/2/22.
 */

public interface TestIView extends BaseView {
    void onShowJoke(JokeBean jokeBean);

}
