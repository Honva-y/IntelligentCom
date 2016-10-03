package com.example.tick.myapplication.Home.Entity;


/**
 * Created by Tick on 2016/9/14.
 */
public class NotifyEntity {

    private SendCodeBean SendCode;

    private NotifyListBean notifyList;

    public SendCodeBean getSendCode() {
        return SendCode;
    }

    public void setSendCode(SendCodeBean SendCode) {
        this.SendCode = SendCode;
    }

    public NotifyListBean getNotifyList() {
        return notifyList;
    }

    public void setNotifyList(NotifyListBean notifyList) {
        this.notifyList = notifyList;
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

    public static class NotifyListBean {
        /**
         * nanos : 0
         * time : 1474348649000
         * minutes : 17
         * seconds : 29
         * hours : 13
         * month : 8
         * year : 116
         * timezoneOffset : -480
         * day : 2
         * date : 20
         */

        private NotifyDatetimeBean notify_datetime;
        private int notify_id;
        private String notify_content;
        private int notify_userid;
        private String notify_titile;

        public NotifyDatetimeBean getNotify_datetime() {
            return notify_datetime;
        }

        public void setNotify_datetime(NotifyDatetimeBean notify_datetime) {
            this.notify_datetime = notify_datetime;
        }

        public int getNotify_id() {
            return notify_id;
        }

        public void setNotify_id(int notify_id) {
            this.notify_id = notify_id;
        }

        public String getNotify_content() {
            return notify_content;
        }

        public void setNotify_content(String notify_content) {
            this.notify_content = notify_content;
        }

        public int getNotify_userid() {
            return notify_userid;
        }

        public void setNotify_userid(int notify_userid) {
            this.notify_userid = notify_userid;
        }

        public String getNotify_titile() {
            return notify_titile;
        }

        public void setNotify_titile(String notify_titile) {
            this.notify_titile = notify_titile;
        }

        public static class NotifyDatetimeBean {
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
}
