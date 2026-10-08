package Day3.service;

import Day3.exception.DuplicateContactException;
import Day3.model.Contact;

import java.util.*;

public class ContactManager {
    private final Map<String, Contact> contacts;

    public ContactManager() {
        contacts = new HashMap<>();
    }

    public void addContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("Contact cannot be null");
        }
        String phoneNumber = contact.getPhoneNumber();
        if (contacts.containsKey(phoneNumber)){
            throw new DuplicateContactException("This phonenumber has already in contacts");
        }
        contacts.put(phoneNumber, contact);
    }

    public Contact findContactByPhone(
            String phoneNumber
    ) {
        if (phoneNumber == null || phoneNumber.isBlank()){
            throw new IllegalArgumentException("Phonenumber cannot be null");
        }
        return contacts.get(phoneNumber);
    }

    public void updateContact(
            String phoneNumber,
            String newName,
            String newEmail
    ) {
        Contact contact = contacts.get(phoneNumber);
        if (contact == null){
            throw new IllegalArgumentException("Cannot find contact by this phone number");
        }
        contact.setName(newName);
        contact.setEmail(newEmail);
    }

    public Contact deleteContact(String phoneNumber) {
        Contact contact = contacts.remove(phoneNumber);
        return contact;
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(
                contacts.values()
        );
    }

    public List<Contact> getContactsSortedByName(){
        List<Contact> res = getAllContacts();
        Comparator<Contact> byName = Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER);
        res.sort(byName);
        return res;
    }

}
