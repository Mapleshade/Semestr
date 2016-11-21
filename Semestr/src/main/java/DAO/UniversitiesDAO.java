package DAO;

import Classes.University;
import java.util.List;


public interface UniversitiesDAO {

    University find(int id);

    List<University> findAll();

    List<University> findAllFromCity(String city);

    void save(University university);

    void delete(int id);

    void update(University university);
}
