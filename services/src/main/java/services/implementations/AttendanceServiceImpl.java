package services.implementations;

import entity.Attendance;
import repository.AttendanceRepository;
import repository.implementations.AttendanceRepositoryImpl;
import services.AttendanceService;

import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {
    private AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl() {
        attendanceRepository = new AttendanceRepositoryImpl();
    }

    @Override
    public Attendance getByName(String name) {
        return attendanceRepository.getByName(name);
    }

    @Override
    public boolean create(Attendance entity) {
        return attendanceRepository.create(entity);
    }

    @Override
    public boolean update(Attendance entity) {
        return false;
    }

    @Override
    public boolean delete(Attendance attendance) {
        return attendanceRepository.delete(attendance.getId());
    }

    @Override
    public boolean delete(int id) {
        return attendanceRepository.delete(id);
    }

    @Override
    public Attendance getById(int id) {
        return attendanceRepository.getById(id);
    }

    @Override
    public List<Attendance> getAll() {
        return attendanceRepository.getAll();
    }
}
