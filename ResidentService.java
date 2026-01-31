import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ResidentService {
    private Resident resident;
    private Scanner sc = new Scanner(System.in);

    public ResidentService(Resident resident) {
        this.resident = resident;
    }

    // --- ฟังก์ชันล้างหน้าจอ ---
    private void clearScreen() {
        // ใช้ ANSI Escape Codes สำหรับล้างหน้าจอ (ใช้ได้กับ VS Code, IntelliJ, Linux, macOS)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // --- ฟังก์ชันหยุดรอให้ผู้ใช้กด Enter ก่อนไปต่อ ---
    private void pause() {
        System.out.println("\nPress Enter to continue...");
        sc.nextLine(); // เคลียร์ buffer
        sc.nextLine(); // รอรับ Enter
    }

    // --- 1. เมนูหลัก ---
    public void showMenu() {
        boolean running = true;
        while (running) {
            clearScreen();
            System.out.println("===========================================");
            System.out.println("   JAIBAAN LIVING OS - Welcome, " + resident.getUsername());
            System.out.println("   House No: " + resident.getHouseNumber());
            System.out.println("===========================================");
            System.out.println("1. [Parcel] - Manage your packages");
            System.out.println("2. [Finance] - View & Pay bills");
            System.out.println("0. Logout");
            System.out.println("===========================================");
            System.out.print("Select Menu: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: parcelMenu(); break;
                case 2: billMenu(); break;
                case 0: running = false; break;
                default: 
                    System.out.println("Invalid option!");
                    pause();
            }
        }
    }

    // --- 2. เมนูพัสดุ (แสดงข้อมูลทันที) ---
    private void parcelMenu() {
        boolean inMenu = true;
        while (inMenu) {
            clearScreen();
            System.out.println("--- MY PARCELS ---");
            
            // แสดงข้อมูลทันทีที่เข้ามา
            List<Parcel> myParcels = new ArrayList<>();
            for (Parcel p : MockDatabase.parcels) {
                if (p.getHouseNumber().equals(resident.getHouseNumber())) {
                    myParcels.add(p);
                    System.out.println("- [ID: " + p.getParcelId() + "] Tracking: " + p.getTrackingNo() + " | Status: " + p.getStatus());
                }
            }
            if (myParcels.isEmpty()) System.out.println("(No parcels found)");

            System.out.println("\n-------------------------------------------");
            System.out.println("1. Confirm Receipt (กดรับพัสดุ)");
            System.out.println("0. Back to Main Menu");
            System.out.print("Action: ");

            int choice = sc.nextInt();
            if (choice == 1) {
                confirmParcelReceipt();
            } else if (choice == 0) {
                inMenu = false;
            }
        }
    }

    private void confirmParcelReceipt() {
        System.out.println("\n--- Select Parcel to Confirm ---");
        List<Parcel> arrived = new ArrayList<>();
        for (Parcel p : MockDatabase.parcels) {
            if (p.getHouseNumber().equals(resident.getHouseNumber()) && p.getStatus() == Parcel.Status.ARRIVED) {
                arrived.add(p);
                System.out.println(arrived.size() + ". ID: " + p.getParcelId() + " (" + p.getTrackingNo() + ")");
            }
        }

        if (arrived.isEmpty()) {
            System.out.println("No new parcels to confirm.");
            pause();
            return;
        }

        System.out.print("Enter number (0 to cancel): ");
        int idx = sc.nextInt();
        if (idx > 0 && idx <= arrived.size()) {
            arrived.get(idx - 1).setStatus(Parcel.Status.RECEIVED);
            System.out.println("Success! Status updated.");
            pause();
        }
    }

    // --- 3. เมนูการเงิน (แสดงข้อมูลทันที) ---
    private void billMenu() {
        boolean inMenu = true;
        while (inMenu) {
            clearScreen();
            System.out.println("--- MY BILLS & FINANCE ---");
            
            // แสดงข้อมูลทันที
            List<Bill> myBills = new ArrayList<>();
            for (Bill b : MockDatabase.bills) {
                if (b.getHouseNumber().equals(resident.getHouseNumber())) {
                    myBills.add(b);
                    System.out.println("- [ID: " + b.getBillId() + "] Amount: " + b.getAmount() + " | Status: " + b.getStatus());
                }
            }
            if (myBills.isEmpty()) System.out.println("(No bills found)");

            System.out.println("\n-------------------------------------------");
            System.out.println("1. Pay Unpaid Bill");
            System.out.println("0. Back to Main Menu");
            System.out.print("Action: ");

            int choice = sc.nextInt();
            if (choice == 1) {
                payBill();
            } else if (choice == 0) {
                inMenu = false;
            }
        }
    }

    private void payBill() {
        System.out.println("\n--- Select Bill to Pay ---");
        List<Bill> unpaid = new ArrayList<>();
        for (Bill b : MockDatabase.bills) {
            if (b.getHouseNumber().equals(resident.getHouseNumber()) && b.getStatus() == Bill.Status.UNPAID) {
                unpaid.add(b);
                System.out.println(unpaid.size() + ". ID: " + b.getBillId() + " | Amount: " + b.getAmount());
            }
        }

        if (unpaid.isEmpty()) {
            System.out.println("No unpaid bills.");
            pause();
            return;
        }

        System.out.print("Enter number (0 to cancel): ");
        int idx = sc.nextInt();
        if (idx > 0 && idx <= unpaid.size()) {
            Bill selected = unpaid.get(idx - 1);
            System.out.println("QR Code: " + selected.getQrCode());
            System.out.print("Confirm payment? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) {
                selected.setStatus(Bill.Status.PAID);
                System.out.println("Payment Successful!");
                pause();
            }
        }
    }
}