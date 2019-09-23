package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContent() {
        Article testArticle = setupArticle();
        assertEquals("Kirubi has just bought all the Apartments in Kileleshwa", testArticle.getContent());
    }

    @Test
    public void setContent() {
        Article testArticle = setupArticle();
        testArticle.setContent("Uhuru sells his Brookside shares");
        assertNotEquals("Kirubi has just bought all the Apartments in Kileleshwa", testArticle.getContent());
    }


    @Test
    public void getTitle() {
        Article testArticle = setupArticle();
        assertEquals("Business Daily", testArticle.getTitle());
    }

    @Test
    public void setTitle() {
        Article testArticle = setupArticle();
        testArticle.setTitle("Corporate News");
        assertNotEquals("Business Daily", testArticle.getTitle());
    }

    @Test
    public void setId() {
        Article testArticle = setupArticle();
        testArticle.setId(5);
        assertEquals(5, testArticle.getId());
    }

    // helper
    public Article setupArticle(){
        return new Article("Business Daily","Kirubi has just bought all the Apartments in Kileleshwa");
    }


}