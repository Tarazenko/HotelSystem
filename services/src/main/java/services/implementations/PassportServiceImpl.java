package services.implementations;

import entity.Passport;
import repository.implementations.PassportRepositoryImpl;
import services.PassportService;

import java.sql.SQLException;
import java.util.List;

public class PassportServiceImpl implements PassportService {

    private PassportRepositoryImpl passportRepository;

    public PassportServiceImpl() {
       // try {
            passportRepository = new PassportRepositoryImpl();
       // } catch (SQLException e) {
       //     e.printStackTrace();
       // }
    }

    @Override
    public void save(Passport passport) {
        passportRepository.create(passport);
    }

    @Override
    public boolean delete(Passport passport) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Passport getPassportByNumber(String number) {
        return passportRepository.getByNumber(number);
    }

    @Override
    public List<Passport> getAll() {
        return null;
    }
}
