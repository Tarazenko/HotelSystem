package controller.servlets.guest;

import com.google.gson.Gson;
import controller.servlets.Error;
import entity.Guest;
import entity.Passport;
import services.GuestService;
import services.dto.GuestDTO;
import services.implementations.GuestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guest/delete")
public class DeleteGuestServlet extends HttpServlet {
    private static GuestService guestService = new GuestServiceImpl();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        GuestDTO guest = guestService.getById(Integer.parseInt(request.getParameter("id")));
        if (guest != null) {
            guestService.delete(Integer.parseInt(request.getParameter("id")));
            String json = new Gson().toJson(guest);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
        else {
            Error error = new Error("No such element",
                    "No object database");
            String json = new Gson().toJson(error);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
    }
}
