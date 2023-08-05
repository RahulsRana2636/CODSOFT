import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {

        contacts.remove(contact);
    }

    public Contact searchContact(int phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber() == phoneNumber) {
                return contact;
            }
        }
        return null; // Contact not found
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    // You can add methods for reading and writing contacts from/to a file or
    // database.
}
