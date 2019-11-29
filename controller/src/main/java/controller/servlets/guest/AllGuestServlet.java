package controller.servlets.guest;

import com.google.gson.Gson;
import entity.Guest;
import services.GuestService;
import services.implementations.GuestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/guest/getAll")
public class AllGuestServlet extends HttpServlet {
    private static GuestService guestService = new GuestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = new Gson().toJson(guestService.getAll());
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}
