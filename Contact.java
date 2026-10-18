/** A contact with an immutable identifier and validated editable details. */
public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {
        requireLength(contactId, 10, "contactId");
        this.contactId = contactId;
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    private static void requireLength(String value, int maximum, String field) {
        if (value == null || value.length() > maximum) {
            throw new IllegalArgumentException(field + " must be non-null and at most "
                    + maximum + " characters");
        }
    }

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setFirstName(String firstName) {
        requireLength(firstName, 10, "firstName");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        requireLength(lastName, 10, "lastName");
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("[0-9]{10}")) {
            throw new IllegalArgumentException("phone must contain exactly 10 digits");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        requireLength(address, 30, "address");
        this.address = address;
    }
}
