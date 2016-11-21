package DAO;

import Classes.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


class UsersDAOImpl implements UsersDAO {

    private Connection connection;
    private Statement st;
    private PreparedStatement preparedStatement;

    UsersDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public User find(int id) {
        User user = null;

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE (id =?)");
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();



            //ResultSet res = st.executeQuery(
            //        "SELECT * FROM users WHERE (id =" + id + ")"
            //);
            res.next();

            user = new User(id, res.getString("user_name"), res.getString("user_surname"),
                    res.getString("country"), res.getString("city"),
                    res.getString("user_login"), res.getString("user_pasword"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

    public User find(String login) {
        User user = null;

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE (user_login =?)");
            preparedStatement.setString(1,login);
            ResultSet res = preparedStatement.executeQuery();


           // ResultSet res = st.executeQuery(
           //         "SELECT * FROM users WHERE (user_login = '" + login + "')"
           // );

            res.next();
            user = new User(res.getInt("id"), res.getString("user_name"), res.getString("user_surname"),
                    res.getString("country"), res.getString("city"),
                    res.getString("user_login"), res.getString("user_pasword"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

    @Override
    public List<User> findAll() {


        List<User> users = new LinkedList<User>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet res = preparedStatement.executeQuery();



           // ResultSet res = st.executeQuery(
            //        "SELECT * FROM users"
           // );
            while (res.next())
                users.add(new User(res.getInt("id"), res.getString("user_name"), res.getString("user_surname"),
                        res.getString("country"), res.getString("city"),
                        res.getString("user_login"), res.getString("user_pasword")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {


        try {

            preparedStatement = connection.prepareStatement("INSERT INTO users(user_name, user_surname, country, city, user_login, user_pasword)" +
                    "VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setString(4,user.getCity());
            preparedStatement.setString(5,user.getLogin());
            preparedStatement.setString(6,user.getPassword());
            preparedStatement.executeUpdate();






           // st.executeUpdate(
            //        "INSERT INTO users(user_name, user_surname, country, city, user_login, user_pasword) " +
            //                "VALUES ( '" + user.getName() + "', '" + user.getSurname() +
            //                "', '" + user.getCountry() +
           //                 "', '" + user.getCity() + "', '"
           //                 + user.getLogin() + "', '"
           //                 + user.getPassword() + "')"
           // );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

        try {

            preparedStatement = connection.prepareStatement( "DELETE FROM users WHERE (id =?)");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();




            //st.executeUpdate(
           //         "DELETE FROM users WHERE (id =" + id + ")"
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User user) {

        try {

            preparedStatement = connection.prepareStatement( "UPDATE users SET user_name =?, user_surname =?, country =?, city =?, user_login =?, user_pasword =? WHERE id =?");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setString(4,user.getCity());
            preparedStatement.setString(5,user.getLogin());
            preparedStatement.setString(6,user.getPassword());
            preparedStatement.setInt(7,user.getId());
            preparedStatement.executeUpdate();



           // st.executeUpdate(
           //         "UPDATE users SET user_name = '" + user.getName() +
            //                "', user_surname = '" + user.getSurname() +
           //                 "', country = '" + user.getCountry() +
           //                 "', city = '" + user.getCity() +
            //                "', user_login = '" + user.getCity() +
           //                 "', user_passwoord = '" + user.getCity() +
           //                 "' WHERE id =" + user.getId()
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
