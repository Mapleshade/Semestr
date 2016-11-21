package DAO;

import Classes.Speciality;
import java.util.List;


public interface SpecialityDAO {

    Speciality find(int id);

    List<Speciality> findAll();

    void save(Speciality speciality);

    void delete(int id);

    void update(Speciality speciality);
}
