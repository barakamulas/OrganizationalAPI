package models.dao;

import models.Department;
import models.Scopedarticle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {


    private Sql2oScopedarticleDao scopedarticleDao;
    private Sql2oDepartmentDao departmentDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        scopedarticleDao = new Sql2oScopedarticleDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingDepartmentSetsId() throws Exception {
        Department testDepartment = setupDepartment();
        int originalDepartmentId = testDepartment.getId();
        departmentDao.add(testDepartment);
        assertNotEquals(originalDepartmentId, testDepartment.getId());
    }

    @Test
    public void addedDepartmentsAreReturnedFromGetAll() throws Exception {
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void noDepartmentsReturnsEmptyList() throws Exception {
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectDepartment() throws Exception {
        Department department = setupDepartment();
        departmentDao.add(department);
        departmentDao.deleteById(department.getId());
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupAltDepartment();
        departmentDao.add(testDepartment);
        departmentDao.add(otherDepartment);
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }
    @Test
    public void DepartmentReturnsScopedarticlesCorrectly() throws Exception {
        Scopedarticle testScopedarticle  = new Scopedarticle("Seafood","The best in the world");
        scopedarticleDao.add(testScopedarticle);
        Scopedarticle otherScopedarticle  = new Scopedarticle("Bar Food", "Nyama choma and beer");
        scopedarticleDao.add(otherScopedarticle);
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        departmentDao.addDepartmentToScopedarticle(testDepartment,testScopedarticle);
        departmentDao.addDepartmentToScopedarticle(testDepartment,otherScopedarticle);
        assertEquals(2, departmentDao.getAllScopedarticlesForADepartment(testDepartment.getId()).size());
    }



    @Test
    public void deleteingDepartmentAlsoUpdatesJoinTable() throws Exception {
        Scopedarticle testScopedarticle  = new Scopedarticle("Seafood", "the best in the world");
        scopedarticleDao.add(testScopedarticle);
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        Department altDepartment = setupAltDepartment();
        departmentDao.add(altDepartment);
        departmentDao.addDepartmentToScopedarticle(testDepartment,testScopedarticle);
        departmentDao.addDepartmentToScopedarticle(altDepartment, testScopedarticle);
        departmentDao.deleteById(testDepartment.getId());
        assertEquals(0, departmentDao.getAllScopedarticlesForADepartment(testDepartment.getId()).size());
    }


    public Department setupAltDepartment (){
        Department department = new Department("Sports", "Handles the sports segment", 10);
        return department;
    }

    public Department setupDepartment (){
        Department department = new Department("Business", "Keeping track of business related news", 6);
        return department;
    }

}