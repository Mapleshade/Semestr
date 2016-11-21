package DAO;


import java.sql.*;
import java.util.ArrayList;

class UserAchiveDAOImpl implements UserAchiveDAO {


    private Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement;
    UserAchiveDAOImpl() {

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

            preparedStatement = connection.prepareStatement("SELECT * FROM user_achive WHERE (user_id =?)");
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();



           // ResultSet res = st.executeQuery(
            //        "SELECT * FROM user_achive WHERE (user_id =" + id + ")"
            //);
            int i = 0;
            while (res.next()) {
                int[] point = new int[6];
                point[0] = id;
                point[1] = res.getInt("ach_id");
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

            preparedStatement = connection.prepareStatement("SELECT * FROM user_achive");
            ResultSet res = preparedStatement.executeQuery();



            //ResultSet res = st.executeQuery(
            //        "SELECT * FROM user_achive"
           // );
            int i = 0;
            while (res.next()) {
                int[] point = new int[6];
                point[0] = res.getInt("user_id");
                point[1] = res.getInt("ach_id");
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
    public void save(int user_id, int ach_id) {
        try {

            preparedStatement = connection.prepareStatement("INSERT INTO user_achive VALUES (?,?)");
            preparedStatement.setInt(1,user_id);
            preparedStatement.setInt(2, ach_id);
            preparedStatement.executeUpdate();




            //st.executeUpdate(
           //         "INSERT INTO user_achive VALUES ( " + user_id +", " + ach_id +")"
            //);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int user_id, int ach_id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM user_achive WHERE (user_id =?, ach_id =?)");
            preparedStatement.setInt(1,user_id);
            preparedStatement.setInt(1,ach_id);
            preparedStatement.executeUpdate();



            //st.executeUpdate(
            //        "DELETE FROM user_achive WHERE (user_id =" + user_id + ", ach_id =" + ach_id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void delete(int user_id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM user_achive WHERE user_id =?");
            preparedStatement.setInt(1,user_id);
            preparedStatement.executeUpdate();



            //st.executeUpdate(
            //        "DELETE FROM user_achive WHERE user_id =" + user_id
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
