package pl.library.dto;

public final class UserView {

    private final String name;

    private final String lastName;

    private final String email;

    public UserView(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
