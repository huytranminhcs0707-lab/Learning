package Day3.app;

import Day3.model.Contact;
import Day3.service.ContactManager;

public class ContactApplication {
    public static void main(String[] args){
        ContactManager manager = new ContactManager();
        Contact contact1 = new Contact(
                "Huy",
                "0903000000",
                "huy@gmail.com"
        );

        Contact contact2 = new Contact(
                "An",
                "0902000000",
                "an@gmail.com"
        );

        Contact contact3 = new Contact(
                "Binh",
                "0901000000",
                "binh@gmail.com"
        );

        Contact contact4 = new Contact(
                "An",
                "0900000000",
                "an2@gmail.com"
        );

        manager.addContact(contact1);
        manager.addContact(contact2);
        manager.addContact(contact3);
        manager.addContact(contact4);
        System.out.println("===== SORT BY NAME =====");

        for (Contact contact
                : manager.getContactsSortedByName()) {
            System.out.println(contact);
        }
    }
}
