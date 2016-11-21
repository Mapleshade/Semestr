package DAO;


public interface UserPointsDAO {

    int[][] find(int id);

    int[][] findAll();



    void save(int user_id, int sub_id, int points);

    void delete(int user_id, int sub_id);

    void delete(int user_id);

    void update(int user_id, int sub_id, int points);
}
