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

@WebServlet("/attendace/getAll")
public class AllAttendanceServlet extends HttpServlet {
    private static AttendanceService attendanceService = new AttendanceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Attendance> attendances;
        attendances = attendanceService.getAll();
        System.out.println(attendances);
        String json = new Gson().toJson(attendances);
        System.out.println(json);
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}
