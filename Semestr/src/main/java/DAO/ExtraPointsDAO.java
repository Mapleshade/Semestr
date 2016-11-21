package DAO;


public interface ExtraPointsDAO {

    int[] find(int univ_id, int ach_id);

    int[][] findforUniversity(int univ_id);

    int[][] findAll();

    void save(int univ_id, int ach_id, int points);

    void delete(int univ_id, int ach_id);

    void update(int univ_id, int ach_id, int points);
}
