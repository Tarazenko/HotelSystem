package controller.servlets.guest;

import com.google.gson.Gson;
import entity.Guest;
import services.PassportService;
import services.dto.GuestDTO;
import services.implementations.GuestServiceImpl;
import services.implementations.PassportServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guest/get")
public class GetGuestServlet extends HttpServlet {
    private static GuestServiceImpl guestService = new GuestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        GuestDTO guest = guestService.getById(Integer.parseInt(request.getParameter("id")));
        String json = new Gson().toJson(guest);
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}
