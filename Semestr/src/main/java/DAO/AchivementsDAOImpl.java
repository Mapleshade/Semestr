package DAO;

import Classes.Achivement;
import Classes.Subject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


class AchivementsDAOImpl implements AchivementsDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement st;
    private List<Achivement> list;

    AchivementsDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Achivement find(int id) {

        Achivement ach = null;
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM achivements WHERE (id =?)");
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();

            SubjectsDAO subjectsDAO = DAOFactory.getSubjectsDAO();
            res.next();
            Subject subject = subjectsDAO.find(res.getInt("ach_sub"));
            ach = new Achivement(subject, res.getString("ach_name"), res.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ach;
    }


    public List<Achivement> findAll() {

        list = new LinkedList<Achivement>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM achivements");
            ResultSet res = preparedStatement.executeQuery();

            SubjectsDAO subjectsDAO = DAOFactory.getSubjectsDAO();
            Subject subject;
            Achivement ach;
            while (res.next()) {
                subject = subjectsDAO.find(res.getInt("ach_sub"));
                ach = new Achivement(subject, res.getString("ach_name"), res.getInt("id"));
                list.add(ach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



    public void save(Achivement achivement) {

        try {
           // "INSERT INTO achivements(ach_sub, ach_name) VALUES ( " + achivement.getSubject().getId() +", '" + achivement.getType() + "')")
            preparedStatement = connection.prepareStatement("INSERT INTO achivements(ach_sub, ach_name) VALUES (?,?)");
            preparedStatement.setInt(1,achivement.getSubject().getId());
            preparedStatement.setString(2,achivement.getType());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM achivements WHERE (id =?)");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();




           //st.executeUpdate(
            //        "DELETE FROM achivements WHERE (id =" + id + ")"
            //);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(Achivement achivement) {

        try {
            preparedStatement = connection.prepareStatement("UPDATE achivements SET ach_sub =?, ach_name = ? WHERE id =?");
            preparedStatement.setInt(1,achivement.getSubject().getId());
            preparedStatement.setString(2,achivement.getType());
            preparedStatement.setInt(3,achivement.getId());
            preparedStatement.executeUpdate();




           // st.executeUpdate(
           //         "UPDATE achivements SET ach_sub =" + achivement.getSubject().getId() + ", ach_name = '" + achivement.getType() + "' WHERE id =" + achivement.getId()
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
