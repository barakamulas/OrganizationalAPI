package models;

import java.util.Objects;

public class User {

    private int id;
    private String u_name;
    private String role;
    private int department_id;

    public User(String u_name, String role, int department_id) {
        this.u_name = u_name;
        this.role = role;
        this.department_id = department_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getDepartment_id() == user.getDepartment_id() &&
                getU_name().equals(user.getU_name()) &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getU_name(), getRole(), getDepartment_id());
    }
}
