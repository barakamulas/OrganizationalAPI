package models.dao;

import models.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oArticleDaoTest {

    private Sql2oArticleDao articleDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        articleDao = new Sql2oArticleDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingFoodSetsId() throws Exception {
        Article testArticle = setupNewArticle();
        int originalArticleId = testArticle.getId();
        articleDao.add(testArticle);
        assertNotEquals(originalArticleId, testArticle.getId());
    }

    @Test
    public void addedArticlesAreReturnedFromGetAll() throws Exception {
        Article testArticle = setupNewArticle();
        articleDao.add(testArticle);
        assertEquals(1, articleDao.getAll().size());
    }

    @Test
    public void noArticlesReturnsEmptyList() throws Exception {
        assertEquals(0, articleDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectArticle() throws Exception {
        Article article = setupNewArticle();
        articleDao.add(article);
        articleDao.deleteById(article.getId());
        assertEquals(0, articleDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Article testArticle = setupNewArticle();
        Article otherArticle = setupNewArticle();
        articleDao.clearAll();
        assertEquals(0, articleDao.getAll().size());
    }

//    @Test
//    public void addFoodTypeToRestaurantAddsTypeCorrectly() throws Exception {
//
//        Restaurant testRestaurant = setupRestaurant();
//        Restaurant altRestaurant = setupAltRestaurant();
//
//        restaurantDao.add(testRestaurant);
//        restaurantDao.add(altRestaurant);
//
//        Article testArticle = setupNewArticle();
//
//        articleDao.add(testArticle);
//
//        articleDao.addArticleToRestaurant(testArticle, testRestaurant);
//        articleDao.addArticleToRestaurant(testArticle, altRestaurant);
//
//        assertEquals(2, articleDao.getAllRestaurantsForAArticle(testArticle.getId()).size());
//    }
//
//    @Test
//    public void deleteingFoodTypeAlsoUpdatesJoinTable() throws Exception {
//        Article testArticle  = setupNewArticle();
//        articleDao.add(testArticle);
//
//        Article altTestArticle  = new Article("Seafood");
//        articleDao.add(altTestArticle);
//
//        Restaurant testRestaurant = setupRestaurant();
//        restaurantDao.add(testRestaurant);
//
//        Restaurant altRestaurant = setupAltRestaurant();
//        restaurantDao.add(altRestaurant);
//
//        articleDao.addArticleToRestaurant(testArticle,testRestaurant);
//        articleDao.addArticleToRestaurant(altTestArticle, altRestaurant);
//
//        articleDao.deleteById(testArticle.getId());
//        assertEquals(0, articleDao.getAllRestaurantsForAArticle(testArticle.getId()).size());
//    }

    // helpers

    public Article setupNewArticle() {
        return new Article("Ballon D'or A Scam","In a controversial manner Messi pipped Van Dijk to win the Ballon D'or for the sixth time");
    }
//    public Restaurant setupAltRestaurant (){
//        Restaurant restaurant = new Restaurant("Fish Omena", "214 NE Ngara", "97232", "254-402-9874");
//        restaurantDao.add(restaurant);
//        return restaurant;
//    }
//
//    public Restaurant setupRestaurant (){
//        Restaurant restaurant = new Restaurant("Fish Omena", "214 NE Ngara", "97232", "254-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com");
//        restaurantDao.add(restaurant);
//        return restaurant;
//    }


}