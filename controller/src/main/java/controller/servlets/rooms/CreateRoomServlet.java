package controller.servlets.rooms;

import com.google.gson.Gson;
import entity.Guest;
import entity.Room;
import repository.RoomRepository;
import repository.implementations.RoomRepositoryImpl;
import services.RoomService;
import services.dto.RoomDTO;
import services.implementations.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/room/create")
public class CreateRoomServlet extends HttpServlet {
    private static RoomService roomService = new RoomServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().readLine();
        RoomDTO room = new Gson().fromJson(json, RoomDTO.class);
        roomService.createRoom(room);
        json = new Gson().toJson(roomService.getRoomByNumber(room.getNumber()));
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}
