package com.mx.application.utils;
/*
 * MathUtils is a class that stores numerous math methods applied  to the program
 *
 * @author Anette Larios
 * @since 2023-06-06
 */
public class MathUtils {

    /*
     * assignTaxAccordingToNumberRange is a method that calculates the tax of each customer according to their numberRange and balance
     * @param numberRangeClients is a float extracted from Clients' file previously processed by convertDataToFloat function
     * @param balanceClientsNumber is a float extracted from Clients' file previously processed by convertBalanceToFloat function
     * @return tax is a float variable that stores the calculated tax by the method according to the number range and
     * balanceClientsNumber
     *
     * @author Anette Larios
     * @since 2023-06-06
     */
    public static float assignTaxAccordingToNumberRange(float numberRangeClients, float balanceClientsNumber){

        float tax = 0;

        if(numberRangeClients == 0) {
            tax = 0;
        }
        else if(numberRangeClients == 1) {
            tax = (balanceClientsNumber) * Float.valueOf("0.10");
        }
        else if(numberRangeClients == 2) {
            tax = (balanceClientsNumber) * Float.valueOf("0.11");
        }
        else if(numberRangeClients == 3) {
            tax = (balanceClientsNumber) * Float.valueOf("0.12");
        }
        else if(numberRangeClients >= 4) {
            tax = (balanceClientsNumber) * Float.valueOf("0.15");
        }
        return tax;
    }

}