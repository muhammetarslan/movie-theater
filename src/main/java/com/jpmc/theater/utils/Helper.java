package com.jpmc.theater.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Helper {
    public static void saveReservation(String customerId, String customerName, String movieTitle,String movieDate, String pricePaid){
        File file = new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"reservations.csv");
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.append("\n"+customerId+","+customerName+","+movieTitle+","+movieDate+","+pricePaid+",");
            fileWriter.close();
        } catch (IOException e){
            System.out.println("reservations.csv file wasn't found. Please check the Helper class");
            e.printStackTrace();
        }
    }
}
