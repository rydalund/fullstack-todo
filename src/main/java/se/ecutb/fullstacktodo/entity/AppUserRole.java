package se.ecutb.fullstacktodo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AppUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    @Column(unique = true)
    private Roles role;



    public AppUserRole(Roles role) {
        this.role = role;
    }

    public AppUserRole() {}


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserRole that = (AppUserRole) o;
        return roleId == that.roleId &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }

    @Override
    public String toString() {
        return "AppUserRole{" +
                "role='" + role + '\'' +
                '}';
    }
}

