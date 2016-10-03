package com.example.tick.myapplication.Home.Entity;

/**
 * Created by Tick on 2016/9/13.
 */
public class RepairEntity {
    String repair_starttime;//格式yyyy-MM-dd hh-mm-ss开始时间
    String repair_endtime;//结束时间
    String repair_project;//维修的内容
    int repair_userid;//用户id
    int repair_id;//自助报修ID
    String repair_completetime;//完成时间
    String repair_decldatatime;//保修时间

    public String getRepair_completetime() {
        return repair_completetime;
    }

    public void setRepair_completetime(String repair_completetime) {
        this.repair_completetime = repair_completetime;
    }

    public String getRepair_decldatatime() {
        return repair_decldatatime;
    }

    public void setRepair_decldatatime(String repair_decldatatime) {
        this.repair_decldatatime = repair_decldatatime;
    }

    public String getRepair_starttime() {
        return repair_starttime;
    }

    public void setRepair_starttime(String repair_starttime) {
        this.repair_starttime = repair_starttime;
    }

    public String getRepair_endtime() {
        return repair_endtime;
    }

    public void setRepair_endtime(String repair_endtime) {
        this.repair_endtime = repair_endtime;
    }

    public String getRepair_project() {
        return repair_project;
    }

    public void setRepair_project(String repair_project) {
        this.repair_project = repair_project;
    }

    public int getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(int repair_id) {
        this.repair_id = repair_id;
    }

    public int getRepair_userid() {

        return repair_userid;
    }

    public void setRepair_userid(int repair_userid) {
        this.repair_userid = repair_userid;
    }
}
