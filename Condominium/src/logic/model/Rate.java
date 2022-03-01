package logic.model;

public class Rate {

    private String rateId;
    private String ownId;
    private String resId;
    private String rateVal;
    private String rateTxt;


    public Rate(String rateId,String resId, String rateVal, String rateTxt) {
        this.rateId = rateId;
        this.resId = resId;
        this.rateVal = rateVal;
        this.rateTxt = rateTxt;
    }

    public Rate(String rateId,String resId,String ownId, String rateVal, String rateTxt) {
        this.rateId = rateId;
        this.resId = resId;
        this.ownId = ownId;
        this.rateVal = rateVal;
        this.rateTxt = rateTxt;
    }

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
