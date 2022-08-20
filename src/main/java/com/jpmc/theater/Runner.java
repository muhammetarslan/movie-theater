package com.jpmc.theater;

import com.jpmc.theater.utils.Helper;

import java.util.Scanner;

public class Runner {
    private static int customerIdGenerator = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Welcome to Movie-theater! Please enter the first and last name. Or enter \"exit\" to exit from Movie-Theater");
                String name = scanner.nextLine();
                if(name.equals("exit")){
                    break;
                }
                Customer customer = new Customer(name, (customerIdGenerator++) + "");
                System.out.println("In order to make a reservation, please enter the number of the showing. Or enter \"exit\" to exit from Movie-Theater");
                Theater theater = new Theater(LocalDateProvider.singleton());
                theater.printSchedule();
                String selected = scanner.nextLine();
                if(selected.equals("exit")){
                    break;
                }
                System.out.println("How many tickets you would like to buy?");
                String count = scanner.nextLine();
                Showing showing = theater.getSchedule().get(Integer.parseInt(selected));
                Helper.saveReservation(customer.getId(),
                        customer.getName(),
                        showing.getMovie().getTitle(),
                        showing.getStartTime().toString(),
                        String.valueOf(showing.calculateFee(Integer.parseInt(count))));
                System.out.printf("Reservation booked for %s, by %s \n\n",showing.getMovie().getTitle(),customer);
            }
        } catch (Exception e) {
            scanner.close();
        }
    }
}
