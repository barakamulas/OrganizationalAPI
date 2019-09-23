package models.dao;

import models.User;

import java.util.List;

public interface UserDao {


    void add(User article);

    List<User> getAll();

    //update
    //omit for now

    void deleteById(int id);
    void clearAll();
}
