package mavenCourseWork.app;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "base_mountain")
public class Mountain implements Cloneable {
    @Id
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    private String country;
    private Integer high;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 2) throw new IllegalArgumentException("name must be longer than 2 letters");
        this.name = name;
    }

    private void setCountry(String country) {
        if (country == null || country.trim().length() < 2) throw new IllegalArgumentException("country must be longer than 2 letters");
        this.country = country;
    }

    public int getHigh() {

        return high;
    }

    private void setHigh(int high) {
        if (high < 100) throw new IllegalArgumentException("high must be more than 100 meters");
        this.high = high;
    }

    //    Конструктор
    public Mountain(String name, int high, String country) {
        setName(name);
        setHigh(high);
        setCountry(country);
    }
    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                "country='" + country + '\'' +
                ", height=" + high +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mountain)) return false;
        Mountain mountain = (Mountain) o;
        return high == mountain.high && Objects.equals(name, mountain.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, high);
    }


    @Override
    public Mountain clone() {
        try {
            return (Mountain) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}