package com.example.tick.myapplication.Topic.Entity;

import java.util.List;

/**
 * Created by Tick on 2016/9/29.
 */
public class ZanList {

    /**
     * message : 点赞成功!
     * code : 1
     */

    private SendCodeBean SendCode;
    /**
     * TopicList : [{"user_nickname":"jian","user_head":"/upload/201324133252.png","user_id":2},{"user_nickname":"鹏小蔡","user_head":"/upload/201324133220.png","user_id":6},{"user_nickname":"lin","user_head":"/upload/201324133223.png","user_id":5},{"user_nickname":"w_w","user_head":"/upload/201324133211.png","user_id":3},{"user_nickname":"丰小华","user_head":"/upload/201324133242.png","user_id":1}]
     * SendCode : {"message":"点赞成功!","code":1}
     * topic_id : 1
     */

    private int topic_id;
    /**
     * user_nickname : jian
     * user_head : /upload/201324133252.png
     * user_id : 2
     */

    private List<TopicListBean> TopicList;

    public SendCodeBean getSendCode() {
        return SendCode;
    }

    public void setSendCode(SendCodeBean SendCode) {
        this.SendCode = SendCode;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public List<TopicListBean> getTopicList() {
        return TopicList;
    }

    public void setTopicList(List<TopicListBean> TopicList) {
        this.TopicList = TopicList;
    }

    public static class SendCodeBean {
        private String message;
        private int code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static class TopicListBean {
        private String user_nickname;
        private String user_head;
        private int user_id;

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getUser_head() {
            return user_head;
        }

        public void setUser_head(String user_head) {
            this.user_head = user_head;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
