package com.example.tick.myapplication.User.Entity;

/**
 * Created by Tick on 2016/10/1.
 */
public class MessageCode {

    /**
     * message : 获取短信成功!
     * code : 1
     */

    private SendCodeBean SendCode;
    /**
     * SendCode : {"message":"获取短信成功!","code":1}
     * Number : 877068
     */

    private String Number;

    public SendCodeBean getSendCode() {
        return SendCode;
    }

    public void setSendCode(SendCodeBean SendCode) {
        this.SendCode = SendCode;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
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
}
