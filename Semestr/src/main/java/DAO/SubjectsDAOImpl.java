package DAO;

import Classes.Subject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

class SubjectsDAOImpl implements SubjectsDAO {

    private Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement;
    SubjectsDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Subject find(int id) {

        Subject sub = null;
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM subjects WHERE id =?");
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();


           // ResultSet res = st.executeQuery(
           //         "SELECT * FROM subjects WHERE id = " + id
           // );
            res.next();
            sub = new Subject(res.getInt("id"), res.getString("sub_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sub;
    }


    public List<Subject> findAll() {

        List<Subject> sub = new LinkedList<Subject>();
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM subjects");
            ResultSet res = preparedStatement.executeQuery();


           // ResultSet res = st.executeQuery(
            //        "SELECT * FROM subjects"
           // );
            while (res.next())
              sub.add(new Subject(res.getInt("id"), res.getString("sub_name")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sub;
    }


    public void save(Subject subject) {

        try {

            preparedStatement = connection.prepareStatement("INSERT INTO subjects(sub_name) VALUES (?)");
            preparedStatement.executeUpdate();




            //st.executeUpdate(
            //        "INSERT INTO subjects(sub_name) VALUES ( '" + subject.getName() + "')"
           // );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM subjects WHERE (id =" + id + ")");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();


           // st.executeUpdate(
           //         "DELETE FROM subjects WHERE (id =" + id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(Subject subject) {

        try {

            preparedStatement = connection.prepareStatement("UPDATE subjets SET sub_name =? WHERE id =?");
            preparedStatement.setString(1,subject.getName());
            preparedStatement.setInt(2,subject.getId());
            preparedStatement.executeUpdate();




            //st.executeUpdate(
            //        "UPDATE subjets SET sub_name = '" + subject.getName() + "' WHERE id =" + subject.getId()
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
