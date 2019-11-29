package services;

import entity.Passport;

import java.util.List;

public interface PassportService {
    void save(Passport passport);

    boolean delete(Passport passport);

    boolean delete(int id);

    Passport getPassportByNumber(String number);

    List<Passport> getAll();
}
