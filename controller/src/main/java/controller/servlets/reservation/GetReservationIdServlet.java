package controller.servlets.reservation;

import com.google.gson.Gson;
import controller.servlets.Error;
import services.ReservationService;
import services.dto.ReservationDTO;
import services.implementations.GuestServiceImpl;
import services.implementations.ReservationServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reservation/getId")
public class GetReservationIdServlet  extends HttpServlet {
    private static ReservationService reservationService = new ReservationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = request.getReader().readLine();
        ReservationDTO reservation = new Gson().fromJson(json, ReservationDTO.class);
        int id = reservationService.getId(reservation);
        if (id != -1) {
            json = new Gson().toJson(id);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        } else {
            Error error = new Error("No such reservation",
                    "No object database");
            json = new Gson().toJson(error);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
    }
}
