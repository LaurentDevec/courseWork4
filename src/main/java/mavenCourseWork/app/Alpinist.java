package mavenCourseWork.app;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "my_alpinist")
public class Alpinist implements Cloneable{
    @Column(nullable = false)
    private String name;

    private String address;
    @Column(nullable = false, unique = true)

    private int id;
    @ManyToMany(fetch = FetchType.LAZY)
    private Group group;
    private int age;

    //    методы
    public void setName(String name) {
        if (name == null || name.trim().length() < 3) throw new IllegalArgumentException("В имени должно быть больше 3 символов");
        this.name = name;

    }

    public void setAddress(String address) {
        if (address == null || !address.trim().contains("@")) throw new IllegalArgumentException("В адресе должно быть больше 5 символов");
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Climber{" +
                "fullName='" + name + '\'' +
                ", age='" + age + '\'' +
                ", email='" + address + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public Alpinist clone() {
        try {
            return (Alpinist) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alpinist)) return false;
        Alpinist climber = (Alpinist) o;
        return Objects.equals(name, climber.name) && Objects.equals(address, climber.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, id, age);
    }
}
