package eu.domroese.openstoragemanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StorageCompartmentDatabase {

    private static StorageCompartmentDatabase instance = null;


    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private List<StorageCompartment> compartments = new ArrayList<>();
    private Properties config = null;
    private Connection connection = null;

    private StorageCompartmentDatabase() throws Exception {
        ConfigHandler conf = ConfigHandler.getInstance();
        this.config = conf.loadConfig();
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.config.getProperty("mysqlHost") + "/openStorageManagement?user=" + this.config.getProperty("mysqlUser") + "&password=" + this.config.getProperty("mysqlPassword") + "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        } catch (Exception e) {
            System.out.println("An exception occured while inserting the stored component");
            System.out.println(e.getMessage());
            close();
            throw e;
        }}

    public static StorageCompartmentDatabase getInstance() throws Exception {
        if (instance == null) {
            instance = new StorageCompartmentDatabase();
        }

        return instance;
    }

    public void saveStorageCompartment(
            String name,
            String description,
            String storageContainerId
    ) throws Exception {
        try {
            /**
             * CREATE TABLE `storageCompartment` (
             *   `uniqueid` varchar(255) NOT NULL DEFAULT '',
             *   `name` varchar(255) DEFAULT NULL,
             *   `description` varchar(255) DEFAULT NULL,
             *   `storageContainerid` varchar(255) DEFAULT NULL,
             *   PRIMARY KEY (`uniqueid`)
             * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
             */
            preparedStatement = this.connection.prepareStatement("insert into  openStorageManagement.storageContent values (uuid(), ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, storageContainerId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("An exception occured while inserting the stored component");
            System.out.println(e.getMessage());
            close();
            throw e;
        }
    }

    public List<StorageCompartment> readAll() throws Exception {

        try {
            // Statements allow to issue SQL queries to the database
            statement = this.connection.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from openStorageManagement.storageCompartment");

            return this.createListFromResultSet(resultSet);
        } catch (Exception e) {
            System.out.println("An exception occured while loading the stored components");
            System.out.println(e.getMessage());
            close();
            throw e;
        }
    }

    private List<StorageCompartment> createListFromResultSet(ResultSet resultset) throws SQLException {

        List<StorageCompartment> compartments = new ArrayList<>();
        while (resultSet.next()) {
            String uniqueId = resultSet.getString("uniqueId");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String storageContainerId = resultSet.getString("storageContainerId");

            compartments.add(new StorageCompartment(uniqueId, name, description, storageContainerId));
        }

        this.compartments = compartments;

        return this.compartments;
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
