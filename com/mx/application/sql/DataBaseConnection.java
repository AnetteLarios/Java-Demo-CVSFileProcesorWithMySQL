package com.mx.application.sql;

import java.sql.PreparedStatement;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.List;

import java.sql.Connection;

import javax.swing.JOptionPane;

import com.mx.application.model.Client;

/*Data Base Connection is the main class in charge of making the connection with the data base
 *
 * @see url, user, driver and pass strings, which are the keys to make the connection with the data base.
 * @see insertStamentSql a String variable that contains a SQL command to insert data into teh database and can be filled
 * with the processed data from the file
 *
 * @author Anette Larios
 * @since 2023-06-06
 */

public class DataBaseConnection {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/bank_data_sql";
    private String user = "root";
    private String pass = "12345678";
    Connection connection = null;
    String insertStatementSql = "INSERT INTO customers (name, phone_number, email, country, number_range, balance, rfc, tax) "
            + "VALUES (?,?,?,?,?,?,?,?) ";

    /*Data BaseConnection is the constructor of DataBaseConnection
     * This constructor is the one who takes the data from the class and makes the connection with the database.
     * @param List<Client> clientList is a list of Client class model called clientList that was previously filled and sent by the
     * FileReaderProcessor in FileProcessorService Class
     * @see connection which is an object specificly made to do the connection with the database receiving login data.
     * @see JOptionPane.showMessageDialog is a class, it's porpose is to show a dialog message if the connection with the database
     * is successful
     * @see insertClientData which is a object of Prepared Statement class in charge to insert the statement in "insertStatementSql"
     * Once this command is send to the database will expect to receive the data that was specified in the message
     * @see loop for(Client clients : clientList) iterates in the elements of type Client in the client list and are
     * inserted in the database according to the order the fields are in the sql statement
     * Once all clients were inserted @see insertClientData.close() and connection.close() to conclude the insert process.
     * @exceptions SQLException e prints a message if a mistake is shown
     * @exceptions Exception f prints a message if any another mistake in DataBaseConnection Constructor is shown
     *
     * @author Anette Larios
     * @since 2023-06-06
     */

    public DataBaseConnection(List<Client> clientList){

        try {

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "Connected successfully.");

            PreparedStatement insertClientData = connection.prepareStatement(insertStatementSql);

            for(Client clients : clientList) {
                insertClientData.setString(1, clients.getName());
                insertClientData.setString(2, clients.getPhone());
                insertClientData.setString(3, clients.getEmail());
                insertClientData.setString(4, clients.getCountry());
                insertClientData.setInt(5, clients.getNumberRange());
                insertClientData.setFloat(6, clients.getBalance());
                insertClientData.setString(7, clients.getRfc());
                insertClientData.setFloat(8, clients.getTax());
                insertClientData.executeUpdate();

            }
            JOptionPane.showMessageDialog(null, "Client List Inserted in Data Base Successfully");
            insertClientData.close();
            connection.close();


        }catch(SQLException e){
            System.out.println(e.toString());
        }catch (Exception f) {
            System.out.println(f.toString());
        }finally {

        }

    }
}