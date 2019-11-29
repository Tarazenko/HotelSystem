package controller.servlets.rooms;


import com.google.gson.Gson;
import controller.servlets.Error;
import services.GuestService;
import services.RoomService;
import services.implementations.GuestServiceImpl;
import services.implementations.RoomServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/room/addFeature")
public class AddFeatureServlet extends HttpServlet {
    private static RoomService roomService = new RoomServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        if(!roomService.addFeature(id, name)){
            Error error = new Error("No such room or feature",
                    "No object in database");
            String json = new Gson().toJson(error);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
    }
}
