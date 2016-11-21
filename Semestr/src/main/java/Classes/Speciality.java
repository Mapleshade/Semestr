package Classes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Speciality {

    private int id;
    private String name;
    private List<Subject> subjects;
    private String about;
    private University university;
    private int[] points;
    private int[] costs;


    public Speciality(int id, String name, List<Subject> subjects, String about) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.about = about;
        this.university = null;
        this.points = new int[4];
        this.costs = new int[3];
    }



    public Speciality(int id, University university, String name, String about) {
        this.id = id;
        this.university = university;
        this.name = name;
        this.about = about;
        this.subjects = new LinkedList<Subject>();
        this.points = new int[4];
        this.costs = new int[3];
}

    public Speciality(int id, String name, University university, List<Subject> subjects, String about) {
        this.id = id;
        this.university = university;
        this.name = name;
        this.subjects = subjects;
        this.about = about;
        this.points = new int[4];
        this.costs = new int[3];
    }

    public int[] getPoints() {
        return points;
    }

    public void setPoints(int bugjet, int dayContract, int eveningForm, int correspondenceForm) {
        this.points = new int[] {bugjet, dayContract, eveningForm, correspondenceForm};
    }

    public int[] getCosts() {
        return costs;
    }

    public void setCosts(int dayContract, int eveningForm, int correspondenceForm) {
        this.costs = new int[] {dayContract, eveningForm, correspondenceForm};
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getAbout() {
        return about;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getSubjToString() {
        String subs = "";
        Iterator<Subject> it = subjects.iterator();
        while (it.hasNext()) {
            subs += it.next().getName() + " " + "\n";
        }
        return subs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

