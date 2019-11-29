package controller.servlets.reservation;

import com.google.gson.Gson;
import controller.servlets.Error;
import repository.ReservationRepository;
import repository.implementations.ReservationRepositoryImpl;
import services.GuestService;
import services.ReservationService;
import services.dto.GuestDTO;
import services.dto.ReservationDTO;
import services.implementations.GuestServiceImpl;
import services.implementations.ReservationServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/reservation/delete")
public class ReservationDelete extends HttpServlet {
    private static ReservationService reservationService = new ReservationServiceImpl();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ReservationDTO reservation = reservationService.getById(Integer.parseInt(request.getParameter("id")));
        if (reservation != null) {
            reservationService.delete(Integer.parseInt(request.getParameter("id")));
            String json = new Gson().toJson(reservation);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        } else {
            Error error = new Error("No such reservation",
                    "No object database");
            String json = new Gson().toJson(error);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
    }
}

