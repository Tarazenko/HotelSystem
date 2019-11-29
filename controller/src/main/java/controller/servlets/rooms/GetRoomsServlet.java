package controller.servlets.rooms;

import com.google.gson.Gson;
import repository.RoomRepository;
import repository.implementations.RoomRepositoryImpl;
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
import java.util.List;

@WebServlet("/room/getAll")
public class GetRoomsServlet extends HttpServlet {
    private static RoomService roomService = new RoomServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<RoomDTO> rooms = roomService.getAll();
        String json = new Gson().toJson(rooms);
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}
