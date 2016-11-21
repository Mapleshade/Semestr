package Services;

import Classes.Achivement;
import Classes.Speciality;
import Classes.Subject;
import Classes.University;
import DAO.*;
import Utils.Verifier;
import Utils.VerifierFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UniversityServiceImpl implements UniversityService {

    Connection connection;
    Statement statement;
    Verifier verifier;

    SubjectsDAO subDao;
    SpecialityDAO specDAO;
    UniversitiesDAO univDAO;
    CostDAO cosDAO;
    PointsDAO poDAO;
    ExtraPointsDAO EPoinDAO;
    AchivementsDAO achDAO;

    UniversityServiceImpl() {
        try {
            EPoinDAO = DAOFactory.getExtraPointsDAO();
            achDAO = DAOFactory.getAchivementsDAO();
            connection = ConnectionPSQL.getConnection();
            statement = connection.createStatement();
            verifier = VerifierFactory.getVerifier();
            subDao = DAOFactory.getSubjectsDAO();
            specDAO = DAOFactory.getSpecialityDAO();
            univDAO = DAOFactory.getUniversitiesDAO();
            cosDAO = DAOFactory.getCostDAO();
            poDAO = DAOFactory.getPointsDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Speciality getSpecialityFromUniv(int idSpec, int idUniv) {

        if(verifier.existPoint(idUniv, idSpec) && verifier.existCost(idUniv, idSpec)) {

            Speciality spec = specDAO.find(idSpec);
            spec.setUniversity(univDAO.find(idUniv));
            int[] cost = cosDAO.find(idUniv, idSpec);
            spec.setCosts(cost[2],cost[3],cost[4]);
            cost = poDAO.find(idUniv, idSpec);
            spec.setPoints(cost[2],cost[3],cost[4],cost[5]);

            return spec;

        } else {
            return null;
        }
    }

    public List<Speciality> getAllSpecFromUniv(int idUniv) {
        if (verifier.existPoint(idUniv)) {
            List<Speciality> list = new LinkedList<Speciality>();
            int[][] points = poDAO.findAllSpecInUniv(idUniv);
            for(int i = 0; i < points.length; i++) {
                list.add(getSpecialityFromUniv(points[i][1], idUniv));
            }
            return list;
        } else {
            return null;
        }
    }

    public University getUniversity(int idUniv, boolean withSpec) {
        if(verifier.existUniversity(idUniv)) {
            University univ = univDAO.find(idUniv);
            if(verifier.existEPoint(idUniv)) {
                int[][] point = EPoinDAO.findforUniversity(idUniv);
                Achivement ach;
                List<Achivement> achs = new LinkedList<Achivement>();
                for(int i=0;  i<point.length; i++) {
                    ach = achDAO.find(point[i][1]);
                    ach.setPoints(point[i][2]);
                    achs.add(ach);
                }
                univ.setExtra(achs);
            }
            if (withSpec)
            univ.setSpecialities(getAllSpecFromUniv(idUniv));
            return univ;
        } else
        return null;
    }

    public List<University> getAllUniviversities() {
        List<University> list = univDAO.findAll();
        return list;
    }

    public List<University> getAllUniviversitiesFromCity(String city) {
        if(verifier.existUnivInCity(city)) {
            List<University> list = univDAO.findAllFromCity(city);
            return list;
        } else
            return null;
    }

    public List<Speciality> getAllSpeialities() {
        List<Speciality> list = specDAO.findAll();
        return list;
    }

    public Speciality getSpeciality(int idSpec) {
        if(verifier.existSpeciality(idSpec)) {
            return specDAO.find(idSpec);
        } else
            return null;
    }


    public List<University> getAllUnivWithSpec(int idSpec) {
        List<University> list = new LinkedList<University>();
        if(verifier.existSpeciality(idSpec)) {
            int[][] point = poDAO.findAllUnivWithSpec(idSpec);
            for(int i=0; i<point.length; i++) {
                list.add(univDAO.find(point[i][0]));
            }
        }
        return list;
    }
}
