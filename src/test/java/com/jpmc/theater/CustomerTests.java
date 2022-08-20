package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTests {
    @Test
    public void getSetEqualsTests(){
        Customer customer1 = new Customer("ray","qwe");
        Customer customer2 = new Customer("asd","ewq");
        customer2.setId("qwe");
        customer2.setName("ray");
        assertTrue(customer1.equals(customer2));
    }
}
