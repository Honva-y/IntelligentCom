package com.example.tick.myapplication.Mine.Entity;

import java.util.List;

/**
 * Created by Tick on 2016/9/27.
 */
public class BackSuggest {

    /**
     * message : 修改成功!
     * code : 1
     */

    private SendCodeBean SendCode;
    /**
     * complains_title :
     * complains_replytime : null
     * complains_phone :
     * complains_id : 0
     * complains_usertype : 0
     * complains_content :
     * complains_state : 0
     * complains_datetime : null
     * complains_replycontent :
     * complains_userid : 0
     */

    private List<ComplainsBean> Complains;

    public SendCodeBean getSendCode() {
        return SendCode;
    }

    public void setSendCode(SendCodeBean SendCode) {
        this.SendCode = SendCode;
    }

    public List<ComplainsBean> getComplains() {
        return Complains;
    }

    public void setComplains(List<ComplainsBean> Complains) {
        this.Complains = Complains;
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

    public static class ComplainsBean {
        private String complains_title;
        private Object complains_replytime;
        private String complains_phone;
        private int complains_id;
        private int complains_usertype;
        private String complains_content;
        private int complains_state;
        private Object complains_datetime;
        private String complains_replycontent;
        private int complains_userid;

        public String getComplains_title() {
            return complains_title;
        }

        public void setComplains_title(String complains_title) {
            this.complains_title = complains_title;
        }

        public Object getComplains_replytime() {
            return complains_replytime;
        }

        public void setComplains_replytime(Object complains_replytime) {
            this.complains_replytime = complains_replytime;
        }

        public String getComplains_phone() {
            return complains_phone;
        }

        public void setComplains_phone(String complains_phone) {
            this.complains_phone = complains_phone;
        }

        public int getComplains_id() {
            return complains_id;
        }

        public void setComplains_id(int complains_id) {
            this.complains_id = complains_id;
        }

        public int getComplains_usertype() {
            return complains_usertype;
        }

        public void setComplains_usertype(int complains_usertype) {
            this.complains_usertype = complains_usertype;
        }

        public String getComplains_content() {
            return complains_content;
        }

        public void setComplains_content(String complains_content) {
            this.complains_content = complains_content;
        }

        public int getComplains_state() {
            return complains_state;
        }

        public void setComplains_state(int complains_state) {
            this.complains_state = complains_state;
        }

        public Object getComplains_datetime() {
            return complains_datetime;
        }

        public void setComplains_datetime(Object complains_datetime) {
            this.complains_datetime = complains_datetime;
        }

        public String getComplains_replycontent() {
            return complains_replycontent;
        }

        public void setComplains_replycontent(String complains_replycontent) {
            this.complains_replycontent = complains_replycontent;
        }

        public int getComplains_userid() {
            return complains_userid;
        }

        public void setComplains_userid(int complains_userid) {
            this.complains_userid = complains_userid;
        }
    }
}
