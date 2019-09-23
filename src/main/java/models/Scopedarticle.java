package models;

import java.util.Objects;

public class Scopedarticle extends Article {


    private int department_id;

    public Scopedarticle(String title, String content, int department_id) {
        super(title, content);
        this.department_id = department_id;
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
        if (!super.equals(o)) return false;
        Scopedarticle that = (Scopedarticle) o;
        return getDepartment_id() == that.getDepartment_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDepartment_id());
    }
}
