import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. จำลองการ Login (สมมติว่า Login ผ่านแล้วได้ตัว Resident มา)
        Resident currentResident = new Resident("somchai_007", "pass123", "101/5", "081-234-5678");
        
        // 2. เรียกใช้ Service
        ResidentService service = new ResidentService(currentResident);
        
        // 3. รัน Loop เมนู
        service.showMenu();
    }
}