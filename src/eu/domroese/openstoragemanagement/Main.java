package eu.domroese.openstoragemanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main extends Application {
    private TableView componentsTable = new TableView();
    private TableView containerTable = new TableView();
    private TableView compartmentsTable = new TableView();
    private TextField txtComponentName;
    private TextField txtComponentDescription;
    private TextField txtComponentAmount;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ConfigHandler conf = ConfigHandler.getInstance();

            Properties config = conf.loadConfig();

            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("openStorageManagement.fxml"));
            //Controller storageController = loader.getController();


            primaryStage.setTitle("Open Storage Management");
            Scene scene = new Scene(root, 1800, 800);
            primaryStage.setScene(scene);
            this.componentsTable = (TableView<String>) scene.lookup("#tableViewPartsList");
            this.containerTable = (TableView<String>) scene.lookup("#tableViewContainers");
            this.compartmentsTable = (TableView<String>) scene.lookup("#tableviewCompartments");

            initializeContentTableColumns();
            loadContentTableData();

            initializeContainerTableColumns();
            loadContainerTableData();

            initializeCompartmentTableColumns();
            loadCompartmentTableData();
            loadContainerTableData();
            loadCompartmentTableData();

            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * load Data into tables
     */
    private void loadContentTableData() {
        List<StorageContent> contents = this.getContents();

        for (StorageContent content : contents) {
            this.componentsTable.getItems().add(content);
        }
    }

    private void loadContainerTableData() {
        List<StorageContainer> containers = this.getContainers();
        for (StorageContainer container : containers) {
            this.containerTable.getItems().add(container);
        }
    }

    private void loadCompartmentTableData() {
        List<StorageCompartment> compartments = this.getCompartments();
        for (StorageCompartment compartment : compartments) {
            this.compartmentsTable.getItems().add(compartment);
        }
    }


    private List<StorageContent> getContents() {
        try {
            StorageContentDatabase db = StorageContentDatabase.getInstance();

            return db.readAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<StorageContent> noContents = new ArrayList<>();

            return noContents;
        }
    }

    /**
     * load Data from DB end
     */

    private List<StorageContainer> getContainers() {
        try {
            StorageContainerDatabase db = StorageContainerDatabase.getInstance();
            return db.readAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<StorageContainer> noContainers = new ArrayList<>();
            return noContainers;
        }

    }

    private List<StorageCompartment> getCompartments() {
        try {
            StorageCompartmentDatabase db = StorageCompartmentDatabase.getInstance();
            return db.readAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<StorageCompartment> noCompartments = new ArrayList<>();
            return noCompartments;
        }

    }


    private void initializeContentTableColumns() {
        TableColumn uniqueId = getTableColumn("ID", "Container Id", "containerId", "uniqueId");
        TableColumn componentName = getTableColumn("componentName", "Name", "componentName", "name");
        TableColumn componentDescription = getTableColumn("componentDescription", "Description", "componentDescription", "description");
        TableColumn componentAmount = getTableColumn("componentAmount", "Amount", "componentAmount", "amount");
        TableColumn componentContainerId = getTableColumn("componentContainerId", "Container", "componentContainerId", "storageContainerId");
        TableColumn componentCompartmentId = getTableColumn("componentCompartmentId", "Compartment", "componentCompartmentId", "storageCompartmentId");

        this.componentsTable.getColumns().addAll(uniqueId, componentName, componentDescription, componentAmount, componentContainerId, componentCompartmentId);
    }

    /**
     * initialize Table columns
     */
    private void initializeContainerTableColumns() {
        TableColumn uniqueId = getTableColumn("ID", "Container Id", "containerId", "uniqueId");
        TableColumn name = getTableColumn("containerName", "Name", "containerName", "name");
        TableColumn description = getTableColumn("containerDescription", "Description", "containerDescription", "description");
        TableColumn location = getTableColumn("containerLocation", "Location", "containerLocation", "location");
        TableColumn number = getTableColumn("containerNumber", "Number", "containerNumber", "number");
        TableColumn compartmentCount = getTableColumn("compartmentCount", "Compartments", "compartmentCount", "compartmentCount");
        TableColumn rows = getTableColumn("containerRows", "Rows", "containerRows", "rows");
        TableColumn columns = getTableColumn("containerCols", "Columns", "containerColumns", "columns");

        this.containerTable.getColumns().addAll(uniqueId, name, description, location, number, compartmentCount, rows, columns);

    }

    private void initializeCompartmentTableColumns() {
        TableColumn uniqueId = getTableColumn("ID", "Compartment Id", "compartmentId", "uniqueId");
        TableColumn name = getTableColumn("Compartment Name", "Compartment Name", "compartmentName", "name");
        TableColumn description = getTableColumn("compartmentDescription", "compartment Description", "compartmentDescription", "description");
        TableColumn storageContainerId = getTableColumn("storageContainerId", "Container", "storageContainerId", "storageContainerId");

        this.compartmentsTable.getColumns().addAll(uniqueId, name, description, storageContainerId);
    }

    private TableColumn getTableColumn(String columnName, String columnTitle, String columnId, String
            propertyValueFactoryIdentifier) {
        TableColumn column = new TableColumn(columnName);
        column.setText(columnTitle);
        column.setId(columnId);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyValueFactoryIdentifier));

        return column;
    }
    /**
     * initialize Table columns end
     */
}

