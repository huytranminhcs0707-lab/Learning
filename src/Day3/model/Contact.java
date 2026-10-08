package Day3.model;

public class Contact {

    private String name;
    private String phoneNumber;
    private String email;

    public Contact(
            String name,
            String phoneNumber,
            String email
    ) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (phoneNumber == null || phoneNumber.isBlank()){
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be null");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null");
        }

        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be null");
        }

        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}