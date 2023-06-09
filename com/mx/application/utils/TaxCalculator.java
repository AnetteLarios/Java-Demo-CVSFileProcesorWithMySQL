package com.mx.application.utils;
/*
 * MathUtils is a class that stores numerous math methods applied  to the program
 *
 * @author Anette Larios
 * @since 2023-06-06
 */
public class TaxCalculator {

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

        /*
        By default, all customers have a tax of 0. But once their information is obtained,
        it is required to calculate a proper tax for every client according to their number +
        range and balance.
         */
        float tax = 0;
        //If the number range of the clients is equal to 0, the tax is 0.
        if(numberRangeClients == 0) {
            tax = 0;
        }
        /*
        If the number Range of the clients is equal to 1, the tax is the result of the balance
        multiplied by 0.10.
         */
        else if(numberRangeClients == 1) {
            tax = (balanceClientsNumber) * Float.valueOf("0.10");
        }
        /*
        If the number range of the clients is equal to 2, the tax is the result of the balance
        multiplied by 0.11.
         */
        else if(numberRangeClients == 2) {
            tax = (balanceClientsNumber) * Float.valueOf("0.11");
        }
        /*
        If the number range of the clients is equal to 3, the tax is the result of the balance
        multiplied by 0.12
         */
        else if(numberRangeClients == 3) {
            tax = (balanceClientsNumber) * Float.valueOf("0.12");
        }
        /*
        If the number range is equal to 4 or bigger, the tax is the result of the balance
        multiplied by 0.15
        */
        else if(numberRangeClients >= 4) {
            tax = (balanceClientsNumber) * Float.valueOf("0.15");
        }
        //Returns the tax according to client's data.
        return tax;
    }

}