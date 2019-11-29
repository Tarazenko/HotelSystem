package controller.servlets.rooms;

import com.google.gson.Gson;
import services.RoomService;
import services.implementations.RoomServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/room/getId")
public class GetRoomIdServlet extends HttpServlet {
    private static RoomService roomService = new RoomServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int number = Integer.parseInt(request.getParameter("number"));
        String json = new Gson().toJson(roomService.getId(number));
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}