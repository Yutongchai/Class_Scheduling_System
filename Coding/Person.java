// Abstract Class for Person
public abstract class Person {
    private String type;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public Person(String name, String username, String password, String email, String phoneNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Person(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }


    public String getName() {
        return name;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDetails() {
        return "Name: " + name + ", \nEmail Address: " + email + ", \nPhone Number: " + phoneNumber + "\nUsername: "
                + username + "\n";
    }
}