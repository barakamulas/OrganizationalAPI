package models.dao;

import models.Article;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oArticleDao implements ArticleDao {

    private final Sql2o sql2o;
    public Sql2oArticleDao(Sql2o sql2o){ this.sql2o = sql2o; }

    @Override
    public void add(Article article) {
        String sql = "INSERT INTO articles (title, content) VALUES (:title, :content)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(article)
                    .executeUpdate()
                    .getKey();
            article.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Article> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM articles")
                    .executeAndFetch(Article.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from articles WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

//    @Override
//    public void deleteById(int id) {
//        String sql = "DELETE from articles WHERE id=:id"; //raw sql
//        String joinSql = "DELETE from restaurants_articles WHERE articleId = :articleId";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("id", id)
//                    .executeUpdate();
//            con.createQuery(joinSql)
//                    .addParameter("articleId", id)
//                    .executeUpdate();
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//    }

    @Override
    public void clearAll() {
        String sql = "DELETE from articles";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }





//    @Override
//    public void addArticleToRestaurant(Article article, Restaurant restaurant){
//        String sql = "INSERT INTO restaurants_articles (restaurantid, articleid) VALUES (:restaurantId, :articleId)";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("restaurantId", restaurant.getId())
//                    .addParameter("articleId", article.getId())
//                    .executeUpdate();
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//    }

//    @Override
//    public List<Restaurant> getAllRestaurantsForArticle(int articleId) {
//
//        ArrayList<Restaurant> restaurants = new ArrayList<>();
//
//        String joinQuery = "SELECT restaurantid FROM restaurants_articles WHERE articleid = :articleId";
//
//        try (Connection con = sql2o.open()) {
//            List<Integer> allRestaurantIds = con.createQuery(joinQuery)
//                    .addParameter("articleId", articleId)
//                    .executeAndFetch(Integer.class); //what is happening in the lines above?
//            for (Integer restaurantId : allRestaurantIds){
//                String restaurantQuery = "SELECT * FROM restaurants WHERE id = :restaurantId";
//                restaurants.add(
//                        con.createQuery(restaurantQuery)
//                                .addParameter("restaurantId", restaurantId)
//                                .executeAndFetchFirst(Restaurant.class));
//            } //why are we doing a second sql query - set?
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//        return restaurants;
//    }
}
