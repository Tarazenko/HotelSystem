package controller.servlets.rooms;

import com.google.gson.Gson;
import entity.Room;
import services.RoomService;
import services.dto.FeatureNamesDTO;
import services.implementations.RoomServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/room/getByParams")
public class RoomByParamsServlet extends HttpServlet {
    private static RoomService roomService = new RoomServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = request.getReader().readLine();
        LocalDate dateIn = LocalDate.parse(request.getParameter("datein"));
        LocalDate dateOut = LocalDate.parse(request.getParameter("dateout"));
        FeatureNamesDTO features = new Gson().fromJson(json, FeatureNamesDTO.class);
        json = new Gson().toJson(roomService.findRoomByParams(features,dateIn,dateOut));
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}