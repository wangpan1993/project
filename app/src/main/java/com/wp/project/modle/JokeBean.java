package com.wp.project.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangPan on 2017/6/13 13:48
 */

public class JokeBean implements Serializable {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * content : 一天他爹娘们都在，他问他们他是哪来的，他爹挠了挠头说石头缝里蹦出来的，他娘撇了撇嘴说山沟里捡来的，口径不一致啊，他俩对视了一眼 然后他娘拿起个桃子蹭了蹭咬了一口说先从石头缝里蹦出来然后掉到了山沟里然后捡来回来的，小伙伴们说他父母说的是真话吗？
         * hashId : 2ee3e60e3a12af4ae7522ae338dcb91d
         * unixtime : 1519617300
         * updatetime : 2018-02-26 11:55:00
         */

        private String content;
        private String hashId;
        private int unixtime;
        private String updatetime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHashId() {
            return hashId;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public int getUnixtime() {
            return unixtime;
        }

        public void setUnixtime(int unixtime) {
            this.unixtime = unixtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "content='" + content + '\'' +
                    ", hashId='" + hashId + '\'' +
                    ", unixtime=" + unixtime +
                    ", updatetime='" + updatetime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JokeBean{" +
                "data=" + data +
                '}';
    }
}