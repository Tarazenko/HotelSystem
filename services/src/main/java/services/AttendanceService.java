package services;

import entity.Attendance;

public interface AttendanceService extends Service<Attendance> {
    Attendance getByName(String name);
}
