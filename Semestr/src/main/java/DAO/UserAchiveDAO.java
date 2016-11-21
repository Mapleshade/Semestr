package DAO;


public interface UserAchiveDAO {

    int[][] find(int id);

    int[][] findAll();

    void save(int user_id, int ach_id);

    void delete(int user_id, int ach_id);

    void delete(int user_id);

}
