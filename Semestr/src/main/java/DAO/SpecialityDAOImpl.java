package DAO;

import Classes.Speciality;
import Classes.Subject;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class SpecialityDAOImpl implements SpecialityDAO {

    private Connection connection;
    private Statement st;
    private List<Speciality> list;
    private PreparedStatement preparedStatement;

    SpecialityDAOImpl() {

        try {
            this.connection = ConnectionPSQL.getConnection();
            this.st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Speciality find(int id) {
        Speciality spec = null;
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM specialities WHERE (id =?)");
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();


           // ResultSet res = st.executeQuery(
           //         "SELECT * FROM specialities WHERE (id =" + id + ")"
           // );
            res.next();
            SubjectsDAO subjectsDAO = DAOFactory.getSubjectsDAO();
            List<Subject> subjects = new LinkedList<Subject>();
            String string = "subject1";
            int i =2;
            Subject subject;
            while (res.getInt(string) != 0 && (i < 6)) {
                subject = subjectsDAO.find(res.getInt(string));
                string = "subject" + i;
                i++;
                subjects.add(subject);
            }

            spec = new Speciality(res.getInt("id"), res.getString("spec_name"), subjects, res.getString("about"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spec;
    }


    public List<Speciality> findAll() {
        List<Speciality> specialities = new LinkedList<Speciality>();
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM specialities");
            ResultSet res = preparedStatement.executeQuery();


            //ResultSet res = st.executeQuery(
           //         "SELECT * FROM specialities"
           // );

            SubjectsDAO subjectsDAO = DAOFactory.getSubjectsDAO();
            List<Subject> subjects;
            Subject subject;
            while(res.next()) {
                subjects = new LinkedList<Subject>();
                String string = "subject1";
                int i = 2;
                while (res.getInt(string) != 0 && (i < 6)) {
                    subject = subjectsDAO.find(res.getInt(string));
                    string = "subject" + i;
                    i++;
                    subjects.add(subject);
                }
                Speciality spec = new Speciality(res.getInt("id"), res.getString("spec_name"), subjects, res.getString("about"));
                specialities.add(spec);
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialities;
    }


    public void save(Speciality speciality) {

        List<Subject> subjects = speciality.getSubjects();

        if (subjects.size() < 5) {
            int dif = 5 - subjects.size();
            for (int i = 0; i < dif; i++) {
                subjects.add(new Subject(0, ""));
            }
        }

        Iterator<Subject> iter = speciality.getSubjects().iterator();

        try {

            preparedStatement = connection.prepareStatement("INSERT INTO specialities(spec_name, subject1, subject2, subject3, subject4, subject5, about) VALUES (?,?,?,?,?,?,?");
            preparedStatement.setString(1,speciality.getName());
            preparedStatement.setInt(2,iter.next().getId());
            preparedStatement.setInt(3,iter.next().getId());
            preparedStatement.setInt(4,iter.next().getId());
            preparedStatement.setInt(5,iter.next().getId());
            preparedStatement.setInt(6,iter.next().getId());
            preparedStatement.setString(7,speciality.getAbout());
            preparedStatement.executeUpdate();





            //st.executeUpdate(
            //        "INSERT INTO specialities(spec_name, subject1, subject2, subject3, subject4, subject5, about) VALUES ( '" + speciality.getName() + "', " + iter.next().getId() + ", " + iter.next().getId() + "," + iter.next().getId() + ", " + iter.next().getId() + ", " + iter.next().getId() + ", '" + speciality.getAbout()  + "')"
           // );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {

        try {

            preparedStatement = connection.prepareStatement("DELETE FROM specialities WHERE (id =?)");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();





            //st.executeUpdate(
            //        "DELETE FROM specialities WHERE (id =" + id + ")"
            //);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(Speciality speciality) {
        List<Subject> subjects = speciality.getSubjects();

        if (subjects.size() < 5) {
            int dif = 5 - subjects.size();
            for (int i = 0; i < dif; i++) {
                subjects.add(new Subject(0, ""));
            }
        }

        Iterator<Subject> iter = speciality.getSubjects().iterator();

        try {

            preparedStatement = connection.prepareStatement("UPDATE specialities SET spec_name =?, subject1 =?, subject2 =?, subject3 =?, subject4 =?, subject5 =?, about =? WHERE id =?");
            preparedStatement.setString(1,speciality.getName());
            preparedStatement.setInt(2,iter.next().getId());
            preparedStatement.setInt(3,iter.next().getId());
            preparedStatement.setInt(4,iter.next().getId());
            preparedStatement.setInt(5,iter.next().getId());
            preparedStatement.setInt(6,iter.next().getId());
            preparedStatement.setString(7,speciality.getAbout());
            preparedStatement.setInt(8,speciality.getId());
            preparedStatement.executeUpdate();



           // st.executeUpdate(
            //        "UPDATE specialities SET spec_name = '" + speciality.getName() + "', subject1 = " + iter.next().getId() + ", subject2 = " + iter.next().getId() + ", subject3 = " + iter.next().getId() + ", subject4 = " + iter.next().getId() + ", subject5 = " + iter.next().getId() + ", about = '" + speciality.getAbout() + "' WHERE id =" + speciality.getId()
           // );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
