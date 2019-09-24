package models.dao;

import models.Department;
import models.Department;
import models.Scopedarticle;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);
   

    Department findById(int id);

    List<Department> getAll();
    void addDepartmentToScopedarticle(Department department, Scopedarticle scopedarticle);
    List<Scopedarticle> getAllScopedarticlesForADepartment(int departmentId);

    //update


    void deleteById(int id);
    void clearAll();
}
