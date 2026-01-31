
public class Bill {
    private String billId;
    private double amount;
    private Status status;
    private String qrCode;
    private String houseNumber; // เพิ่มเพื่อให้รู้ว่าเป็นของบ้านไหน

    public enum Status {
        UNPAID, PAID
    }

    public Bill(String billId, double amount, Status status, String qrCode, String houseNumber) {
        this.billId = billId;
        this.amount = amount;
        this.status = status;
        this.qrCode = qrCode;
        this.houseNumber = houseNumber;
    }

    // Getters and Setters
    public String getBillId() { return billId; }
    public double getAmount() { return amount; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getQrCode() { return qrCode; }
    public String getHouseNumber() { return houseNumber; }
}