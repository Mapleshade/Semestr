package Utils;


import DAO.ConnectionPSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class VerifierImpl implements Verifier {

    private Connection connection;
    private Statement st;

    public VerifierImpl() {
        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean existAchivement(int userId) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM achivements WHERE (id =" + userId + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existCost(int univ_id, int spec_id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM costs WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existEPoint(int univ_id, int ach_id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM ExtraPoints WHERE (univ_id =" + univ_id + " AND  ach_id =" + ach_id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existEPoint(int univ_id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM ExtraPoints WHERE univ_id =" + univ_id
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existPoint(int univ_id, int spec_id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM points WHERE (univ_id =" + univ_id + " AND  spec_id =" + spec_id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existPoint(int univ_id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM points WHERE univ_id =" + univ_id
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existSpeciality(int id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM specialities WHERE (id =" + id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existSubject(int id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM subjects WHERE (id =" + id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existUniversity(int id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM universities WHERE (id =" + id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existUAchive(int id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM user_achive WHERE (user_id =" + id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existUPoints(int id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM user_points WHERE (user_id =" + id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existUser(int id) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM users WHERE (id =" + id + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existUnivInCity(String city) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM universities WHERE (city =" + city + ")"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existUser(String login) {
        try {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM users WHERE (user_login = '" + login + "')"
            );

            if (!resultSet.next()) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
