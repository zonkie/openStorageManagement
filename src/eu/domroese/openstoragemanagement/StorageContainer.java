package eu.domroese.openstoragemanagement;

import java.util.UUID;

public class StorageContainer {

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

    /**
     * If more than one storage container (e.G. storage boxes with slides) are available, this is the umber of the container
     */
    private Integer number;

    /**
     * Total Count of Compartments
     */
    private Integer compartmentCount;

    /**
     * rows (cupboards, rows of slides) available in the storage container
     */
    private int rows;

    /**
     * columns available in the storage container
     */
    private int columns;

    public StorageContainer(String uniqueId, String name, String description, String location, Integer number, int compartmentCount, int rows, int columns) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.number = number;
        this.compartmentCount = compartmentCount;
        this.rows = rows;
        this.columns = columns;
    }

    public StorageContainer(String name, String description, String location, Integer number, int rows, int columns) {
        this.uniqueId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.location = location;
        this.number = number;
        this.rows = rows;
        this.columns = columns;
    }

    public StorageContainer(String name, String description, String location) {
        this.uniqueId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.location = location;
        this.rows = 1;
        this.columns = 1;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getCompartmentCount() {
        return compartmentCount;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
