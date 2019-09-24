package models.dao;

import models.Department;
import models.Scopedarticle;

import java.util.List;

public interface ScopedarticleDao {

    void add(Scopedarticle scopedarticle);
    void addScopedarticleToDepartment(Scopedarticle scopedarticle, Department department);

    List<Scopedarticle> getAll();
    List<Department> getAllDepartmentsForAScopedarticle(int scopedarticle_id);

    //update
    //omit for now


    void deleteById(int id);
    void clearAll();
}
