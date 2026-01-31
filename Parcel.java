public class Parcel {
    private String parcelId;
    private String trackingNumber;
    private Status status; // Enum
    private String houseNumber;

    public enum Status {
        ARRIVED, RECEIVED
    }

    public Parcel(String parcelId, String trackingNumber, Status status, String houseNumber) {
        this.parcelId = parcelId;
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.houseNumber = houseNumber;
    }

    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public String getTrackingNo() {
        return trackingNumber;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNumber = trackingNo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}