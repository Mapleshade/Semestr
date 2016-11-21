package DAO;


import java.sql.*;
import java.util.ArrayList;

class CostDAOImpl implements CostDAO {

    private Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement;

    CostDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int[] find(int univ_id, int spec_id) {
        int[] cost = new int[5];

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM costs WHERE (univ_id =? AND  spec_id =?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,spec_id);
            ResultSet res = preparedStatement.executeQuery();




           // ResultSet res = st.executeQuery(
            //        "SELECT * FROM costs WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id +")"
           // );

            while (res.next()) {
                cost[0] = univ_id;
                cost[1] = spec_id;
                cost[2] = res.getInt("day_contract");
                cost[3] = res.getInt("evening_form");
                cost[4] = res.getInt("correspondence_form");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cost;
    }

    public int[][] findAllSpecInUniv(int univ_id) {

        int[][] costs = new int[0][0];
        ArrayList<int[]> cost = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM costs WHERE ( univ_id =?");
            preparedStatement.setInt(1,univ_id);
            ResultSet res = preparedStatement.executeQuery();




           // ResultSet res = st.executeQuery(
           //         "SELECT * FROM costs WHERE ( univ_id = " + univ_id + ")"
           // );



            int i = 0;
            while (res.next()) {
                int[] cost1 = new int[6];
                cost1[0] = res.getInt("univ_id");
                cost1[1] = res.getInt("spec_id");
                cost1[2] = res.getInt("budjet");
                cost1[3] = res.getInt("day_contract");
                cost1[4] = res.getInt("evening_form");
                cost1[5] = res.getInt("correspondence_form");
                i++;
                cost.add(cost1);
            }
            costs = new int[i][6];
            for (int k=0; k<i; k++) {
                costs[k] = cost.get(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return costs;
    }

    public int[][] findAllUnivWithSpec(int spec_id) {

        int[][] costs = new int[0][0];
        ArrayList<int[]> cost = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM costs WHERE ( spec_id=? )");
            preparedStatement.setInt(1,spec_id);
            ResultSet res = preparedStatement.executeQuery();





            //ResultSet res = st.executeQuery(
            //        "SELECT * FROM costs WHERE ( spec_id = "+ spec_id +" )"
           // );




            int i = 0;
            while (res.next()) {
                int[] cost1 = new int[6];
                cost1[0] = res.getInt("univ_id");
                cost1[1] = res.getInt("spec_id");
                cost1[2] = res.getInt("budjet");
                cost1[3] = res.getInt("day_contract");
                cost1[4] = res.getInt("evening_form");
                cost1[5] = res.getInt("correspondence_form");
                i++;
                cost.add(cost1);
            }
            costs = new int[i][6];
            for (int k=0; k<i; k++) {
                costs[k] = cost.get(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return costs;
    }

    public int[][] findAll() {

        int[][] costs = new int[0][0];
        ArrayList<int[]> cost = new ArrayList<>();

        try {
            ResultSet res = st.executeQuery(
                    "SELECT * FROM costs"
            );
            int i = 0;
            while (res.next()) {
                int[] cost1 = new int[6];
                cost1[0] = res.getInt("univ_id");
                cost1[1] = res.getInt("spec_id");
                cost1[2] = res.getInt("budjet");
                cost1[3] = res.getInt("day_contract");
                cost1[4] = res.getInt("evening_form");
                cost1[5] = res.getInt("correspondence_form");
                i++;
                cost.add(cost1);
            }
            costs = new int[i][6];
            for (int k=0; k<i; k++) {
                costs[k] = cost.get(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return costs;
    }


    public void save(int univ_id, int spec_id, int[] cost) {

        try {

            preparedStatement = connection.prepareStatement("INSERT INTO costs VALUES ( ?, ?,?,?,?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,spec_id);
            preparedStatement.setInt(3,cost[0]);
            preparedStatement.setInt(4,cost[1]);
            preparedStatement.setInt(5,cost[2]);
            preparedStatement.executeUpdate();



            //st.executeUpdate(
            //        "INSERT INTO costs VALUES ( " + univ_id +", " + spec_id +", " + cost[0] +", " + cost[1] +", " + cost[2] + ")"
            //);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int univ_id, int spec_id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM costs WHERE (univ_id =?, spec_id =?)");
            preparedStatement.setInt(1,univ_id);
            preparedStatement.setInt(2,spec_id);
            preparedStatement.executeUpdate();


            //st.executeUpdate(
            //        "DELETE FROM costs WHERE (univ_id =" + univ_id + ", spec_id =" + spec_id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(int univ_id, int spec_id, int[] cost) {

        try {
            preparedStatement = connection.prepareStatement("UPDATE costs SET day_contract =?, evening_form = ?, correspondence_form = ? WHERE (univ_id =? AND  spec_id =?");
            preparedStatement.setInt(1,cost[0]);
            preparedStatement.setInt(2,cost[1]);
            preparedStatement.setInt(3,cost[2]);
            preparedStatement.setInt(4,univ_id);
            preparedStatement.setInt(5,spec_id);
            preparedStatement.executeUpdate();



            // st.executeUpdate(
            //        "UPDATE costs SET day_contract =" + cost[0] + ", evening_form = " + cost[1] + ", correspondence_form = " + cost[2] + " WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id +")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
