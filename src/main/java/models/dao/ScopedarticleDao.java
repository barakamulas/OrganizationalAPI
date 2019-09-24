package models.dao;

import models.Department;
import models.Scopedarticle;

import java.util.List;

public interface ScopedarticleDao {

    void add(Scopedarticle scopedarticle);
    void addScopedarticleToDepartment(Scopedarticle scopedarticle, Department department);
    List<Department> getAllDepartmentsForAScopedarticle(int id);

    List<Scopedarticle> getAll();


    //update
    //omit for now


    void deleteById(int id);
    void clearAll();
}
