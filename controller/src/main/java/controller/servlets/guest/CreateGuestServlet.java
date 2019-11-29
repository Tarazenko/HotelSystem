package controller.servlets.guest;

import com.google.gson.Gson;
import entity.Guest;
import entity.Passport;
import services.GuestService;
import services.PassportService;
import services.dto.GuestDTO;
import services.implementations.GuestServiceImpl;
import services.implementations.PassportServiceImpl;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/guest/create")
public class CreateGuestServlet extends HttpServlet {
    private static GuestServiceImpl guestService = new GuestServiceImpl();
    private static PassportService passportService = new PassportServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = request.getReader().readLine();
        GuestDTO guest = new Gson().fromJson(json, GuestDTO.class);
        guestService.create(guest);
    }
}
