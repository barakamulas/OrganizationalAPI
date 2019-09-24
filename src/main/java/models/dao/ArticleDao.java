package models.dao;

import models.Article;

import java.util.List;

public interface ArticleDao {


    void add(Article article);

    List<Article> getAll();


    //update
    //omit for now

    void deleteById(int id);
    void clearAll();
}
