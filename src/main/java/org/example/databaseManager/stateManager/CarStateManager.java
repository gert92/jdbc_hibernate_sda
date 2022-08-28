package org.example.databaseManager.stateManager;

import org.example.databaseManager.CarRentalRepo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class CarStateManager {
    private final CarRentalRepo carRentalRepo = new CarRentalRepo();
    private State state = State.CHOOSE;
    private final Scanner sc = new Scanner(System.in);

    public State getState() {
        return state;
    }

    public void choose(){
        System.out.println("Select from the menu:");
        System.out.println("0. Exit");
        System.out.println("1. Find all cars");
        System.out.println("2. Find single car");
        System.out.println("3. Add a car");
        System.out.println("4. Delete a car");
        int selection = Integer.parseInt(sc.nextLine());
        Iterator<State> values = Arrays.stream(State.values()).iterator();
        while (values.hasNext()){
            State next = values.next();
            if (next.number == selection){
                state = next;
            }
        }

    }
    public void exit(){
        state = State.EXIT;
    }

    public void findCars(){
        carRentalRepo.findAllCars();
        state = State.CHOOSE;
    }

    public void findCar(){
        System.out.println("Input the id of the car:");
        int id = Integer.parseInt(sc.nextLine());
        carRentalRepo.findCarById(id);
        state = State.CHOOSE;
    }

    public void createCar(){
        System.out.println("Who is the producer of the car?");
        String prod = sc.nextLine();
        System.out.println("What is the model of the car?");
        String model = sc.nextLine();
        System.out.println("What year is it released?");
        int year = Integer.parseInt(sc.nextLine());
        System.out.println("How much horsepower does it have?");
        int hp = Integer.parseInt(sc.nextLine());
        System.out.println("What is the price per day of the car?");
        int ppd = Integer.parseInt(sc.nextLine());

        carRentalRepo.createCar(prod, model, year, hp, ppd);

        state = State.CHOOSE;

    }

    public void deleteCar(){
        System.out.println("Enter the id of the car to delete:");
        int carId = Integer.parseInt(sc.nextLine());
        carRentalRepo.deleteCarById(carId);
        state = State.CHOOSE;
    }


}
