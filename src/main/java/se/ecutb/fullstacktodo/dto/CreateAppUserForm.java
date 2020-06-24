package se.ecutb.fullstacktodo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateAppUserForm {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 2, max = 255, message = "Username need to have 2 or more letters")
    private String username;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 255, message = "First name need to have 2 or more letters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 255, message = "Last name need to have 2 or more letters")
    private String lastName;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$", message = "Must contain at least one letter, one number, and be longer than six characters.")
    private String password;

    @NotBlank(message = "Please confirm your password")
    private String passwordConfirm;

    boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}