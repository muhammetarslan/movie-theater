package com.jpmc.theater.utilsTests;

import com.jpmc.theater.utils.Helper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelperTests {


    @Test
    public void testSaveReservation() throws IOException {
        StringBuilder stringBuilderReadBeforeNewRes = readReservationsCsvFile("src"+File.separator+"main"+File.separator+"resources"+File.separator+"reservations.csv");
        Helper.saveReservation("test1","test2","test3","test4","test5");
        StringBuilder stringBuilderReadAfterNewRes = readReservationsCsvFile("src"+File.separator+"main"+File.separator+"resources"+File.separator+"reservations.csv");
        String lastReservationMade = getLastReservationMade(stringBuilderReadAfterNewRes);
        File file = new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"reservations.csv");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(stringBuilderReadBeforeNewRes.toString()); //clean up the test data
        fileWriter.close();
        assertEquals(lastReservationMade.trim(),"test1,test2,test3,test4,test5");
    }

    private String getLastReservationMade(StringBuilder stringBuilder){
        String commaSeperatedStrings[] = stringBuilder.toString().split(",");
        return commaSeperatedStrings[commaSeperatedStrings.length-5]
                        +","+commaSeperatedStrings[commaSeperatedStrings.length-4]
                        +","+commaSeperatedStrings[commaSeperatedStrings.length-3]
                        +","+commaSeperatedStrings[commaSeperatedStrings.length-2]
                        +","+commaSeperatedStrings[commaSeperatedStrings.length-1];

    }
    private StringBuilder readReservationsCsvFile(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fileInputStream =
                new FileInputStream(path);
        int i = 0;
        while ((i = fileInputStream.read())!=-1){
            stringBuilder.append((char)i);
        }
        fileInputStream.close();
        return stringBuilder;
    }
}
