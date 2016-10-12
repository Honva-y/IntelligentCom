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
     * repair_project : 电脑 爆炸
     * repair_decldatatime : {"nanos":0,"time":1359475200000,"minutes":0,"seconds":0,"hours":0,"month":0,"timezoneOffset":-480,"year":113,"day":3,"date":30}
     * repair_state : 0
     * repair_reply :
     * repair_id : 3
     * repair_starttime : {"nanos":0,"time":1357056000000,"minutes":0,"seconds":0,"hours":0,"month":0,"timezoneOffset":-480,"year":113,"day":3,"date":2}
     * repair_userid : 1
     * repair_completetime : {"nanos":0,"time":1359475200000,"minutes":0,"seconds":0,"hours":0,"month":0,"timezoneOffset":-480,"year":113,"day":3,"date":30}
     * repair_endtime : {"nanos":0,"time":1359043200000,"minutes":0,"seconds":0,"hours":0,"month":0,"timezoneOffset":-480,"year":113,"day":5,"date":25}
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
        /**
         * nanos : 0
         * time : 1359475200000
         * minutes : 0
         * seconds : 0
         * hours : 0
         * month : 0
         * timezoneOffset : -480
         * year : 113
         * day : 3
         * date : 30
         */

        private RepairDecldatatimeBean repair_decldatatime;
        private int repair_state;
        private String repair_reply;
        private int repair_id;
        /**
         * nanos : 0
         * time : 1357056000000
         * minutes : 0
         * seconds : 0
         * hours : 0
         * month : 0
         * timezoneOffset : -480
         * year : 113
         * day : 3
         * date : 2
         */

        private RepairStarttimeBean repair_starttime;
        private int repair_userid;
        /**
         * nanos : 0
         * time : 1359475200000
         * minutes : 0
         * seconds : 0
         * hours : 0
         * month : 0
         * timezoneOffset : -480
         * year : 113
         * day : 3
         * date : 30
         */

        private RepairCompletetimeBean repair_completetime;
        /**
         * nanos : 0
         * time : 1359043200000
         * minutes : 0
         * seconds : 0
         * hours : 0
         * month : 0
         * timezoneOffset : -480
         * year : 113
         * day : 5
         * date : 25
         */

        private RepairEndtimeBean repair_endtime;

        public String getRepair_project() {
            return repair_project;
        }

        public void setRepair_project(String repair_project) {
            this.repair_project = repair_project;
        }

        public RepairDecldatatimeBean getRepair_decldatatime() {
            return repair_decldatatime;
        }

        public void setRepair_decldatatime(RepairDecldatatimeBean repair_decldatatime) {
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

        public RepairStarttimeBean getRepair_starttime() {
            return repair_starttime;
        }

        public void setRepair_starttime(RepairStarttimeBean repair_starttime) {
            this.repair_starttime = repair_starttime;
        }

        public int getRepair_userid() {
            return repair_userid;
        }

        public void setRepair_userid(int repair_userid) {
            this.repair_userid = repair_userid;
        }

        public RepairCompletetimeBean getRepair_completetime() {
            return repair_completetime;
        }

        public void setRepair_completetime(RepairCompletetimeBean repair_completetime) {
            this.repair_completetime = repair_completetime;
        }

        public RepairEndtimeBean getRepair_endtime() {
            return repair_endtime;
        }

        public void setRepair_endtime(RepairEndtimeBean repair_endtime) {
            this.repair_endtime = repair_endtime;
        }

        public static class RepairDecldatatimeBean {
            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
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

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
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

        public static class RepairStarttimeBean {
            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
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

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
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

        public static class RepairCompletetimeBean {
            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
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

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
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

        public static class RepairEndtimeBean {
            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
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

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
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
}
