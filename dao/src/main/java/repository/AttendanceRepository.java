package repository;

import entity.Attendance;

public interface AttendanceRepository extends Repository<Attendance> {
    Attendance getByName(String name);
}
