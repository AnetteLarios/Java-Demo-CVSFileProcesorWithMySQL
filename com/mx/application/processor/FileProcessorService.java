package com.mx.application.processor;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import java.io.BufferedReader;

import com.mx.application.model.Client;

import com.mx.application.sql.DataBaseConnection;

import com.mx.application.utils.TaxCalculator;
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

        //Try to create a fileReader object for file in the given filepath
        try(FileReader fileReader = new FileReader(filePath);
            /*
            Try to create a buffered reader object called fileToRead for the same file.
            This allow us to read the file line by line.
             */
            BufferedReader fileToRead = new BufferedReader(fileReader)){

            /*
            Try to create a DataBaseConnection object to make the DataBaseConnection with the
            file and calls fileReaderProcessor fuction passing as reference the buffered reader object
            that contains the file to read.
             */
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
        /*
        Create a list of type client called clientList that will work as a dictionary.
        Client data will be stored here.
         */
        List<Client> clientList = new ArrayList<>();

        //String called line declared. The lines of the file will be stored here.
        String line;
        /*
        Array of Strings declared, named dataFromFile. Here the information line by line
        will be stored. One per array position.
         */
        String[] dataFromFile;

        /*
        Float variable called tax initialized in 0. The tax will be calculated per client
        with clients' data
        */
        float tax = 0;

        /*
        While is not the end of the file, line will be equal to the object in charge
        to read the lines of the file.
         */
        while((line = fileToRead.readLine()) != null) {
            /*
            line contains all the data of the customers, but we need to separate it by fields.
            In the txt file, the fields are separated by ",". This fuction splits lines detectin ","
            and stores them in array's positions one by one.
            This will be made line by line.  (Client by client).
             */
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
            tax = TaxCalculator.assignTaxAccordingToNumberRange(numberRangeClients, balanceClientsNumber);

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
        //Once all the clients are added to the list, the function returns the list.
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
        /*
        Converts in one string everyline readed. In this case, in one line is located
        all the information of one client, this applies for all clients.
        */

        String dataByClient = String.format("data name: %s " +
                        "phone: %s " +
                        "address: %s " +
                        "email: %s " +
                        "country: %s" +
                        "number range: %s " +
                        "balance: %s " +
                        "rfc: %s",
                        dataFromFile[0],
                        dataFromFile[1],
                        dataFromFile[2],
                        dataFromFile[3],
                        dataFromFile[4],
                        dataFromFile[5],
                        dataFromFile[6],
                        dataFromFile[7]);
        System.out.println(dataByClient);
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
        /*
        The String that the function receives is read by replace function, and it will
        replace the "$" symbol by blank spaces.
        This has to be done to calculate the tax.
         */
        String dataToConvert = dataFromFile.replace("$", "");
        // After the special character is removed, the string is converted to float.
        float dataConverted = Float.parseFloat(dataToConvert.toString());
        //The function returns the String converted to float
        return dataConverted;
    }
}
