package models.dao;

import models.Department;
import models.Scopedarticle;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oScopedarticleDao implements ScopedarticleDao {

    private final Sql2o sql2o;
    public Sql2oScopedarticleDao(Sql2o sql2o){ this.sql2o = sql2o; }

    @Override
    public void add(Scopedarticle scopedarticle) {
        String sql = "INSERT INTO scoped_articles (title, content) VALUES (:title, :content)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(scopedarticle)
                    .executeUpdate()
                    .getKey();
            scopedarticle.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Scopedarticle> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM scoped_articles")
                    .executeAndFetch(Scopedarticle.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from scoped_articles WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from scoped_articles";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addScopedarticleToDepartment(Scopedarticle scopedarticle, Department department){
        String sql = "INSERT INTO departments_scopedarticles (department_id, scopedarticle_id) VALUES (:department_id, :scopedarticle_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("department_id", department.getId())
                    .addParameter("scopedarticle_id", scopedarticle.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Department> getAllDepartmentsForAScopedarticle(int scopedarticle_id) {

        ArrayList<Department> departments = new ArrayList<>();

        String joinQuery = "SELECT department_id FROM departments_scopedarticles WHERE scopedarticle_id = :scopedarticle_id";

        try (Connection con = sql2o.open()) {
            List<Integer> allDepartment_ids = con.createQuery(joinQuery)
                    .addParameter("scopedarticle_id", scopedarticle_id)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer department_id : allDepartment_ids){
                String departmentQuery = "SELECT * FROM departments WHERE id = :department_id";
                departments.add(
                        con.createQuery(departmentQuery)
                                .addParameter("department_id", department_id)
                                .executeAndFetchFirst(Department.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }


}
