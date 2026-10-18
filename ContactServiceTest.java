import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    @Test
    void addsDistinctContactsAndFindsThemById() {
        ContactService service = new ContactService();
        Contact one = new Contact("C1", "A", "B", "0123456789", "Main");
        service.addContact(one);
        service.addContact("C2", "C", "D", "9876543210", "Oak");
        assertSame(one, service.getContact("C1"));
        assertEquals("C", service.getContact("C2").getFirstName());
    }

    @Test
    void rejectsNullAndDuplicateContacts() {
        ContactService service = new ContactService();
        service.addContact("C1", "A", "B", "0123456789", "Main");
        assertThrows(IllegalArgumentException.class, () -> service.addContact((Contact) null));
        assertThrows(IllegalArgumentException.class,
                () -> service.addContact("C1", "Other", "Name", "9876543210", "Oak"));
        assertEquals("A", service.getContact("C1").getFirstName());
    }

    @Test
    void removesContactByIdAndRejectsMissingId() {
        ContactService service = new ContactService();
        service.addContact("C1", "A", "B", "0123456789", "Main");
        service.addContact("C2", "C", "D", "9876543210", "Oak");
        service.deleteContact("C1");
        assertThrows(IllegalArgumentException.class, () -> service.getContact("C1"));
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("C1"));
        assertEquals("C", service.getContact("C2").getFirstName());
    }

    @Test
    void updatesFourPermittedFieldsWithoutChangingId() {
        ContactService service = new ContactService();
        service.addContact("C1", "A", "B", "0123456789", "Main");
        service.updateContact("C1", "Alice", "Smith", "9876543210", "Park Lane");
        Contact updated = service.getContact("C1");
        assertAll(
                () -> assertEquals("C1", updated.getContactId()),
                () -> assertEquals("Alice", updated.getFirstName()),
                () -> assertEquals("Smith", updated.getLastName()),
                () -> assertEquals("9876543210", updated.getPhone()),
                () -> assertEquals("Park Lane", updated.getAddress()));
    }

    @Test
    void supportsIndividualFieldUpdates() {
        ContactService service = new ContactService();
        service.addContact("C1", "A", "B", "0123456789", "Main");
        service.updateFirstName("C1", "First");
        service.updateLastName("C1", "Last");
        service.updatePhone("C1", "1112223333");
        service.updateAddress("C1", "Elm Street");
        Contact updated = service.getContact("C1");
        assertAll(
                () -> assertEquals("First", updated.getFirstName()),
                () -> assertEquals("Last", updated.getLastName()),
                () -> assertEquals("1112223333", updated.getPhone()),
                () -> assertEquals("Elm Street", updated.getAddress()));
    }

    @Test
    void invalidUpdatesCannotPartiallyModifySavedContact() {
        ContactService service = new ContactService();
        service.addContact("C1", "A", "B", "0123456789", "Main");
        assertThrows(IllegalArgumentException.class,
                () -> service.updateContact("C1", "Changed", "Name", "bad", "Oak"));
        assertEquals("A", service.getContact("C1").getFirstName());
        assertEquals("Main", service.getContact("C1").getAddress());
        assertThrows(IllegalArgumentException.class,
                () -> service.updateFirstName("C1", "x".repeat(11)));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateLastName("C1", null));
        assertThrows(IllegalArgumentException.class,
                () -> service.updatePhone("C1", "123"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateAddress("C1", null));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateContact("nope", "A", "B", "0123456789", "Main"));
        assertEquals("A", service.getContact("C1").getFirstName());
    }
}
