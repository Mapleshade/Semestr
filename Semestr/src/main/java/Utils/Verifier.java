package Utils;


public interface Verifier {

    boolean existAchivement(int userId);

    boolean existCost(int univ_id, int spec_id);

    boolean existEPoint(int univ_id, int ach_id);

    boolean existEPoint(int univ_id);

    boolean existPoint(int univ_id, int spec_id);

    boolean existPoint(int univ_id);

    boolean existSpeciality(int id);

    boolean existSubject(int id);

    boolean existUniversity(int id);

    boolean existUnivInCity(String city);

    boolean existUAchive(int id);

    boolean existUPoints(int id);

    boolean existUser(int id);

    boolean existUser(String login);
}
