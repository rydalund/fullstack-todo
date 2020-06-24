package se.ecutb.fullstacktodo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate regDate;
    private String password;

    @OneToMany(mappedBy = "username")
    private List<TodoItem> todoItems = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)

    @JoinTable(name = "app_user_user_role", joinColumns = @JoinColumn(name = "app_user"), inverseJoinColumns = @JoinColumn(name = "user_role"))
    Set<AppUserRole> roleSet;


    public AppUser(String username, String firstName, String lastName, LocalDate regDate, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regDate = regDate;
        this.password = password;
    }
    public AppUser(String username,LocalDate regDate, String password) {
        this.username = username;
        this.regDate = regDate;
        this.password = password;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public AppUser() {
    }

    public int getUserId() {
        return userId;
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

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppUserRole> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<AppUserRole> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return userId == appUser.userId &&
                Objects.equals(username, appUser.username) &&
                Objects.equals(firstName, appUser.firstName) &&
                Objects.equals(lastName, appUser.lastName) &&
                Objects.equals(regDate, appUser.regDate) &&
                Objects.equals(password, appUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, firstName, lastName, regDate, password);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}

