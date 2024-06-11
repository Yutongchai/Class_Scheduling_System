enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN
}

// Abstract Class for Person
public abstract class Person {
    private String name;
    private String address;
    private String phoneNumber;

    public Person(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDetails() {
        return "Name: " + name + ", Address: " + address + ", Phone Number: " + phoneNumber;
    }
}