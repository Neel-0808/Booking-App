import java.util.Scanner;
public abstract class User {
    private String name;
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.name = ""; // Default empty name
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public abstract void provideFeedback(Scanner scanner);
}
