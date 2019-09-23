package models;

import java.util.Objects;

public class Department {

    private int id;
    private String d_name;
    private String description;
    private int no_of_employees;

    public Department(String d_name, String description, int no_of_employees) {
        this.d_name = d_name;
        this.description = description;
        this.no_of_employees = no_of_employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNo_of_employees() {
        return no_of_employees;
    }

    public void setNo_of_employees(int no_of_employees) {
        this.no_of_employees = no_of_employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return getNo_of_employees() == that.getNo_of_employees() &&
                getD_name().equals(that.getD_name()) &&
                getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getD_name(), getDescription(), getNo_of_employees());
    }
}
