import java.util.ArrayList;
import java.util.List;

public class MockDatabase {
    // เก็บข้อมูลพัสดุและบิลทั้งหมดของโครงการ
    public static List<Parcel> parcels = new ArrayList<>();
    public static List<Bill> bills = new ArrayList<>();

    // static block สำหรับสร้างข้อมูลจำลองทันทีที่โปรแกรมเริ่มทำงาน
    static {
        // --- ข้อมูลพัสดุจำลอง ---
        // พัสดุของบ้าน 101/5 (บ้านที่เราใช้ทดสอบ)
        parcels.add(new Parcel("P001", "TH-999-01", Parcel.Status.ARRIVED, "101/5"));
        parcels.add(new Parcel("P002", "SHP-777-02", Parcel.Status.RECEIVED, "101/5"));
        
        // พัสดุของบ้านอื่น (เพื่อทดสอบว่าระบบ Filter ถูกต้องไหม)
        parcels.add(new Parcel("P003", "LEX-555-03", Parcel.Status.ARRIVED, "99/1"));

        // --- ข้อมูลบิลจำลอง ---
        // บิลของบ้าน 101/5
        bills.add(new Bill("B-2024-001", 1200.0, Bill.Status.UNPAID, "HTTPS://PROMPT-PAY/QR001", "101/5"));
        bills.add(new Bill("B-2024-002", 550.50, Bill.Status.PAID, "HTTPS://PROMPT-PAY/QR002", "101/5"));
        
        // บิลของบ้านอื่น
        bills.add(new Bill("B-2024-003", 2000.0, Bill.Status.UNPAID, "HTTPS://PROMPT-PAY/QR003", "99/1"));
    }
}