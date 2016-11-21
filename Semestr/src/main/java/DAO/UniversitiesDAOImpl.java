package DAO;

import Classes.University;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

class UniversitiesDAOImpl implements UniversitiesDAO {


    private Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement;

    UniversitiesDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public University find(int id) {

        University university = null;
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM universities WHERE (id =?)");
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();





            //ResultSet res = st.executeQuery(
            //        "SELECT * FROM universities WHERE (id =" + id + ")"
            //);
            while (res.next())
            university = new University(res.getInt("id"), res.getString("univ_name"),
                    res.getString("country"), res.getString("city"), res.getString("address"), res.getString("about"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return university;
    }

    public List<University> findAllFromCity(String city) {

        List<University> universities =new LinkedList<University>();
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM universities WHERE city = ?");
            preparedStatement.setString(1,city);
            ResultSet res = preparedStatement.executeQuery();




            //ResultSet res = st.executeQuery(
            //        "SELECT * FROM universities WHERE city = " + city
            //);
            while (res.next())
                universities.add(new University(res.getInt("id"), res.getString("univ_name"),
                        res.getString("country"), res.getString("city"), res.getString("address"), res.getString("about")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return universities;
    }

    @Override
    public List<University> findAll() {

        List<University> universities =new LinkedList<University>();
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM universities");
            ResultSet res = preparedStatement.executeQuery();



            //ResultSet res = st.executeQuery(
            //        "SELECT * FROM universities"
            //);
            while (res.next())
            universities.add(new University(res.getInt("id"), res.getString("univ_name"),
                    res.getString("country"), res.getString("city"), res.getString("address"), res.getString("about")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return universities;
    }

    @Override
    public void save(University university) {

        try {

            preparedStatement = connection.prepareStatement("INSERT INTO universities(univ_name, country, city, address, about) " +
                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,university.getName());
            preparedStatement.setString(2,university.getCountry());
            preparedStatement.setString(3,university.getCity());
            preparedStatement.setString(4,university.getAddress());
            preparedStatement.setString(5,university.getAbout());
            preparedStatement.executeUpdate();


            //st.executeUpdate(
            //        "INSERT INTO universities(univ_name, country, city, address, about) " +
            //                "VALUES ( '" + university.getName() + "', '" + university.getCountry() +
            //                "', '" + university.getCity() +
            //                "', '" + university.getAddress() +
            //                "', '" + university.getAbout() + "')"
           // );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM universities WHERE (id =?)");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();


           // st.executeUpdate(
           //         "DELETE FROM universities WHERE (id =" + id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(University university) {

        try {

            preparedStatement = connection.prepareStatement("UPDATE universities SET univ_name =?, country = ?, city =?, address = ?, about = ? WHERE id = ?");
            preparedStatement.setString(1,university.getName());
            preparedStatement.setString(2,university.getCountry());
            preparedStatement.setString(3,university.getCity());
            preparedStatement.setString(4,university.getAddress());
            preparedStatement.setString(5,university.getAbout());
            preparedStatement.setInt(6,university.getId());
            preparedStatement.executeUpdate();


            //st.executeUpdate(
              //      "UPDATE universities SET unniv_name = '" + university.getName() +
             //               "', country = '" + university.getCountry() +
              //              "', city = '" + university.getCity() +
             //               "', address = '" + university.getAddress() +
            //                "', about = '" + university.getAbout()
             //               + "' WHERE id =" + university.getId()
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
