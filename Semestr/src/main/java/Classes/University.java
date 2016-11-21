package Classes;

import java.util.LinkedList;
import java.util.List;

public class University {

    private int id;
    private String name;
    private String country;
    private String city;
    private String address;
    private String about;
    private List<Speciality> specialities;
    private List<Achivement> extra;


    public University(int id, String name, String country, String city, String address, String about, List<Speciality> specialities) {

        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.about = about;
        this.specialities = specialities;
        this.extra = new LinkedList<>();
    }

    public University(int id, String name, String country, String city, String address, String about) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.about = about;
        this.specialities = new LinkedList<Speciality>();
        this.extra = new LinkedList<>();
    }

    public List<Achivement> getExtra() {
        return extra;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public void setExtra(List<Achivement> extra) {
        this.extra = extra;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getAbout() {
        return about;
    }



}
