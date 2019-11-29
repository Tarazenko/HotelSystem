package controller.servlets;

import com.google.gson.Gson;
import entity.Attendance;
import services.AttendanceService;
import services.implementations.AttendanceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/attendance/create")
public class CreateAttendanceServlet extends HttpServlet {
    private static AttendanceService attendanceService = new AttendanceServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = request.getReader().readLine();
        Attendance attendance = new Gson().fromJson(json, Attendance.class);
        attendanceService.create(attendance);
    }
}
