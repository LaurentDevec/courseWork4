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
    private Integer height = 10; //значение по умолчанию

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 2) throw new IllegalArgumentException("name must be longer than 2 letters");
        this.name = name;
    }

    public int getHeight() {

        return height;
    }

    private void setHeight(int height) {
        if (height < 100) throw new IllegalArgumentException("height must be more than 100 meters");
        this.height = height;
    }

    //    Конструктор
    public Mountain(String name, int height) {
        setName(name);
        setHeight(height);
    }
    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mountain)) return false;
        Mountain mountain = (Mountain) o;
        return height == mountain.height && Objects.equals(name, mountain.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height);
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