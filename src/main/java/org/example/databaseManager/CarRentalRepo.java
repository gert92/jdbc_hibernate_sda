package org.example.databaseManager;

import java.sql.*;

public class CarRentalRepo {

    private final String host;
    private final String port;
    private final String database;
    private final String user;
    private final String password;

    public CarRentalRepo(){
        host = "jdbc:mysql://localhost";
        port = "3306";
        database = "car_rental";
        user = "sdauser";
        password = "";
    }

    public Connection getConnection() {
        String connectionUrl = host + ":" + port + "/" + database + "?serverTimezone=UTC";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(connectionUrl, user, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void findAllCars() {
        String query = "SELECT * FROM cars";

        try(Connection db = getConnection()) {
            PreparedStatement preparedStatement = db.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            System.out.printf("%3s | %10s | %10s | %10s | %5s | %5s \n", "ID", "Producer", "Model", "Year", "HP", "PPD");

            while (result.next()){
                int id = result.getInt("car_id");
                String producer = result.getString("producer");
                String model = result.getString("model");
                int year = result.getInt("year");
                int hp = result.getInt("horse_power");
                int ppd = result.getInt("price_per_day");

                System.out.printf("%3d | %10s | %10s | %10s | %5d | %5d \n", id, producer, model, year, hp, ppd);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findCarById(int carId) {
        String query = "SELECT * FROM cars WHERE car_id = ?";

        try(Connection db = getConnection()) {
            PreparedStatement preparedStatement = db.prepareStatement(query);
            preparedStatement.setInt(1, carId);
            ResultSet result = preparedStatement.executeQuery();

            System.out.printf("%3s | %10s | %10s | %10s | %5s | %5s \n", "ID", "Producer", "Model", "Year", "HP", "PPD");
            result.next();

                int id = result.getInt("car_id");
                String producer = result.getString("producer");
                String model = result.getString("model");
                int year = result.getInt("year");
                int hp = result.getInt("horse_power");
                int ppd = result.getInt("price_per_day");

                System.out.printf("%3d | %10s | %10s | %10s | %5d | %5d \n", id, producer, model, year, hp, ppd);


            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCar(String producer, String model, int year, int hp, int ppd) {
        String query = "INSERT INTO cars(producer, model, year, horse_power, price_per_day) VALUES(?, ?, ?, ?, ?)";

        try(Connection db = getConnection()) {
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, producer);
            statement.setString(2, model);
            statement.setInt(3, year);
            statement.setInt(4, hp);
            statement.setInt(5, ppd);

            Integer result = statement.executeUpdate();

            if (result > 0){
                System.out.println("Adding a car was successful");
            } else {
                System.out.println("Something went wrong!");
            }


            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteCarById(int carId) {
        String query = "DELETE FROM cars WHERE car_id = ?";

        try(Connection db = getConnection()) {
            PreparedStatement preparedStatement = db.prepareStatement(query);
            preparedStatement.setInt(1, carId);
            Integer result = preparedStatement.executeUpdate();

            if (result > 0){
                System.out.println("Deleting a car was successful");
            } else {
                System.out.println("Something went wrong!");
            }


            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
