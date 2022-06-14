package logic.engineeringclasses.bean;

public class RateBean {

    private String id;
    private String res;
    private String value;
    private String txt;

    public String getId(){
        return id;
    }

    public void setId(String rateId){
        this.id = rateId;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String resId) {
        this.res = resId;
    }

    public String getVal() {
        return value;
    }

    public void setVal(String rateVal) {
        this.value = rateVal;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String rateTxt) {
        this.txt = rateTxt;
    }
}
