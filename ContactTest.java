import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

class ContactTest {
    private Contact sample() {
        return new Contact("C123", "Alex", "Morgan", "1234567890", "1 Main Street");
    }

    @Test
    void constructorStoresValidFieldsAndAcceptsMaximumLengths() {
        Contact contact = new Contact("1234567890", "1234567890", "abcdefghij",
                "0123456789", "a".repeat(30));
        assertAll(
                () -> assertEquals("1234567890", contact.getContactId()),
                () -> assertEquals("1234567890", contact.getFirstName()),
                () -> assertEquals("abcdefghij", contact.getLastName()),
                () -> assertEquals("0123456789", contact.getPhone()),
                () -> assertEquals("a".repeat(30), contact.getAddress()));
    }

    @Test
    void contactIdMustNotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "A", "B", "0123456789", "C"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "A", "B", "0123456789", "C"));
    }

    @Test
    void idCannotBeUpdated() throws NoSuchFieldException {
        assertTrue(Modifier.isFinal(Contact.class.getDeclaredField("contactId").getModifiers()));
        assertThrows(NoSuchMethodException.class,
                () -> Contact.class.getMethod("setContactId", String.class));
        assertEquals("C123", sample().getContactId());
    }

    @Test
    void firstNameMustBePresentAndAtMostTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", null, "B", "0123456789", "C"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "12345678901", "B", "0123456789", "C"));
    }

    @Test
    void lastNameMustBePresentAndAtMostTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", null, "0123456789", "C"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "12345678901", "0123456789", "C"));
    }

    @Test
    void phoneMustBeExactlyTenAsciiDigits() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", null, "C"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "123456789", "C"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "12345678901", "C"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "12345X7890", "C"));
    }

    @Test
    void addressMustBePresentAndAtMostThirtyCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "0123456789", null));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "0123456789", "a".repeat(31)));
    }

    @Test
    void settersModifyEachEditableField() {
        Contact contact = sample();
        contact.setFirstName("Jordan");
        contact.setLastName("Lee");
        contact.setPhone("9876543210");
        contact.setAddress("2 Oak Lane");
        assertAll(
                () -> assertEquals("Jordan", contact.getFirstName()),
                () -> assertEquals("Lee", contact.getLastName()),
                () -> assertEquals("9876543210", contact.getPhone()),
                () -> assertEquals("2 Oak Lane", contact.getAddress()));
    }

    @Test
    void settersRejectInvalidUpdatesAndKeepExistingData() {
        Contact contact = sample();
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("a".repeat(11)));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("b".repeat(11)));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("abc1234567"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("x".repeat(31)));
        assertAll(
                () -> assertEquals("Alex", contact.getFirstName()),
                () -> assertEquals("Morgan", contact.getLastName()),
                () -> assertEquals("1234567890", contact.getPhone()),
                () -> assertEquals("1 Main Street", contact.getAddress()));
    }
}
