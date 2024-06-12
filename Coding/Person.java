// Enumeration Class for Day
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// Abstract Class for Person
public abstract class Person {
    private String name;
    private String username;
    private String password;
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

    public void setUsername(String u) {
        this.username = u;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public String getPassword() {
        return password;
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
        return "Name: " + name + ", \nEmail Address: " + email + ", \nPhone Number: " + phoneNumber + "\nUsername: " + username + "\n";
    }
}