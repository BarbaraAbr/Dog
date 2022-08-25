package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogRepository {

    /*public static void main(String[] args) {
        getConnection();

        Employee employee = new Employee();

        employee.setName("oleg");
        employee.setEmail(" ");
        employee.setCountry(" ");
        save(employee);
    }*/

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/dog";
        String user = "postgres";
        String password = "Molly";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int save(Dog employee) {
        int status = 0;
        Connection connection = DogRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("insert into dog(name,email,country) values (?,?,?)");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getOwner());
            ps.setString(3, employee.getCountry());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(Dog employee) {

        int status = 0;
        Connection connection = DogRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("update dog set name=?,owner=?,country=? where id=?");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getOwner());
            ps.setString(3, employee.getCountry());
            ps.setInt(4, employee.getId());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {

        int status = 0;
        Connection connection = DogRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("delete from dog where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Dog getDogById(int id) {

        Dog dog = new Dog();
        Connection connection = DogRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from dog where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dog.setId(rs.getInt(1));
                dog.setName(rs.getString(2));
                dog.setOwner(rs.getString(3));
                dog.setCountry(rs.getString(4));
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return dog;
    }

    public static List<Dog> getAllDogs() {

        List<Dog> listDogs = new ArrayList<>();
        Connection connection = DogRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from dog");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Dog dog = new Dog();

                dog.setId(rs.getInt(1));
                dog.setName(rs.getString(2));
                dog.setOwner(rs.getString(3));
                dog.setCountry(rs.getString(4));

                listDogs.add(dog);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDogs;
    }
}
