enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN
}

// Abstract Class for Person
public abstract class Person {
    private String name;
    private String email;
    private String phoneNumber;

    public Person(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDetails() {
        return "Name: " + name + ", \nEmail Address: " + email + ", \nPhone Number: " + phoneNumber;
    }
}