package org.example;


import org.example.databaseManager.stateManager.CarStateManager;
import org.example.databaseManager.stateManager.State;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        CarStateManager carStateManager = new CarStateManager();

        while (carStateManager.getState() != State.EXIT) {
            switch (carStateManager.getState()) {
                case FIND -> carStateManager.findCar();
                case FIND_ALL -> carStateManager.findCars();
                case CHOOSE -> carStateManager.choose();
                case CREATE -> carStateManager.createCar();
                case DELETE -> carStateManager.deleteCar();
                case EXIT -> carStateManager.exit();
            }
        }
    }
}