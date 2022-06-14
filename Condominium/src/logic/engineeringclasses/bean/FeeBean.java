package logic.engineeringclasses.bean;

public class FeeBean {

    private String feeApartment;
    private Double feeAdmin;
    private Double feePark;
    private Double feeElevator;
    private Double feePet;
    private Double feeWifi;


    public String getFeeApt() {
        return feeApartment;
    }

    public void setFeeApt(String apartment) {
        this.feeApartment = apartment;
    }

    public Double getFeeAdmin() {
        return feeAdmin;
    }

    public void setFeeAdmin(Double feeAdmin) {
        this.feeAdmin = feeAdmin;
    }

    public Double getFeePark() {
        return feePark;
    }

    public void setFeePark(Double feePark) {
        this.feePark = feePark;
    }

    public Double getFeeElevator() {
        return feeElevator;
    }

    public void setFeeElevator(Double feeElevator) {
        this.feeElevator = feeElevator;
    }

    public Double getFeePet() {
        return feePet;
    }

    public void setFeePet(Double feePet) {
        this.feePet = feePet;
    }

    public Double getFeeWifi() {
        return feeWifi;
    }

    public void setFeeWifi(Double feeWifi) {
        this.feeWifi = feeWifi;
    }


}
