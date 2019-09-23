package models;

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
}
