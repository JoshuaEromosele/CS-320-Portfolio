import java.util.HashMap;
import java.util.Map;

/** Stores contact objects in memory; IDs are unique within this service. */
public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("duplicate contact ID: " + contact.getContactId());
        }
        contacts.put(contact.getContactId(), contact);
    }

    public void addContact(String contactId, String firstName, String lastName,
                           String phone, String address) {
        addContact(new Contact(contactId, firstName, lastName, phone, address));
    }

    public Contact getContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("unknown contact ID: " + contactId);
        }
        return contact;
    }

    public void deleteContact(String contactId) {
        if (contacts.remove(contactId) == null) {
            throw new IllegalArgumentException("unknown contact ID: " + contactId);
        }
    }

    /** Validates all new fields before changing any existing field. */
    public void updateContact(String contactId, String firstName, String lastName,
                              String phone, String address) {
        Contact current = getContact(contactId);
        Contact validated = new Contact(contactId, firstName, lastName, phone, address);
        current.setFirstName(validated.getFirstName());
        current.setLastName(validated.getLastName());
        current.setPhone(validated.getPhone());
        current.setAddress(validated.getAddress());
    }

    public void updateFirstName(String contactId, String firstName) {
        getContact(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        getContact(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        getContact(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        getContact(contactId).setAddress(address);
    }
}
