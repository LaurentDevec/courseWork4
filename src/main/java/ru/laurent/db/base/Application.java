package ru.laurent.db.base;

import mavenCourseWork.Dao.AlpinistDao;
import mavenCourseWork.app.Alpinist;

public class Application {
    public static void main(String[] args) {

        AlpinistDao alpinistDao = new AlpinistDao();
        alpinistDao.createTable();

        Alpinist alpinist01 = new Alpinist();
        alpinist01.setName("Mike");
        alpinist01.setAddress("Moscow");
        alpinist01.setAge(21);
        alpinistDao.add(alpinist01);

        Alpinist alpinist02 = new Alpinist();
        alpinist02.setName("Alex");
        alpinist02.setAddress("Berlin");
        alpinist02.setAge(24);
        alpinistDao.add(alpinist02);

        Alpinist alpinist03 = new Alpinist();
        alpinist03.setName("Tom");
        alpinist03.setAddress("London");
        alpinist03.setAge(35);
        alpinistDao.add(alpinist03);
    }
}


