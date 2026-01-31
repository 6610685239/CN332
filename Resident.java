public class Resident extends User{
    private String houseNumber;
    private String phone;

    public Resident(String username, String password, String houseNumber, String phone) {
        super(username, password); // เรียกใช้ Constructor ของ User
        this.houseNumber = houseNumber;
        this.phone = phone;
    }
    
    public String getHouseNumber() {
        return houseNumber;
    }
    
    public String getPhone() {
        return phone;
    }
    
}