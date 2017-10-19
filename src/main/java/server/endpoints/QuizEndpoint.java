package server.endpoints;
import com.google.gson.Gson;
import server.controller.AdminController;
import server.controller.UserController;
import server.dbmanager.DbManager;
import server.models.Quiz;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/quiz")
public class QuizEndpoint {
    DbManager dbManager = new DbManager();
    AdminController adminController = new AdminController();
    UserController userController = new UserController();

    @GET
    @Path("/{CourseID}")
    public Response loadQuizzes(@PathParam("CourseID") int courseId){
        ArrayList<Quiz> quizzes = dbManager.loadQuizzes(courseId);

        return Response.status(200).entity(new Gson().toJson(quizzes)).build();

    }

    @POST
    public Response createQuiz(String quizJson) {
        Boolean quizCreated = adminController.createQuiz(quizJson);

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"quizCreated\":\"true\"}")
                .build();
    }
    @GET
    //
    @Path("{quizID}/{userID}")
    public Response getUserScore(@PathParam("quizID") int quizID, @PathParam("userID") int userID){
        //

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson( userController.getResult(quizID,userID)))
                .build();

    }

}
