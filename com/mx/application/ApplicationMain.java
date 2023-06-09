package com.mx.application;

import com.mx.application.processor.FileProcessorService;

public class ApplicationMain {
    /*The main program calls a function that receives the filepath of the principal file that is going to be processed
     * to extract the data and put it into a database
     *@exception prints a message to the user if a problem is shown in the process to localize the file by the program
     *
     *@author Anette Larios
     *@version 1.8.0
     *@since 2023-06-06
     **/
    public static void main (String args[]) {
        try {
            // Tries to pass as reference the filepath of the file to fileOpenAndRead fuction.
            FileProcessorService.fileOpenAndRead("C:\\Users\\SpectrumByte\\eclipse-workspace\\JavaTxtProcessorWithMySQL\\src\\resources\\UsersData.txt");
            /*
             If any problem occurs while the program is trying to localize the file, a message
             will be shown.
            */
        }catch(Exception e) {
            System.out.println(e.toString());
        }

    }
}
