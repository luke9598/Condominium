package logic.engineeringclasses.bean;

public class RateBean {

    private String rateId;
    private String ownId;
    private String resId;
    private String rateVal;
    private String rateTxt;

    public String getRateId(){
        return rateId;
    }

    public void setRateId(String rateId){
        this.rateId = rateId;
    }

    public String getOwnId() {return ownId;}

    public void setOwnId(String ownId) {
        this.ownId = ownId;}

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getRateVal() {
        return rateVal;
    }

    public void setRateVal(String rateVal) {
        this.rateVal = rateVal;
    }

    public String getRateTxt() {
        return rateTxt;
    }

    public void setRateTxt(String rateTxt) {
        this.rateTxt = rateTxt;
    }
}
