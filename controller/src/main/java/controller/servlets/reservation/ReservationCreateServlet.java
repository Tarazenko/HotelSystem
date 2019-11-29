package controller.servlets.reservation;

import com.google.gson.Gson;
import entity.Reservation;
import services.ReservationService;
import services.dto.ReservationDTO;
import services.implementations.ReservationServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/reservation/create")
public class ReservationCreateServlet extends HttpServlet {
    private static ReservationService reservationService = new ReservationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().readLine();
        ReservationDTO reservation = new Gson().fromJson(json, ReservationDTO.class);
        reservationService.create(reservation);
    }
}

