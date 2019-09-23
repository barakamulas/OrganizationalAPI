package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScopedarticleTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContent() {
        Scopedarticle testScopedarticle = setupScopedarticle();
        assertEquals("Kirubi has just bought all the Apartments in Kileleshwa", testScopedarticle.getContent());
    }

    @Test
    public void setContent() {
        Scopedarticle testScopedarticle = setupScopedarticle();
        testScopedarticle.setContent("Uhuru sells his Brookside shares");
        assertNotEquals("Kirubi has just bought all the Apartments in Kileleshwa", testScopedarticle.getContent());
    }


    @Test
    public void getTitle() {
        Scopedarticle testScopedarticle = setupScopedarticle();
        assertEquals("Business Daily", testScopedarticle.getTitle());
    }

    @Test
    public void setTitle() {
        Scopedarticle testScopedarticle = setupScopedarticle();
        testScopedarticle.setTitle("Corporate News");
        assertNotEquals("Business Daily", testScopedarticle.getTitle());
    }

    @Test
    public void getDepartment_id() {
        Scopedarticle testScopedarticle = setupScopedarticle();
        assertEquals(1, testScopedarticle.getDepartment_id());
    }

    @Test
    public void setDepartment_id() {
        Scopedarticle testScopedarticle = setupScopedarticle();
        testScopedarticle.setDepartment_id(2);
        assertNotEquals(1, testScopedarticle.getDepartment_id());
    }

    @Test
    public void setId() {
        Scopedarticle testScopedarticle = setupScopedarticle();
        testScopedarticle.setId(5);
        assertEquals(5, testScopedarticle.getId());
    }


    public Scopedarticle setupScopedarticle(){
        return new Scopedarticle("Business Daily","Kirubi has just bought all the Apartments in Kileleshwa",1);
    }

}