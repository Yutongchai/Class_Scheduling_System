enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
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
    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }
    
    public void setEmail(String e) {
        this.email = e;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String pn) {
        this.phoneNumber = pn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDetails() {
        return "Name: " + name + ", \nEmail Address: " + email + ", \nPhone Number: " + phoneNumber;
    }
}