package repository;

import entity.Passport;

public interface PassportRepository extends Repository<Passport>{
    Passport getByNumber(String number);
    boolean changeNumber(int id);
}
