package models.dao;

import models.Department;
import models.Department;
import models.Scopedarticle;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);
   


    List<Department> getAll();
    void addDepartmentToScopedarticle(Department department, Scopedarticle scopedarticle);
    List<Scopedarticle> getAllScopedarticlesForADepartment(int departmentId);

    //update
    //omit for now




    void deleteById(int id);
    void clearAll();
}
