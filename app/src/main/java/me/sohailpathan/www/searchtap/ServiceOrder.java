package me.sohailpathan.www.searchtap;

public class ServiceOrder {

    private String mRetailerName;
    private String mServiceType, mServiceCategory;
    private String mRetailerMobileNo;


    public ServiceOrder(String mRetailerName, String mServiceType, String mServiceCategory, String mRetailerMobileNo) {
        this.mRetailerName = mRetailerName;
        this.mServiceType = mServiceType;
        this.mServiceCategory = mServiceCategory;
        this.mRetailerMobileNo = mRetailerMobileNo;
    }

    public void setmRetailerName(String mRetailerName) {
        this.mRetailerName = mRetailerName;
    }

    public void setmServiceType(String mServiceType) {
        this.mServiceType = mServiceType;
    }

    public void setmServiceCategory(String mServiceCategory) {
        this.mServiceCategory = mServiceCategory;
    }

    public void setmRetailerMobileNo(String mRetailerMobileNo) {
        this.mRetailerMobileNo = mRetailerMobileNo;
    }

    public String getmRetailerName() {
        return mRetailerName;

    }

    public String getmServiceType() {
        return mServiceType;
    }

    public String getmServiceCategory() {
        return mServiceCategory;
    }

    public String getmRetailerMobileNo() {
        return mRetailerMobileNo;
    }

}
