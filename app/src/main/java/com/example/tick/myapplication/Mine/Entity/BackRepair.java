package com.example.tick.myapplication.Mine.Entity;

import java.util.List;

/**
 * Created by Tick on 2016/9/27.
 */
public class BackRepair {

    /**
     * message : 修改成功!
     * code : 1
     */

    private SendCodeBean SendCode;
    /**
     * repair_project : 电脑爆炸
     * repair_decldatatime : null
     * repair_state : 0
     * repair_reply :
     * repair_id : 23
     * repair_starttime : null
     * repair_userid : 0
     * repair_completetime : null
     * repair_endtime : null
     */

    private List<RepairBean> Repair;

    public SendCodeBean getSendCode() {
        return SendCode;
    }

    public void setSendCode(SendCodeBean SendCode) {
        this.SendCode = SendCode;
    }

    public List<RepairBean> getRepair() {
        return Repair;
    }

    public void setRepair(List<RepairBean> Repair) {
        this.Repair = Repair;
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

    public static class RepairBean {
        private String repair_project;
        private Object repair_decldatatime;
        private int repair_state;
        private String repair_reply;
        private int repair_id;
        private Object repair_starttime;
        private int repair_userid;
        private Object repair_completetime;
        private Object repair_endtime;

        public String getRepair_project() {
            return repair_project;
        }

        public void setRepair_project(String repair_project) {
            this.repair_project = repair_project;
        }

        public Object getRepair_decldatatime() {
            return repair_decldatatime;
        }

        public void setRepair_decldatatime(Object repair_decldatatime) {
            this.repair_decldatatime = repair_decldatatime;
        }

        public int getRepair_state() {
            return repair_state;
        }

        public void setRepair_state(int repair_state) {
            this.repair_state = repair_state;
        }

        public String getRepair_reply() {
            return repair_reply;
        }

        public void setRepair_reply(String repair_reply) {
            this.repair_reply = repair_reply;
        }

        public int getRepair_id() {
            return repair_id;
        }

        public void setRepair_id(int repair_id) {
            this.repair_id = repair_id;
        }

        public Object getRepair_starttime() {
            return repair_starttime;
        }

        public void setRepair_starttime(Object repair_starttime) {
            this.repair_starttime = repair_starttime;
        }

        public int getRepair_userid() {
            return repair_userid;
        }

        public void setRepair_userid(int repair_userid) {
            this.repair_userid = repair_userid;
        }

        public Object getRepair_completetime() {
            return repair_completetime;
        }

        public void setRepair_completetime(Object repair_completetime) {
            this.repair_completetime = repair_completetime;
        }

        public Object getRepair_endtime() {
            return repair_endtime;
        }

        public void setRepair_endtime(Object repair_endtime) {
            this.repair_endtime = repair_endtime;
        }
    }
}
