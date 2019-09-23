package models.dao;

import models.Department;
import models.Department;
import models.Scopedarticle;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);


    List<Department> getAll();

    //update
    //omit for now

//    List<Scopedarticle> getAllScopedarticlesForDepartment(Department department);


    void deleteById(int id);
    void clearAll();
}
