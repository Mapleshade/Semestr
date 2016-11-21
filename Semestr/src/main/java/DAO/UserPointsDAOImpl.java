package DAO;


import java.sql.*;
import java.util.ArrayList;

public class UserPointsDAOImpl implements UserPointsDAO {

    private Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement;

    UserPointsDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int[][] find(int id) {
        int[][] uPoints = new int[0][0];
        ArrayList<int[]> points = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user_points WHERE (user_id =?)");
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();



            //ResultSet res = st.executeQuery(
            //        "SELECT * FROM user_points WHERE (user_id =" + id + ")"
            //);
            int i = 0;
            while (res.next()) {
                int[] point = new int[3];
                point[0] = res.getInt("user_id");
                point[1] = res.getInt("subject_id");
                point[2] = res.getInt("points");
                i++;
                points.add(point);
            }
            uPoints = new int[i][3];
            for (int k=0; k<i; k++) {
                uPoints[k] = points.get(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uPoints;
    }

    @Override
    public int[][] findAll() {

        int[][] uPoints = new int[0][0];
        ArrayList<int[]> points = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM user_points");
            ResultSet res = preparedStatement.executeQuery();

            //ResultSet res = st.executeQuery(
             //       "SELECT * FROM user_points"
           // );
            int i = 0;
            while (res.next()) {
                int[] point = new int[6];
                point[0] = res.getInt("user_id");
                point[1] = res.getInt("subject_id");
                point[2] = res.getInt("points");
                i++;
                points.add(point);
            }
            uPoints = new int[i][3];
            for (int k=0; k<i; k++) {
                uPoints[k] = points.get(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uPoints;
    }

    @Override
    public void save(int user_id, int sub_id, int points) {

        try {

            preparedStatement = connection.prepareStatement("INSERT INTO user_points VALUES (?,?,?)");
            preparedStatement.setInt(1,user_id);
            preparedStatement.setInt(2,sub_id);
            preparedStatement.setInt(3,points);
            preparedStatement.executeUpdate();




           // st.executeUpdate(
            //        "INSERT INTO user_points VALUES ( " + user_id +", " + sub_id + ", " + points + ")"
           // );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int user_id, int sub_id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM user_points WHERE (user_id =?, subject_id =?)");
            preparedStatement.setInt(1,user_id);
            preparedStatement.setInt(1,sub_id);
            preparedStatement.executeUpdate();





           // st.executeUpdate(
           //         "DELETE FROM user_points WHERE (user_id =" + user_id + ", subject_id =" + sub_id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int user_id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM user_points WHERE (user_id =?)");
            preparedStatement.setInt(1,user_id);
            preparedStatement.executeUpdate();




           // st.executeUpdate(
           //         "DELETE FROM user_points WHERE (user_id =" + user_id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int user_id, int sub_id, int points) {

        try {

            preparedStatement = connection.prepareStatement("UPDATE user_points SET points=? WHERE (user_id =? AND  subject_id =?)");
            preparedStatement.setInt(1,points);
            preparedStatement.setInt(2,user_id);
            preparedStatement.setInt(3,sub_id);
            preparedStatement.executeUpdate();





            //st.executeUpdate(
            //        "UPDATE user_points SET points= " + points + " WHERE (user_id =" + user_id + " AND  subject_id =" + sub_id +")"
            //);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
