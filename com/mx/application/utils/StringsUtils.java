package com.mx.application.utils;
    /*
    StringUtils contanis general methods that can be applied to strings.

    @author Anette Larios
    since 23-06-06
     */
public class StringsUtils {
    /*
     * convertStringToFloat is  a general method that can convert a String to a float
     * @param dataFromFile is expected to be specifically the numberRange clients' field
     * @return dataConverted is a float variable, stores the previous string converted to float.
     *
     * @author Anette Larios
     * since 2023-06-06
     */
    public static float convertStringToFloat(String dataFromFile) {

        float dataConverted = Float.parseFloat(dataFromFile.toString());

        return dataConverted;
    }

}
