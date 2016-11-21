package DAO;

import Classes.Subject;

import java.util.List;

public interface SubjectsDAO {

    Subject find(int id);

    List<Subject> findAll();

    void save(Subject subject);

    void delete(int id);

    void update(Subject subject);
}
