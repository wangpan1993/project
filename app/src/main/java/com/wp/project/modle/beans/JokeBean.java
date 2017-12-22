package com.wp.project.modle.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangPan on 2017/6/13 13:48
 */

public class JokeBean implements Serializable {
    /**
     * data : [{"content":"大学有个女同学，女神级别，有一富二代追她，经常假装无意中露出劳力士金表，一身名牌的在她面前晃悠。然而女神依然和一男屌丝深爱，直至结婚。我们一群人都暗中佩服，还是有不拜金的好女人啊。一次同学会，趁她喝高问她当年到底有没有对富二代动心过，她沉默半天才说，当年没见识，那些名牌都不认得，唯独一次觉得他的宾利牛逼，她的屌丝男友还骗她说是比亚迪豪华版\u2026\u2026","hashId":"867AFF72121B555B298D8422263E6DAD","unixtime":1494385240,"updatetime":"2017-05-10 11:00:40"}]
     */

    private List<DataBean> data;

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean {
        /**
         * content : 大学有个女同学，女神级别，有一富二代追她，经常假装无意中露出劳力士金表，一身名牌的在她面前晃悠。然而女神依然和一男屌丝深爱，直至结婚。我们一群人都暗中佩服，还是有不拜金的好女人啊。一次同学会，趁她喝高问她当年到底有没有对富二代动心过，她沉默半天才说，当年没见识，那些名牌都不认得，唯独一次觉得他的宾利牛逼，她的屌丝男友还骗她说是比亚迪豪华版……
         * hashId : 867AFF72121B555B298D8422263E6DAD
         * unixtime : 1494385240
         * updatetime : 2017-05-10 11:00:40
         */

        private String content;
        private String hashId;
        private int unixtime;
        private String updatetime;

        public void setContent(String content) {
            this.content = content;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public void setUnixtime(int unixtime) {
            this.unixtime = unixtime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getContent() {
            return content;
        }

        public String getHashId() {
            return hashId;
        }

        public int getUnixtime() {
            return unixtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }
    }

}