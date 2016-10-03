package com.example.tick.myapplication.User.Entity;

/**
 * Created by Tick on 2016/9/20.
 */
public class UserAll {

    /**
     * community : {"community_approvaltime":{"nanos":0,"time":1472719843000,"minutes":50,"seconds":43,"hours":16,"month":8,"year":116,"timezoneOffset":-480,"day":4,"date":1},"community_location":"广东省广州市天河区","community_approver":"方法","community_name":"幸福小区","community_regitime":{"nanos":0,"time":1472719843000,"minutes":50,"seconds":43,"hours":16,"month":8,"year":116,"timezoneOffset":-480,"day":4,"date":1},"community_checkstate":0,"community_indirect":"v个非官方个","community_id":1}
     * usertype : {"usertype_name":"业主","usertype_id":1}
     * house : {"house_roomnumber":"","house_price":200000,"house_prePrice":20000,"user_id":1,"house_picture":"/image/house1.jpg","house_id":1,"community_id":1,"house_floornumber":"","house_area":100}
     * user : {"user_landstate":0,"user_card":"510105199010017999 ","user_checkstate":0,"user_type":1,"user_registertime":{"nanos":0,"time":1472717217000,"minutes":6,"seconds":57,"hours":16,"month":8,"year":116,"timezoneOffset":-480,"day":4,"date":1},"user_realname":"叶丰华","user_nickname":"丰小华","user_email":"334949356@qqcom","user_age":23,"user_approvaltime":null,"user_account":"13255678822","user_community":1,"user_approver":"","user_password":"123456","user_head":"","user_id":1,"user_sex":"男","user_house":1}
     */

    private UserVoBean UserVo;
    /**
     * message : 登录成功!
     * code : 1
     */

    private SendCodeBean SendCode;

    public UserVoBean getUserVo() {
        return UserVo;
    }

    public void setUserVo(UserVoBean UserVo) {
        this.UserVo = UserVo;
    }

    public SendCodeBean getSendCode() {
        return SendCode;
    }

    public void setSendCode(SendCodeBean SendCode) {
        this.SendCode = SendCode;
    }

    public static class UserVoBean {
        /**
         * community_approvaltime : {"nanos":0,"time":1472719843000,"minutes":50,"seconds":43,"hours":16,"month":8,"year":116,"timezoneOffset":-480,"day":4,"date":1}
         * community_location : 广东省广州市天河区
         * community_approver : 方法
         * community_name : 幸福小区
         * community_regitime : {"nanos":0,"time":1472719843000,"minutes":50,"seconds":43,"hours":16,"month":8,"year":116,"timezoneOffset":-480,"day":4,"date":1}
         * community_checkstate : 0
         * community_indirect : v个非官方个
         * community_id : 1
         */

        private CommunityBean community;
        /**
         * usertype_name : 业主
         * usertype_id : 1
         */

        private UsertypeBean usertype;
        /**
         * house_roomnumber :
         * house_price : 200000
         * house_prePrice : 20000
         * user_id : 1
         * house_picture : /image/house1.jpg
         * house_id : 1
         * community_id : 1
         * house_floornumber :
         * house_area : 100
         */

        private HouseBean house;
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
         * user_head :
         * user_id : 1
         * user_sex : 男
         * user_house : 1
         */

        private UserBean user;

        public CommunityBean getCommunity() {
            return community;
        }

        public void setCommunity(CommunityBean community) {
            this.community = community;
        }

        public UsertypeBean getUsertype() {
            return usertype;
        }

        public void setUsertype(UsertypeBean usertype) {
            this.usertype = usertype;
        }

        public HouseBean getHouse() {
            return house;
        }

        public void setHouse(HouseBean house) {
            this.house = house;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class CommunityBean {
            /**
             * nanos : 0
             * time : 1472719843000
             * minutes : 50
             * seconds : 43
             * hours : 16
             * month : 8
             * year : 116
             * timezoneOffset : -480
             * day : 4
             * date : 1
             */

            private CommunityApprovaltimeBean community_approvaltime;
            private String community_location;
            private String community_approver;
            private String community_name;
            /**
             * nanos : 0
             * time : 1472719843000
             * minutes : 50
             * seconds : 43
             * hours : 16
             * month : 8
             * year : 116
             * timezoneOffset : -480
             * day : 4
             * date : 1
             */

            private CommunityRegitimeBean community_regitime;
            private int community_checkstate;
            private String community_indirect;
            private int community_id;

            public CommunityApprovaltimeBean getCommunity_approvaltime() {
                return community_approvaltime;
            }

            public void setCommunity_approvaltime(CommunityApprovaltimeBean community_approvaltime) {
                this.community_approvaltime = community_approvaltime;
            }

            public String getCommunity_location() {
                return community_location;
            }

            public void setCommunity_location(String community_location) {
                this.community_location = community_location;
            }

            public String getCommunity_approver() {
                return community_approver;
            }

            public void setCommunity_approver(String community_approver) {
                this.community_approver = community_approver;
            }

            public String getCommunity_name() {
                return community_name;
            }

            public void setCommunity_name(String community_name) {
                this.community_name = community_name;
            }

            public CommunityRegitimeBean getCommunity_regitime() {
                return community_regitime;
            }

            public void setCommunity_regitime(CommunityRegitimeBean community_regitime) {
                this.community_regitime = community_regitime;
            }

            public int getCommunity_checkstate() {
                return community_checkstate;
            }

            public void setCommunity_checkstate(int community_checkstate) {
                this.community_checkstate = community_checkstate;
            }

            public String getCommunity_indirect() {
                return community_indirect;
            }

            public void setCommunity_indirect(String community_indirect) {
                this.community_indirect = community_indirect;
            }

            public int getCommunity_id() {
                return community_id;
            }

            public void setCommunity_id(int community_id) {
                this.community_id = community_id;
            }

            public static class CommunityApprovaltimeBean {
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

            public static class CommunityRegitimeBean {
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

        public static class UsertypeBean {
            private String usertype_name;
            private int usertype_id;

            public String getUsertype_name() {
                return usertype_name;
            }

            public void setUsertype_name(String usertype_name) {
                this.usertype_name = usertype_name;
            }

            public int getUsertype_id() {
                return usertype_id;
            }

            public void setUsertype_id(int usertype_id) {
                this.usertype_id = usertype_id;
            }
        }

        public static class HouseBean {
            private String house_roomnumber;
            private int house_price;
            private int house_prePrice;
            private int user_id;
            private String house_picture;
            private int house_id;
            private int community_id;
            private String house_floornumber;
            private int house_area;

            public String getHouse_roomnumber() {
                return house_roomnumber;
            }

            public void setHouse_roomnumber(String house_roomnumber) {
                this.house_roomnumber = house_roomnumber;
            }

            public int getHouse_price() {
                return house_price;
            }

            public void setHouse_price(int house_price) {
                this.house_price = house_price;
            }

            public int getHouse_prePrice() {
                return house_prePrice;
            }

            public void setHouse_prePrice(int house_prePrice) {
                this.house_prePrice = house_prePrice;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getHouse_picture() {
                return house_picture;
            }

            public void setHouse_picture(String house_picture) {
                this.house_picture = house_picture;
            }

            public int getHouse_id() {
                return house_id;
            }

            public void setHouse_id(int house_id) {
                this.house_id = house_id;
            }

            public int getCommunity_id() {
                return community_id;
            }

            public void setCommunity_id(int community_id) {
                this.community_id = community_id;
            }

            public String getHouse_floornumber() {
                return house_floornumber;
            }

            public void setHouse_floornumber(String house_floornumber) {
                this.house_floornumber = house_floornumber;
            }

            public int getHouse_area() {
                return house_area;
            }

            public void setHouse_area(int house_area) {
                this.house_area = house_area;
            }
        }

        public static class UserBean {
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
