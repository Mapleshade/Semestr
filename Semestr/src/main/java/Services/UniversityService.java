package Services;

import Classes.Speciality;
import Classes.University;

import java.util.List;

public interface UniversityService {

    Speciality getSpecialityFromUniv(int idSpec, int idUniv);

    List<Speciality> getAllSpecFromUniv(int idUniv);

    University getUniversity(int idUniv, boolean withSpec);

    List<University> getAllUniviversities();

    List<Speciality> getAllSpeialities();

    Speciality getSpeciality(int idSpec);

    List<University> getAllUnivWithSpec(int idSpec);


}
