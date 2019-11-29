package controller.servlets.rooms;

import com.google.gson.Gson;
import controller.servlets.Error;
import entity.Guest;
import entity.Room;
import services.GuestService;
import services.RoomService;
import services.dto.RoomDTO;
import services.implementations.GuestServiceImpl;
import services.implementations.RoomServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/room/delete")
public class DeleteRoomServlet extends HttpServlet {
    private static RoomService roomService = new RoomServiceImpl();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        RoomDTO room = roomService.getRoomById(Integer.parseInt(request.getParameter("id")));
        if (room != null) {
            roomService.removeRoom(Integer.parseInt(request.getParameter("id")));
            String json = new Gson().toJson(room);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
        else {
            Error error = new Error("No such room",
                    "No object database or error with connection to database");
            String json = new Gson().toJson(error);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
    }
}
