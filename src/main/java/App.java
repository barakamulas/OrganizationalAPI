import com.google.gson.Gson;
import exceptions.ApiException;
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

        get("/departments/:id", "application/json", (req, res) -> {
            return gson.toJson(departmentDao.findById(Integer.parseInt(req.params("id"))));
        });

        post("/departments/:departmentId/scopedarticles/:scopedarticleId", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            int scopedarticleId = Integer.parseInt(req.params("scopedarticleId"));
            Department department = departmentDao.findById(departmentId);
            Scopedarticle scopedarticle = scopedarticleDao.findById(scopedarticleId);

            if (department != null && scopedarticle != null){
                scopedarticleDao.addScopedarticleToDepartment(scopedarticle, department);
                res.status(201);
                return gson.toJson(String.format("Department '%s' and Scopedarticle '%s' have been associated",scopedarticle.getName(), department.getName()));
            }
            else {
                throw new ApiException(404, String.format("Department or Scopedarticle does not exist"));
            }
        });

        get("/departments/:id/scopedarticles", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            else if (departmentDao.getAllScopedarticlesForADepartment(departmentId).size()==0){
                return "{\"message\":\"I'm sorry, but no scopedarticles are listed for this department.\"}";
            }
            else {
                return gson.toJson(departmentDao.getAllScopedarticlesForADepartment(departmentId));
            }
        });

        get("/scopedarticles/:id/departments", "application/json", (req, res) -> {
            int scopedarticleId = Integer.parseInt(req.params("id"));
            Scopedarticle scopedarticleToFind = scopedarticleDao.findById(scopedarticleId);
            if (scopedarticleToFind == null){
                throw new ApiException(404, String.format("No scopedarticle with the id: \"%s\" exists", req.params("id")));
            }
            else if (scopedarticleDao.getAllDepartmentsForAScopedarticle(scopedarticleId).size()==0){
                return "{\"message\":\"I'm sorry, but no departments are listed for this scopedarticle.\"}";
            }
            else {
                return gson.toJson(scopedarticleDao.getAllDepartmentsForAScopedarticle(scopedarticleId));
            }
        });

        post("/users/new", "application/json", (req, res) -> {
            User user  = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
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

        get("/articles/:id", "application/json", (req, res) -> {
            return gson.toJson(articleDao.findById(Integer.parseInt(req.params("id"))));
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

        get("/scopedarticles/:id", "application/json", (req, res) -> {
            return gson.toJson(scopedarticleDao.findById(Integer.parseInt(req.params("id"))));
        });


        after((req, res) ->{
            res.type("application/json");
        });
    }
}
