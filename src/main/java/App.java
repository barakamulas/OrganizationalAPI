import com.google.gson.Gson;
import models.Article;
import models.Department;
import models.Scopedarticle;
import models.User;
import models.dao.Sql2oArticleDao;
import models.dao.Sql2oDepartmentDao;
import models.dao.Sql2oScopedarticleDao;
import models.dao.Sql2oUserDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oScopedarticleDao scopedarticleDao;
        Sql2oArticleDao articleDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/organisationalapi.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        articleDao = new Sql2oArticleDao(sql2o);
        scopedarticleDao = new Sql2oScopedarticleDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();


        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);;
            return gson.toJson(department);
        });

        //READ
        get("/departments", "application/json", (req, res) -> {
            return gson.toJson(departmentDao.getAll());
        });

        post("/users/new", "application/json", (req, res) -> {
            User user  = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);;
            return gson.toJson(user);
        });

        //READ
        get("/articles", "application/json", (req, res) -> {
            return gson.toJson(articleDao.getAll());
        });

        post("/articles/new", "application/json", (req, res) -> {
            Article article  = gson.fromJson(req.body(), Article.class);
            articleDao.add(article);
            res.status(201);;
            return gson.toJson(article);
        });


        get("/articles", "application/json", (req, res) -> {
            return gson.toJson(articleDao.getAll());
        });

        post("/scopedscopedarticles/new", "application/json", (req, res) -> {
            Scopedarticle scopedarticle  = gson.fromJson(req.body(), Scopedarticle.class);
            scopedarticleDao.add(scopedarticle);
            res.status(201);;
            return gson.toJson(scopedarticle);
        });

        get("/scopedarticles", "application/json", (req, res) -> {
            return gson.toJson(scopedarticleDao.getAll());
        });







        after((req, res) ->{
            res.type("application/json");
        });
    }
}
