package models.dao;

import models.Department;
import models.Scopedarticle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oScopedarticleDaoTest {

    private Sql2oScopedarticleDao scopedarticleDao;
    private Sql2oDepartmentDao departmentDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        scopedarticleDao = new Sql2oScopedarticleDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingScopedarticleSetsId() throws Exception {
        Scopedarticle testScopedarticle = setupNewScopedarticle();
        int originalScopedarticleId = testScopedarticle.getId();
        scopedarticleDao.add(testScopedarticle);
        assertNotEquals(originalScopedarticleId, testScopedarticle.getId());
    }

    @Test
    public void addedScopedarticlesAreReturnedFromGetAll() throws Exception {
        Scopedarticle testScopedarticle = setupNewScopedarticle();
        scopedarticleDao.add(testScopedarticle);
        assertEquals(1, scopedarticleDao.getAll().size());
    }

    @Test
    public void noScopedarticlesReturnsEmptyList() throws Exception {
        assertEquals(0, scopedarticleDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectScopedarticle() throws Exception {
        Scopedarticle scopedarticle = setupNewScopedarticle();
        scopedarticleDao.add(scopedarticle);
        scopedarticleDao.deleteById(scopedarticle.getId());
        assertEquals(0, scopedarticleDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Scopedarticle testScopedarticle = setupNewScopedarticle();
        Scopedarticle otherScopedarticle = setupNewScopedarticle();
        scopedarticleDao.clearAll();
        assertEquals(0, scopedarticleDao.getAll().size());
    }

//    @Test
//    public void addScopedarticleTypeToDepartmentAddsTypeCorrectly() throws Exception {
//
//        Department testDepartment = setupDepartment();
//        Department altDepartment = setupAltDepartment();
//
//        departmentDao.add(testDepartment);
//        departmentDao.add(altDepartment);
//
//        Scopedarticle testScopedarticle = setupNewScopedarticle();
//
//        scopedarticleDao.add(testScopedarticle);
//
//        scopedarticleDao.addScopedarticleToDepartment(testScopedarticle, testDepartment);
//        scopedarticleDao.addScopedarticleToDepartment(testScopedarticle, altDepartment);
//
//        assertEquals(2, scopedarticleDao.getAllDepartmentsForAScopedarticle(testScopedarticle.getId()).size());
//    }





    public Scopedarticle setupNewScopedarticle() {
        return new Scopedarticle("Ballon D'or A Scam","In a controversial manner Messi pipped Van Dijk to win the Ballon D'or for the sixth time",1);
    }
//    public Department setupAltDepartment (){
//        Department department = new Department("Sports", "Handles the sports segment", 10);
//        departmentDao.add(department);
//        return department;
//    }
//
//    public Department setupDepartment (){
//        Department department = new Department("Business", "Keeping track of business related news", 6);
//        departmentDao.add(department);
//        return department;
//    }

}