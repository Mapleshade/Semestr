package Classes;

public class Subject {
    private int id;
    private String name;
    private int point;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Subject(int id, String name, int point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
        this.point = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
