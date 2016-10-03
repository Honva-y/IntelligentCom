package com.example.tick.myapplication.User.Entity;

/**
 * Created by Tick on 2016/9/18.
 */
public class UserEntity {

    /**
     * user_landstate : 0
     * user_card : 510105199010017999
     * user_checkstate : 0
     * user_type : 1
     * user_registertime : {"nanos":0,"time":1472717217000,"minutes":6,"seconds":57,"hours":16,"month":8,"year":116,"timezoneOffset":-480,"day":4,"date":1}
     * user_realname : 叶丰华
     * user_nickname : 丰小华
     * user_email : 334949356@qqcom
     * user_age : 23
     * user_approvaltime : null
     * user_account : 13255678822
     * user_community : 1
     * user_approver :
     * user_password : 123456
     * user_head : /upload/201324133242.png
     * user_id : 1
     * user_sex : 男
     * user_house : 1
     */

    private int user_landstate;
    private String user_card;
    private int user_checkstate;
    private int user_type;
    /**
     * nanos : 0
     * time : 1472717217000
     * minutes : 6
     * seconds : 57
     * hours : 16
     * month : 8
     * year : 116
     * timezoneOffset : -480
     * day : 4
     * date : 1
     */

    private UserRegistertimeBean user_registertime;
    private String user_realname;
    private String user_nickname;
    private String user_email;
    private int user_age;
    private Object user_approvaltime;
    private String user_account;
    private int user_community;
    private String user_approver;
    private String user_password;
    private String user_head;
    private int user_id;
    private String user_sex;
    private int user_house;

    public int getUser_landstate() {
        return user_landstate;
    }

    public void setUser_landstate(int user_landstate) {
        this.user_landstate = user_landstate;
    }

    public String getUser_card() {
        return user_card;
    }

    public void setUser_card(String user_card) {
        this.user_card = user_card;
    }

    public int getUser_checkstate() {
        return user_checkstate;
    }

    public void setUser_checkstate(int user_checkstate) {
        this.user_checkstate = user_checkstate;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public UserRegistertimeBean getUser_registertime() {
        return user_registertime;
    }

    public void setUser_registertime(UserRegistertimeBean user_registertime) {
        this.user_registertime = user_registertime;
    }

    public String getUser_realname() {
        return user_realname;
    }

    public void setUser_realname(String user_realname) {
        this.user_realname = user_realname;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public Object getUser_approvaltime() {
        return user_approvaltime;
    }

    public void setUser_approvaltime(Object user_approvaltime) {
        this.user_approvaltime = user_approvaltime;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public int getUser_community() {
        return user_community;
    }

    public void setUser_community(int user_community) {
        this.user_community = user_community;
    }

    public String getUser_approver() {
        return user_approver;
    }

    public void setUser_approver(String user_approver) {
        this.user_approver = user_approver;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
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

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_house() {
        return user_house;
    }

    public void setUser_house(int user_house) {
        this.user_house = user_house;
    }

    public static class UserRegistertimeBean {
        private int nanos;
        private long time;
        private int minutes;
        private int seconds;
        private int hours;
        private int month;
        private int year;
        private int timezoneOffset;
        private int day;
        private int date;

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }
    }
}
