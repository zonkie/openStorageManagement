package eu.domroese.openstoragemanagement;

import java.util.UUID;

public class StorageCompartment {

     /** uuid of the storage compartment */
    private String uniqueId;

    /** Short description of the storage compartment */
    private String name;

    /** long description of the storage compartment */
    private String description;

    /** uuid of the storageContainer this compartment is stored in */
    private String storageContainerId;


    public StorageCompartment(String uniqueId, String name, String description, String storageContainerId) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.description = "";
        this.storageContainerId = storageContainerId;
    }

    public StorageCompartment(String name, String storageContainerId) {
        this.uniqueId = UUID.randomUUID().toString();
        this.name = name;
        this.description = "";
        this.storageContainerId = storageContainerId;
    }

    public StorageCompartment(String name, String description, String storageContainerId) {
        this.uniqueId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.storageContainerId = storageContainerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStorageContainerId() {
        return storageContainerId;
    }

    public void setStorageContainerId(String storageContainerId) {
        this.storageContainerId = storageContainerId;
    }
}
