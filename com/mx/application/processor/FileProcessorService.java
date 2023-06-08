package com.mx.application.processor;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import java.io.BufferedReader;

import com.mx.application.model.Client;

import com.mx.application.sql.DataBaseConnection;

import com.mx.application.utils.MathUtils;
import com.mx.application.utils.StringsUtils;

/*FileProcessorService is the main class in charge to process all the data obtained from the file to be send to
 * DataBaseConnection class.
 *
 * @author Anette Larios
 * @since 2023-06-06
 */

public class FileProcessorService {

    /*fileOpenAndRead is a public function, contains processes to read each line of the file.
     * @param filePath is a string, contains the filePath of the file to be processed.
     * @exceptions Exception e shows a message is a problem is shown while the programs is trying to open and localize the file
     *
     * @author Anette Larios
     * @since 2023-06-06
     */
    public static void fileOpenAndRead(String filePath) throws IOException {

        try(FileReader fileReader = new FileReader(filePath);

            BufferedReader fileToRead = new BufferedReader(fileReader)){

            DataBaseConnection connectToDataBase = new DataBaseConnection(fileReaderProcessor(fileToRead));

        }catch(Exception e) {

            System.out.println(e.toString());
        }

    }
    /*
     * fileReaderProcessor is the main public method in charge to process the data obtained from the file
     * @param type Buffered Reader filetoRead refers to the file obtained
     * @see List <Client> clientList, is as list type Client model called clientList. It has the property to act as a dictionary.
     * @see "line" type string variable, will store all file information
     * @see datafromfile [] String array, will store all the file information separated in fields.
     * @see "tax" float variable, will store the value of all clients calculated by assignTaxAccordingToNumberRange fuction
     * @return a list type Client model previously filled by the whole method.
     *
     * @author Anette Larios
     * @since 2023-06-06
     */
    private static List<Client> fileReaderProcessor(BufferedReader fileToRead) throws IOException {

        List<Client> clientList = new ArrayList<>();

        String line;

        String[] dataFromFile;

        float tax = 0;

        while((line = fileToRead.readLine()) != null) {

            dataFromFile = line.split(",");
            /*
             * printClient call will show all the data obtained from the file
             * @param dayaFromFile is the String array previously filled with clients' information
             *
             */
            printClient(dataFromFile);

            /*
             * convertStringToFloat call is a method that receives a String located in position five on dataFromFile[]array
             * (The data it is the numberRange), the return of the function will be stored in numberRangeClients variable.
             * @param dataFromFile[5] is the numberRange from all clients
             */
            float numberRangeClients = StringsUtils.convertStringToFloat(dataFromFile[5]);

            /*
             * convertStringBalanceToFloat is an specific function made for this field, receives the balance and removes the "$"
             * symbol from the string and converts it into a float. The return of the method will be stored in balanceClientsNumber
             * float variable
             * @param dataFromFile[6] is the balance of all clients
             */
            float balanceClientsNumber = convertStringBalanceToFloat(dataFromFile[6]);

            /*
             * assignTaxAccordingToNumberRange is a function that receives the return of the two past methods to calculate the
             * tax of all clients. The return's  method will be stored in "Tax" float variable.
             * @param numberRangeClients it is the return of the last first function extracted from clients' data
             * @param balanceClientNumnber it is the return if the last second function extracted from clients' data.
             */
            tax = MathUtils.assignTaxAccordingToNumberRange(numberRangeClients, balanceClientsNumber);

            /*
             * Client is a call to Client constructor model to assign the values o each client stored in dataFromFilearray
             * @param dataFromFile[0-7] are fields of Client's model
             * @param tax is the return of the last method
             */
            clientList.add(new Client(dataFromFile[0],
                    dataFromFile[1],
                    dataFromFile[2],
                    dataFromFile[3],
                    dataFromFile[4],
                    Integer.parseInt(dataFromFile[5].toString()),
                    Float.parseFloat(dataFromFile[6].replace("$", "")),
                    dataFromFile[7],
                    tax));
        }

        return clientList;
    }

    /*
     * printClient is a method in to display all clients' data before processing.
     * @param dataFromFile[] String array stores all client data.
     *
     * @author Anette Larios
     * @since 2023-06-06
     */
    private static void printClient(String dataFromFile[]) {

        System.out.println("data name: "+ dataFromFile[0]
                +" phone: " + dataFromFile[1]
                +" address: "+ dataFromFile[2]
                +" email: "+ dataFromFile[3]
                +" country: " + dataFromFile[4]
                +" numberrange:  "+ dataFromFile[5]
                +" balance: " + dataFromFile[6]
                +" rfc: "+ dataFromFile[7]);
    }


    /*
     * convertStringBalanceToFloat is a method that receives a String, removes "$" symbol and converts it to float.
     * @param dataFromFile String is specifically the balance of all customers
     * @return dataConverted is the string converted and stored in a float variable.
     *
     * @author Anette Larios
     * @since 2023-06-06
     */
    public static float convertStringBalanceToFloat(String dataFromFile) {

        String dataToConvert = dataFromFile.replace("$", "");

        float dataConverted = Float.parseFloat(dataToConvert.toString());

        return dataConverted;
    }
}
