package DAO;

import java.sql.*;
import java.util.ArrayList;

class PointsDAOImpl implements PointsDAO {

    private Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement;

    PointsDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public int[] find(int univ_id, int spec_id) {
        int[] points = new int[6];

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM points WHERE (univ_id =? AND  spec_id =?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,spec_id);
            ResultSet res = preparedStatement.executeQuery();




           // ResultSet res = st.executeQuery(
           //         "SELECT * FROM points WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id +")"
           // );

            while (res.next()) {
                points[0] = univ_id;
                points[1] = spec_id;
                points[2] = res.getInt("budjet");
                points[3] = res.getInt("day_contract");
                points[4] = res.getInt("evening_form");
                points[5] = res.getInt("correspondence_form");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }

    public int[][] findAllSpecInUniv(int univ_id) {

        int[][] points = new int[0][0];
        ArrayList<int[]> point = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM points WHERE ( univ_id = ?)");
            preparedStatement.setInt(1,univ_id);
            ResultSet res = preparedStatement.executeQuery();




           // ResultSet res = st.executeQuery(
           //         "SELECT * FROM points WHERE ( univ_id = " + univ_id + ")"
           // );
            int i = 0;
            while (res.next()) {
                int[] poin = new int[6];
                poin[0] = res.getInt("univ_id");
                poin[1] = res.getInt("spec_id");
                poin[2] = res.getInt("budjet");
                poin[3] = res.getInt("day_contract");
                poin[4] = res.getInt("evening_form");
                poin[5] = res.getInt("correspondence_form");
                i++;
                point.add(poin);
            }
            points = new int[i][6];
            for (int k=0; k<i; k++) {
                points[k] = point.get(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }

    public int[][] findAllUnivWithSpec(int spec_id) {

        int[][] points = new int[0][0];
        ArrayList<int[]> point = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM points WHERE( spec_id =?)");
            preparedStatement.setInt(1,spec_id);
            ResultSet res = preparedStatement.executeQuery();



           // ResultSet res = st.executeQuery(
           ///         "SELECT * FROM points WHERE( spec_id = "+ spec_id +" )"
           // );
            int i = 0;
            while (res.next()) {
                int[] poin = new int[6];
                poin[0] = res.getInt("univ_id");
                poin[1] = res.getInt("spec_id");
                poin[2] = res.getInt("budjet");
                poin[3] = res.getInt("day_contract");
                poin[4] = res.getInt("evening_form");
                poin[5] = res.getInt("correspondence_form");
                i++;
                point.add(poin);
            }
            points = new int[i][6];
            for (int k=0; k<i; k++) {
                points[k] = point.get(k);
            }

            return points;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }


    public int[][] findAll() {

        int[][] points = new int[0][0];
        ArrayList<int[]> point = new ArrayList<>();

        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM points"
            );
            int i = 0;
            while (res.next()) {
                int[] poin = new int[6];
                poin[0] = res.getInt("univ_id");
                poin[1] = res.getInt("spec_id");
                poin[2] = res.getInt("budjet");
                poin[3] = res.getInt("day_contract");
                poin[4] = res.getInt("evening_form");
                poin[5] = res.getInt("correspondence_form");
                i++;
                point.add(poin);
            }
            points = new int[i][6];
            for (int k=0; k<i; k++) {
                points[k] = point.get(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }


    public void save(int univ_id, int spec_id, int[] points) {

        try {


            preparedStatement = connection.prepareStatement( "INSERT INTO points VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,spec_id);
            preparedStatement.setInt(3,points[0]);
            preparedStatement.setInt(4,points[1]);
            preparedStatement.setInt(5,points[2]);
            preparedStatement.setInt(6,points[3]);
            preparedStatement.executeUpdate();




           // st.executeUpdate(
           //         "INSERT INTO points VALUES ( " + univ_id +", " + spec_id +", " + points[0] +", " + points[1] +", " + points[2] +", " + points[3] + ")"
           // );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int univ_id, int spec_id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM points WHERE (univ_id =?, spec_id =?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,spec_id);
            preparedStatement.executeUpdate();




           // st.executeUpdate(
            //        "DELETE FROM points WHERE (univ_id =" + univ_id + ", spec_id =" + spec_id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(int univ_id, int spec_id, int[] points) {

        try {

            preparedStatement = connection.prepareStatement("UPDATE points SET budjet=?, day_contract =?, evening_form =?, correspondence_form =? WHERE (univ_id =? AND  spec_id =?)");
            preparedStatement.setInt(1,points[0]);
            preparedStatement.setInt(2,points[1]);
            preparedStatement.setInt(3,points[2]);
            preparedStatement.setInt(4,points[3]);
            preparedStatement.setInt(5,univ_id);
            preparedStatement.setInt(6,spec_id);
            preparedStatement.executeUpdate();


            //st.executeUpdate(
            //        "UPDATE points SET budjet= " + points[0] +", day_contract =" + points[1] + ", evening_form = " + points[2] + ", correspondence_form = " + points[3] + " WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id +")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
