package com.example.tick.myapplication.Mine.Entity;

import java.util.List;

/**
 * Created by Tick on 2016/9/27.
 */
public class BackSuggest {

    /**
     * message : 获取成功!
     * code : 1
     */

    private SendCodeBean SendCode;
    /**
     * complains_title : 蛋疼
     * complains_replytime : {"nanos":0,"time":1473733484000,"minutes":24,"seconds":44,"hours":10,"month":8,"timezoneOffset":-480,"year":116,"day":2,"date":13}
     * complains_phone : 13526354456
     * complains_id : 1
     * complains_usertype : 3
     * complains_content : 啦啦啦
     * complains_state : 0
     * complains_datetime : {"nanos":0,"time":1470018236000,"minutes":23,"seconds":56,"hours":10,"month":7,"timezoneOffset":-480,"year":116,"day":1,"date":1}
     * complains_replycontent : 我dsfa
     * complains_userid : 1
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
        /**
         * nanos : 0
         * time : 1473733484000
         * minutes : 24
         * seconds : 44
         * hours : 10
         * month : 8
         * timezoneOffset : -480
         * year : 116
         * day : 2
         * date : 13
         */

        private ComplainsReplytimeBean complains_replytime;
        private String complains_phone;
        private int complains_id;
        private int complains_usertype;
        private String complains_content;
        private int complains_state;
        /**
         * nanos : 0
         * time : 1470018236000
         * minutes : 23
         * seconds : 56
         * hours : 10
         * month : 7
         * timezoneOffset : -480
         * year : 116
         * day : 1
         * date : 1
         */

        private ComplainsDatetimeBean complains_datetime;
        private String complains_replycontent;
        private int complains_userid;

        public String getComplains_title() {
            return complains_title;
        }

        public void setComplains_title(String complains_title) {
            this.complains_title = complains_title;
        }

        public ComplainsReplytimeBean getComplains_replytime() {
            return complains_replytime;
        }

        public void setComplains_replytime(ComplainsReplytimeBean complains_replytime) {
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

        public ComplainsDatetimeBean getComplains_datetime() {
            return complains_datetime;
        }

        public void setComplains_datetime(ComplainsDatetimeBean complains_datetime) {
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

        public static class ComplainsReplytimeBean {
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

        public static class ComplainsDatetimeBean {
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
