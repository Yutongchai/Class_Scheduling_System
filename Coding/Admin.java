public class Admin extends Person {

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayRole() {
        System.out.println("\nRole: Admin");
    }

    public String getDetails() {
        return "";
    }

};
