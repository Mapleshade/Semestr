package DAO;


import Classes.Achivement;
import java.util.List;

public interface AchivementsDAO {

    Achivement find(int id);

    List<Achivement> findAll();

    void save(Achivement achivement);

    void delete(int id);

    void update(Achivement achivement);
}
