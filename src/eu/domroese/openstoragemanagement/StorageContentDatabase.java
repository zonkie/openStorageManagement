package eu.domroese.openstoragemanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StorageContentDatabase {

    private static StorageContentDatabase instance = null;

    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private List<StorageContent> contents = new ArrayList<>();
    private Properties config = null;
    private Connection connection = null;

    private StorageContentDatabase() throws Exception {
        ConfigHandler conf = ConfigHandler.getInstance();
        this.config = conf.loadConfig();
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.config.getProperty("mysqlHost") + "/openStorageManagement?user=" + this.config.getProperty("mysqlUser") + "&password=" + this.config.getProperty("mysqlPassword") + "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        } catch(Exception e){
            System.out.println("An exception occured while inserting the stored component");
            System.out.println(e.getMessage());
            close();
            throw e;
        }
    }

    public static StorageContentDatabase getInstance() throws Exception{
        if (instance == null) {
            instance = new StorageContentDatabase();
        }

        return instance;
    }

    public void saveStorageContent(
            String name,
            String description,
            Integer amount,
            String storageContainerId,
            String storageCompartmentId
    ) throws Exception {
        try {
            /**
             * CREATE TABLE `storageContent` (
             *   `uniqueid` varchar(255) NOT NULL DEFAULT '',
             *   `name` varchar(255) DEFAULT NULL,
             *   `description` varchar(255) DEFAULT NULL,
             *   `amount` decimal(9,2) DEFAULT NULL,
             *   `storagecontainerid` varchar(255) DEFAULT NULL,
             *   `storagecompartmentid` varchar(255) DEFAULT NULL,
             *   PRIMARY KEY (`uniqueid`)
             * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
             */
            preparedStatement = this.connection.prepareStatement("insert into  openStorageManagement.storageContent values (uuid(), ?, ?, ?, ? , ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, amount);
            preparedStatement.setString(4, storageContainerId);
            preparedStatement.setString(5, storageCompartmentId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("An exception occured while inserting the stored component");
            System.out.println(e.getMessage());
            close();
            throw e;
        }
    }

    public List<StorageContent> readAll() throws Exception {

        try {
            // Statements allow to issue SQL queries to the database
            statement = this.connection.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from openStorageManagement.storageContent");

            return this.createListFromResultSet(resultSet);
        } catch (Exception e) {
            System.out.println("An exception occured while loading the stored components");
            System.out.println(e.getMessage());
            close();
            throw e;
        }
    }

    private List<StorageContent> createListFromResultSet(ResultSet resultset) throws SQLException {

        List<StorageContent> contents = new ArrayList<>();
        while (resultSet.next()) {
            String uniqueId = resultSet.getString("uniqueId");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Integer amount = resultSet.getInt("amount");
            String storageContainerId = resultSet.getString("storageContainerId");
            String storageCompartmentId = resultSet.getString("storageCompartmentId");

            contents.add(new StorageContent(uniqueId, name, description, amount, storageContainerId, storageCompartmentId));
        }

        this.contents = contents;

        return this.contents;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (this.connection != null) {
                this.connection.close();
            }
        } catch (Exception e) {

        }
    }
}
