package DAO;


public interface CostDAO {

    int[] find(int univ_id, int spec_id);

    int[][] findAllSpecInUniv(int univ_id);

    int[][] findAllUnivWithSpec(int spec_id);

    int[][] findAll();

    void save(int univ_id, int spec_id, int[] cost);

    void delete(int univ_id, int spec_id);

    void update(int univ_id, int spec_id, int[] cost);
}
