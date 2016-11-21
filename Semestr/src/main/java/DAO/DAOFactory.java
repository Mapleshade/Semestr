package DAO;




import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DAOFactory {

    private static Properties properties;



    static {

        properties = new Properties();

        try {

            properties.load(

                    new FileInputStream("C:\\Users\\Elina\\Documents\\Semestr\\resources\\DAO.properties"));

        } catch (IOException e) {

            e.printStackTrace();

        }



    }



    public static AchivementsDAO getAchivementsDAO() {

        AchivementsDAO achivementsDAO = null;

        try {

            achivementsDAO = (AchivementsDAO) Class.forName( properties.getProperty("achivementDAO.class")).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return achivementsDAO;

    }



    public static CostDAO getCostDAO() {

        CostDAO costDAO = null;

        try {

            costDAO = (CostDAO) Class.forName( properties.getProperty("costDAO.class")).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return costDAO;

    }





    public static ExtraPointsDAO getExtraPointsDAO() {

        ExtraPointsDAO extraDao = null;

        try {

            extraDao = (ExtraPointsDAO) Class.forName( properties.getProperty("extraDAO.class")).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return extraDao;

    }





    public static PointsDAO getPointsDAO() {

        PointsDAO pointsDAO= null;

        try {

             pointsDAO = (PointsDAO) Class.forName(properties.getProperty("pointDAO.class")).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return pointsDAO;

    }





    public static SpecialityDAO getSpecialityDAO() {

        SpecialityDAO specialityDAO = null;

        try {

            specialityDAO = (SpecialityDAO) Class.forName(properties.getProperty("specialityDAO.class")

            ).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return specialityDAO;

    }





    public static SubjectsDAO getSubjectsDAO() {

        SubjectsDAO subjectsDAO = null;

        try {

            subjectsDAO = (SubjectsDAO) Class.forName(

                    properties.getProperty("subjectDAO.class")

            ).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return subjectsDAO;

    }



    public static UniversitiesDAO getUniversitiesDAO() {

        UniversitiesDAO universitiesDAO = null;

        try {

            universitiesDAO = (UniversitiesDAO) Class.forName(

                    properties.getProperty("universityDAO.class")

            ).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return universitiesDAO;

    }


    public static UserAchiveDAO getUserAchiveDAO() {

        UserAchiveDAO userAchiveDAO = null;

        try {

            userAchiveDAO = (UserAchiveDAO) Class.forName(

                    properties.getProperty("userAchiveDAO.class")

            ).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return userAchiveDAO;

    }

    public static UserPointsDAO getUserPointsDAO() {

        UserPointsDAO userPointsDAO = null;

        try {

            userPointsDAO = (UserPointsDAO) Class.forName(

                    properties.getProperty("userPointsDAO.class")

            ).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return userPointsDAO;

    }

    public static UsersDAO getUsersDAO() {

        UsersDAO usersDAO = null;

        try {

            usersDAO = (UsersDAO) Class.forName(

                    properties.getProperty("userDAO.class")

            ).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return usersDAO;

    }
}
