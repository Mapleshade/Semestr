package DAO;

import java.sql.*;
import java.util.ArrayList;

class ExtraPointsDAOImpl implements ExtraPointsDAO {

    private Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement;

    ExtraPointsDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int[] find(int univ_id, int ach_id) {
        int[] points = new int[3];
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM ExtraPoints WHERE (univ_id =? AND  ach_id =?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,ach_id);
            ResultSet res = preparedStatement.executeQuery();

            //ResultSet res = st.executeQuery(
             //       "SELECT * FROM ExtraPoints WHERE (univ_id =" + univ_id + " AND  ach_id =" + ach_id +")"
            //);

            while (res.next()) {
                points[0] = univ_id;
                points[1] = ach_id;
                points[2] = res.getInt("points");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }


    public int[][] findAll() {

        int[][] ePoints = new int[0][0];
        ArrayList<int[]> points = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM ExtraPoints");
            ResultSet res = preparedStatement.executeQuery();



           // ResultSet res = st.executeQuery(
           //         "SELECT * FROM ExtraPoints"
           // );
            int i = 0;
            while (res.next()) {
                int[] point = new int[6];
                point[0] = res.getInt("univ_id");
                point[1] = res.getInt("ach_id");
                point[2] = res.getInt("points");
                i++;
                points.add(point);
            }
            ePoints = new int[i][6];
            for (int k=0; k<i; k++) {
                ePoints[k] = points.get(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ePoints;
    }



    public int[][] findforUniversity(int univ_id) {

        int[][] ePoints = new int[0][0];
        ArrayList<int[]> points = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM ExtraPoints WHERE univ_id =?");
            preparedStatement.setInt(1,univ_id);
            ResultSet res = preparedStatement.executeQuery();



           // ResultSet res = st.executeQuery(
            //        "SELECT * FROM ExtraPoints WHERE univ_id =" + univ_id
           // );
            int i = 0;
            while (res.next()) {
                int[] point = new int[3];
                point[0] = res.getInt("univ_id");
                point[1] = res.getInt("ach_id");
                point[2] = res.getInt("points");
                i++;
                points.add(point);
            }
            ePoints = new int[i][3];
            for (int k=0; k<i; k++) {
                ePoints[k] = points.get(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ePoints;
    }


    public void save(int univ_id, int ach_id, int points) {

        try {


            preparedStatement = connection.prepareStatement("INSERT INTO ExtraPoints VALUES (?,?,?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,ach_id);
            preparedStatement.setInt(3,points);
            preparedStatement.executeUpdate();



            //st.executeUpdate(
            //        "INSERT INTO ExtraPoints VALUES ( " + univ_id +", " + ach_id +", " + points +" )"
          //  );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int univ_id, int ach_id) {

        try {


            preparedStatement = connection.prepareStatement("DELETE FROM ExtraPoints WHERE (univ_id =?, ach_id =?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,ach_id);
            preparedStatement.executeUpdate();



           // st.executeUpdate(
           //         "DELETE FROM ExtraPoints WHERE (univ_id =" + univ_id + ", ach_id =" + ach_id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(int univ_id, int ach_id, int points) {

        try {

            preparedStatement = connection.prepareStatement( "UPDATE extrapoints SET points =? WHERE (univ_id =? AND  ach_id =?)");
            preparedStatement.setInt(1,points);
            preparedStatement.setInt(2,univ_id);
            preparedStatement.setInt(3,ach_id);
            preparedStatement.executeUpdate();



           // st.executeUpdate(
           //         "UPDATE costs SET points =" + points + " WHERE (univ_id =" + univ_id + " AND  ach_id =" + ach_id +")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
