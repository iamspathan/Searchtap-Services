package me.sohailpathan.www.searchtap;

public class ServiceRequest {
    private String mServiceId;
    private String mUserEmail;
    private String userServiceRetailerName;
    private String userServiceRetailerMobileNo;
    private String sServiceCategory;
    private String sServiceType;
    private String sServiceAddress;
    private String mServicePincode;

    public ServiceRequest(){
    }

    public ServiceRequest(String mServiceId, String mUserEmail ,String userServiceRetailerName, String userServiceRetailerMobileNo, String sServiceType, String sServiceCategory,  String sServiceAddress, String mServicePincode) {
        this.mServiceId = mServiceId;
        this.mUserEmail = mUserEmail;
        this.userServiceRetailerName = userServiceRetailerName;
        this.userServiceRetailerMobileNo = userServiceRetailerMobileNo;
        this.sServiceCategory = sServiceCategory;
        this.sServiceType = sServiceType;
        this.sServiceAddress = sServiceAddress;
        this.mServicePincode = mServicePincode;
    }
    public String getsServiceCategory() {
        return sServiceCategory;
    }

    public String getsServiceType() {
        return sServiceType;
    }

    public String getsServiceAddress() {
        return sServiceAddress;
    }

    public String getmServicePincode() {
        return mServicePincode;
    }

    public String getmServiceId() {
        return mServiceId;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public String getUserServiceRetailerName() {
        return userServiceRetailerName;
    }

    public String getUserServiceRetailerMobileNo() {
        return userServiceRetailerMobileNo;
    }

}
