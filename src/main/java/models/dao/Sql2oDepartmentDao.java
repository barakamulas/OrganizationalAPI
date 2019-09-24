package models.dao;

import models.Article;
import models.Department;
import models.Department;
import models.Scopedarticle;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {

    private final Sql2o sql2o;
    public Sql2oDepartmentDao(Sql2o sql2o){ this.sql2o = sql2o; }

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments (d_name, description, no_of_employees) VALUES (:d_name, :description, :no_of_employees)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Department findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<Department> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        String joinSql = "DELETE from departments_scopedarticles WHERE department_id = :department_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(joinSql)
                    .addParameter("department_id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void addDepartmentToScopedarticle(Department department, Scopedarticle scopedarticle){
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
    public List<Scopedarticle> getAllScopedarticlesForADepartment(int department_id) {

        ArrayList<Scopedarticle> scopedarticles = new ArrayList<>();

        String joinQuery = "SELECT scopedarticle_id FROM departments_scopedarticles WHERE department_id = :department_id";

        try (Connection con = sql2o.open()) {
            List<Integer> allScopedarticleIds = con.createQuery(joinQuery)
                    .addParameter("department_id", department_id)
                    .executeAndFetch(Integer.class);
            for (Integer scopedarticleId : allScopedarticleIds){
                String scopedarticleQuery = "SELECT * FROM scoped_articles WHERE id = :scopedarticle_id";
                scopedarticles.add(
                        con.createQuery(scopedarticleQuery)
                                .addParameter("scopedarticle_id", scopedarticleId)
                                .executeAndFetchFirst(Scopedarticle.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return scopedarticles;
    }
}
