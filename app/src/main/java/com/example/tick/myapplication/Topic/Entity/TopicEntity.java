package com.example.tick.myapplication.Topic.Entity;

import java.util.List;

/**
 * Created by Tick on 2016/9/29.
 */
public class TopicEntity {

    /**
     * message : 获取短信成功!
     * code : 1
     */

    private SendCodeBean SendCode;
    /**
     * topic : {"topicpicture_path":"","topic_type":1,"topic_datetime":{"nanos":0,"time":1474452183000,"minutes":3,"seconds":3,"hours":18,"month":8,"timezoneOffset":-480,"year":116,"day":3,"date":21},"topic_comment":"原D1-352打印社搬至D1-509宿舍，感谢新老客户的支持。支付宝转账：13211064041","topic_userid":1,"topic_id":1,"topic_title":"D1-509打印社 "}
     * author : {"user_nickname":"丰小华","user_head":"/upload/201324133242.png","user_id":1}
     * praiseVo : [{"user_nickname":"鹏小蔡","user_head":"/upload/201324133220.png","user_id":6},{"user_nickname":"lin","user_head":"/upload/201324133223.png","user_id":5},{"user_nickname":"jian","user_head":"/upload/201324133252.png","user_id":2},{"user_nickname":"w_w","user_head":"/upload/201324133211.png","user_id":3},{"user_nickname":"丰小华","user_head":"/upload/201324133242.png","user_id":1}]
     * commentVo : [[{"comment_id":1,"replyUser":null,"comment_content":"1号评论","user":{"user_nickname":"丰小华","user_head":"/upload/201324133242.png","user_id":1},"comment_time":{"nanos":0,"time":1474452309000,"minutes":5,"seconds":9,"hours":18,"month":8,"timezoneOffset":-480,"year":116,"day":3,"date":21}},{"comment_id":3,"replyUser":{"user_nickname":"丰小华","user_head":"/upload/201324133242.png","user_id":1},"comment_content":"3号评论","user":{"user_nickname":"jian","user_head":"/upload/201324133252.png","user_id":2},"comment_time":{"nanos":0,"time":1474529387000,"minutes":29,"seconds":47,"hours":15,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22}},{"comment_id":4,"replyUser":{"user_nickname":"jian","user_head":"/upload/201324133252.png","user_id":2},"comment_content":"4号评论","user":{"user_nickname":"w_w","user_head":"/upload/201324133211.png","user_id":3},"comment_time":{"nanos":0,"time":1474529387000,"minutes":29,"seconds":47,"hours":15,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22}},{"comment_id":5,"replyUser":{"user_nickname":"jian","user_head":"/upload/201324133252.png","user_id":2},"comment_content":"5号评论","user":{"user_nickname":"Raindrop","user_head":"/upload/201324133221.png","user_id":4},"comment_time":{"nanos":0,"time":1474529387000,"minutes":29,"seconds":47,"hours":15,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22}},{"comment_id":6,"replyUser":{"user_nickname":"w_w","user_head":"/upload/201324133211.png","user_id":3},"comment_content":"6号评论","user":{"user_nickname":"lin","user_head":"/upload/201324133223.png","user_id":5},"comment_time":{"nanos":0,"time":1474529387000,"minutes":29,"seconds":47,"hours":15,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22}}],[{"comment_id":2,"replyUser":null,"comment_content":"2号评论","user":{"user_nickname":"jian","user_head":"/upload/201324133252.png","user_id":2},"comment_time":{"nanos":0,"time":1474529387000,"minutes":29,"seconds":47,"hours":15,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22}},{"comment_id":7,"replyUser":{"user_nickname":"jian","user_head":"/upload/201324133252.png","user_id":2},"comment_content":"7号评论","user":{"user_nickname":"鹏小蔡","user_head":"/upload/201324133220.png","user_id":6},"comment_time":{"nanos":0,"time":1474529387000,"minutes":29,"seconds":47,"hours":15,"month":8,"timezoneOffset":-480,"year":116,"day":4,"date":22}}]]
     * praise : true
     */

    private List<TopicListBean> TopicList;

    public SendCodeBean getSendCode() {
        return SendCode;
    }

    public void setSendCode(SendCodeBean SendCode) {
        this.SendCode = SendCode;
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
        /**
         * topicpicture_path :
         * topic_type : 1
         * topic_datetime : {"nanos":0,"time":1474452183000,"minutes":3,"seconds":3,"hours":18,"month":8,"timezoneOffset":-480,"year":116,"day":3,"date":21}
         * topic_comment : 原D1-352打印社搬至D1-509宿舍，感谢新老客户的支持。支付宝转账：13211064041
         * topic_userid : 1
         * topic_id : 1
         * topic_title : D1-509打印社
         */

        private TopicBean topic;
        /**
         * user_nickname : 丰小华
         * user_head : /upload/201324133242.png
         * user_id : 1
         */

        private AuthorBean author;
        private boolean praise;
        /**
         * user_nickname : 鹏小蔡
         * user_head : /upload/201324133220.png
         * user_id : 6
         */

        private List<PraiseVoBean> praiseVo;
        /**
         * comment_id : 1
         * replyUser : null
         * comment_content : 1号评论
         * user : {"user_nickname":"丰小华","user_head":"/upload/201324133242.png","user_id":1}
         * comment_time : {"nanos":0,"time":1474452309000,"minutes":5,"seconds":9,"hours":18,"month":8,"timezoneOffset":-480,"year":116,"day":3,"date":21}
         */

        private List<List<CommentVoBean>> commentVo;

        public TopicBean getTopic() {
            return topic;
        }

        public void setTopic(TopicBean topic) {
            this.topic = topic;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public boolean isPraise() {
            return praise;
        }

        public void setPraise(boolean praise) {
            this.praise = praise;
        }

        public List<PraiseVoBean> getPraiseVo() {
            return praiseVo;
        }

        public void setPraiseVo(List<PraiseVoBean> praiseVo) {
            this.praiseVo = praiseVo;
        }

        public List<List<CommentVoBean>> getCommentVo() {
            return commentVo;
        }

        public void setCommentVo(List<List<CommentVoBean>> commentVo) {
            this.commentVo = commentVo;
        }

        public static class TopicBean {
            private String topicpicture_path;
            private int topic_type;
            /**
             * nanos : 0
             * time : 1474452183000
             * minutes : 3
             * seconds : 3
             * hours : 18
             * month : 8
             * timezoneOffset : -480
             * year : 116
             * day : 3
             * date : 21
             */

            private TopicDatetimeBean topic_datetime;
            private String topic_comment;
            private int topic_userid;
            private int topic_id;
            private String topic_title;

            public String getTopicpicture_path() {
                return topicpicture_path;
            }

            public void setTopicpicture_path(String topicpicture_path) {
                this.topicpicture_path = topicpicture_path;
            }

            public int getTopic_type() {
                return topic_type;
            }

            public void setTopic_type(int topic_type) {
                this.topic_type = topic_type;
            }

            public TopicDatetimeBean getTopic_datetime() {
                return topic_datetime;
            }

            public void setTopic_datetime(TopicDatetimeBean topic_datetime) {
                this.topic_datetime = topic_datetime;
            }

            public String getTopic_comment() {
                return topic_comment;
            }

            public void setTopic_comment(String topic_comment) {
                this.topic_comment = topic_comment;
            }

            public int getTopic_userid() {
                return topic_userid;
            }

            public void setTopic_userid(int topic_userid) {
                this.topic_userid = topic_userid;
            }

            public int getTopic_id() {
                return topic_id;
            }

            public void setTopic_id(int topic_id) {
                this.topic_id = topic_id;
            }

            public String getTopic_title() {
                return topic_title;
            }

            public void setTopic_title(String topic_title) {
                this.topic_title = topic_title;
            }

            public static class TopicDatetimeBean {
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

        public static class AuthorBean {
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

        public static class PraiseVoBean {
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

        public static class CommentVoBean {
            private int comment_id;
            private Object replyUser;
            private String comment_content;
            /**
             * user_nickname : 丰小华
             * user_head : /upload/201324133242.png
             * user_id : 1
             */

            private UserBean user;
            /**
             * nanos : 0
             * time : 1474452309000
             * minutes : 5
             * seconds : 9
             * hours : 18
             * month : 8
             * timezoneOffset : -480
             * year : 116
             * day : 3
             * date : 21
             */

            private CommentTimeBean comment_time;

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public Object getReplyUser() {
                return replyUser;
            }

            public void setReplyUser(Object replyUser) {
                this.replyUser = replyUser;
            }

            public String getComment_content() {
                return comment_content;
            }

            public void setComment_content(String comment_content) {
                this.comment_content = comment_content;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public CommentTimeBean getComment_time() {
                return comment_time;
            }

            public void setComment_time(CommentTimeBean comment_time) {
                this.comment_time = comment_time;
            }

            public static class UserBean {
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

            public static class CommentTimeBean {
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
}
