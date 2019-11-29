package controller.servlets.guest;

import com.google.gson.Gson;
import controller.servlets.Error;
import entity.Attendance;
import services.AttendanceService;
import services.GuestService;
import services.implementations.AttendanceServiceImpl;
import services.implementations.GuestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guest/addAttendance")
public class AddGuestAttendance extends HttpServlet {
    private static GuestService guestService = new GuestServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        if(!guestService.addAttendance(id, name)){
            Error error = new Error("No such guest or attendance",
                    "No object in database");
            String json = new Gson().toJson(error);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        }
    }
}
