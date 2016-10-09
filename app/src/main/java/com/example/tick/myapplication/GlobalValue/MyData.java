package com.example.tick.myapplication.GlobalValue;

import android.app.Application;

/**
 * Created by Tick on 2016/9/20.
 */
public class MyData extends Application {
        private String BaseUrl = "http://10.0.2.2:8080/LaiFuCommunity";
//    private String BaseUrl = "http://192.168.191.1:8080/LaiFuCommunity";//真机测试ip
    private String RegisterUrl = BaseUrl + "/Android/user/register";//用户注册地址
    private String ForgetPwUrl = BaseUrl + "/Android/user/forgetPassword";//用户忘记密码地址
    private String LoginUrl = BaseUrl + "/Android/user/login";//用户登录地址
    private String RepairUrl = BaseUrl + "/Android/property/addRepair";//自助维修地址
    private String ChangePwUrl = BaseUrl + "/Android/user/updatePassword";//修改密码，需要提供旧密码
    private String SuggestUrl = BaseUrl + "/Android/property/addComplains";//投诉建议
    private String NickUrl = BaseUrl + "/Android/user/updateNickname";//修改昵称
    private String userHead = BaseUrl + "/Android/user/uploadPicture";//用户修改头像
    private String Notify = BaseUrl + "/Android/property/getNotify";//通知
    private String MySuggest = BaseUrl + "/Android/property/getComplains";//投诉建议列表
    private String MyRepair = BaseUrl + "/Android/property/getRepair";//我的修理列表
    private String GetCode = BaseUrl + "/Android/user/getSMS";//获取验证码
    private String TopicURL = BaseUrl + "/Android/user/topic/0/5/getTopicList?communityTopic=1";//获取小区话题数据?communityTopic=1
    private String TopicSecondURL = BaseUrl + "";//获取小区二手数据
    private String TopicClickZan = BaseUrl + "/Android/user/topic/";//点赞数据传输
    private String TopicCancleZan = BaseUrl + "/Android/user/topic/";//取消赞
    private String TopicComment = BaseUrl + "";//小区话题评论
    private String Financial = BaseUrl + "";//缴费记录
    private String CompleteInfo = BaseUrl + "/Android/user/completeInfor";//完善信息
    private String ModifyPwUrl = BaseUrl + "";//修改密码，不需要提供旧密码

    public String getCompleteInfo() {
        return CompleteInfo;
    }

    public String getTopicComment() {
        return TopicComment;
    }

    public String getTopicCancleZan() {
        return TopicCancleZan;
    }

    public String getTopicClickZan() {
        return TopicClickZan;
    }

    public String getTopicURL() {
        return TopicURL;
    }

    public String getTopicSecondURL() {
        return TopicSecondURL;
    }

    public String getFinancial() {
        return Financial;
    }

    public String getModifyPwUrl() {
        return ModifyPwUrl;
    }

    public String getRegisterUrl() {
        return RegisterUrl;
    }

    public String getForgetPwUrl() {
        return ForgetPwUrl;
    }

    public String getGetCode() {
        return GetCode;
    }

    public String getMyRepair() {
        return MyRepair;
    }

    public String getMySuggest() {
        return MySuggest;
    }

    public String getNotify() {
        return Notify;
    }

    public String getUserHead() {
        return userHead;
    }

    public String getNickUrl() {
        return NickUrl;
    }

    public String getSuggestUrl() {
        return SuggestUrl;
    }

    public String getChangePwUrl() {
        return ChangePwUrl;
    }

    public String getBaseUrl() {
        return BaseUrl;
    }

    public String getLoginUrl() {
        return LoginUrl;
    }


    public String getRepairUrl() {
        return RepairUrl;
    }

}
