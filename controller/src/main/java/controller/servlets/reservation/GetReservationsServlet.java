package controller.servlets.reservation;

import com.google.gson.Gson;
import services.GuestService;
import services.ReservationService;
import services.implementations.GuestServiceImpl;
import services.implementations.ReservationServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/reservation/getAll")
public class GetReservationsServlet extends HttpServlet {
    private static ReservationService reservationService = new ReservationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = new Gson().toJson(reservationService.getAll());
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}
