package controller.servlets.guest;

import com.google.gson.Gson;
import controller.servlets.Error;
import services.dto.GuestDTO;
import services.implementations.GuestServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guest/getId")
public class GetIdServlet extends HttpServlet {
    private static GuestServiceImpl guestService = new GuestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = guestService.getId(request.getParameter("phone"));
        if (id != -1) {
            String json = new Gson().toJson(id);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        } else {
            Error error = new Error("No guest with such phone",
                    "No object database");
            String json = new Gson().toJson(error);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
    }
}
