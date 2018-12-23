package eu.domroese.openstoragemanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class StorageContainerDatabase {

    private static StorageContainerDatabase instance = null;


    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private List<StorageContainer> container = new ArrayList<>();
    private Properties config = null;
    private Connection connection = null;
    /**
     * uuid of the storage container
     */
    private String uniqueId;
    /**
     * Short description of the storage container
     */
    private String name;
    /**
     * long description of the storage container
     */
    private String description;
    /**
     * wher do you find the storage container ?
     */
    private String location;

    private StorageContainerDatabase() throws Exception {
        ConfigHandler conf = ConfigHandler.getInstance();
        this.config = conf.loadConfig();
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.config.getProperty("mysqlHost") + "/openStorageManagement?user=" + this.config.getProperty("mysqlUser") + "&password=" + this.config.getProperty("mysqlPassword") + "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        } catch (Exception e) {
            System.out.println("An exception occured while inserting the stored component");
            System.out.println(e.getMessage());
            close();
            throw e;
        }
    }

    public static StorageContainerDatabase getInstance() throws Exception {
        if (instance == null) {
            instance = new StorageContainerDatabase();
        }

        return instance;
    }

    public void saveStorageContent(
            String name,
            String description,
            String location,
            Integer number,
            Integer rows,
            Integer columns
    ) throws Exception {
        try {
            /**
             * CREATE TABLE `storageContainer` (
             *   `uniqueid` varchar(255) NOT NULL DEFAULT '',
             *   `name` varchar(255) NOT NULL DEFAULT '',
             *   `description` varchar(255) DEFAULT NULL,
             *   `location` varchar(255) DEFAULT NULL,
             *   `number` decimal(9,2) DEFAULT NULL,
             *   `rows` decimal(9,2) DEFAULT NULL,
             *   `columns` decimal(9,2) DEFAULT NULL,
             *   PRIMARY KEY (`uniqueid`)
             * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
             */
            preparedStatement = this.connection.prepareStatement("insert into  openStorageManagement.storageContainer values (uuid(), ?, ?, ?, ?, ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, location);
            preparedStatement.setInt(4, number);
            preparedStatement.setInt(5, rows);
            preparedStatement.setInt(6, columns);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("An exception occured while inserting the storage container");
            System.out.println(e.getMessage());
            close();
            throw e;
        }
    }

    public List<StorageContainer> readAll() throws Exception {

        try {
            // Statements allow to issue SQL queries to the database
            statement = this.connection.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from openStorageManagement.storageContainer");

            return this.createListFromResultSet(resultSet);
        } catch (Exception e) {
            System.out.println("An exception occured while loading the stored components");
            System.out.println(e.getMessage());
            close();
            throw e;
        }
    }

    private List<StorageContainer> createListFromResultSet(ResultSet resultset) throws SQLException {

        List<StorageContainer> container = new ArrayList<>();
        while (resultSet.next()) {
            String uniqueId = resultSet.getString("uniqueId");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            Integer number = resultSet.getInt("number");
            Integer compartmentCount = resultSet.getInt("compartmentCount");
            Integer rows = resultSet.getInt("rows");
            Integer columns = resultSet.getInt("columns");
            System.out.println("Container: " + name);
            container.add(new StorageContainer(uniqueId, name, description, location, number, compartmentCount, rows, columns));
        }

        this.container = container;
        return this.container;
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
