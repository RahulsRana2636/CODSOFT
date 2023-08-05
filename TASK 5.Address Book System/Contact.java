public class Contact {
    private String name;
    private int phoneNumber;
    private String emailAddress;
    // You can add more relevant details here, like address, birthday, etc.
    private String address;

    public Contact(String name, String emailAddress, String address, int phoneNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters for attributes (optional if you're using public fields)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void address(String Address) {
        this.address = address;
    }

    // You can add more methods here as needed, like toString() to display contact
    // information.
}
