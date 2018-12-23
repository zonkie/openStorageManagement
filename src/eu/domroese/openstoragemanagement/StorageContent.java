package eu.domroese.openstoragemanagement;

import java.util.UUID;

public class StorageContent {

    /** uuid of the stored component */
    private String uniqueId;

    /** Short description of the stored Component */
    private String name;

    /** long description of the stored component */
    private String description;

    /** how many of these parts are in the compartment */
    private Integer amount;

    /** uuid of storageContainer */
    private String storageContainerId;

    /** uuid of storageCompartment */
    private String storageCompartmentId;

    public StorageContent(String name, String description) {
        this.uniqueId = UUID.randomUUID().toString();

        this.name = name;
        this.description = description;
    }

    public StorageContent
            (String name, String description, Integer amount) {
        this.uniqueId = UUID.randomUUID().toString();

        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public StorageContent(String name, String description, Integer amount, String storageContainerId, String storageCompartmentId) {
        this.uniqueId = UUID.randomUUID().toString();

        this.name = name;
        this.description = description;
        this.amount = amount;
        this.storageContainerId = storageContainerId;
        this.storageCompartmentId = storageCompartmentId;
    }

    public StorageContent(String uniqueId, String name, String description, Integer amount, String storageContainerId, String storageCompartmentId) {

        this.uniqueId = uniqueId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.storageContainerId = storageContainerId;
        this.storageCompartmentId = storageCompartmentId;
    }

    public String getUniqueId() {
        return uniqueId;
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

    public String getAmount() {
        return String.valueOf(amount);
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStorageContainerId() {
        return storageContainerId;
    }

    public void setStorageContainerId(String storageContainerId) {
        this.storageContainerId = storageContainerId;
    }

    public String getStorageCompartmentId() {
        return storageCompartmentId;
    }

    public void setStorageCompartmentId(String storageCompartmentId) {
        this.storageCompartmentId = storageCompartmentId;
    }
}
