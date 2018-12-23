package eu.domroese.openstoragemanagement;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    private TextField txtComponentName;

    private TextField txtComponentDescription;

    private TextField txtComponentAmount;

    public void handleAddStorageContainer(){
        System.out.println("handleAddStorageContainer");
    }

    public void handleAddStorageCompartment(){
        System.out.println("handleAddStorageCompartment");
    }

    public void handleAddStorageContent(){
        System.out.println("handleAddStorageContent");
        try {
            StorageContentDatabase db = StorageContentDatabase.getInstance();
            db.saveStorageContent("Test", "testdescription", 1, "asdasd", "asdasd");
        } catch(Exception e){
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    public void handleSortList(){System.out.println("handleSortList");
    }


    public TextField getTxtComponentName() {
        return txtComponentName;
    }

    public void setTxtComponentName(TextField txtComponentName) {
        this.txtComponentName = txtComponentName;
    }

    public TextField getTxtComponentDescription() {
        return txtComponentDescription;
    }

    public void setTxtComponentDescription(TextField txtComponentDescription) {
        this.txtComponentDescription = txtComponentDescription;
    }

    public TextField getTxtComponentAmount() {
        return txtComponentAmount;
    }

    public void setTxtComponentAmount(TextField txtComponentAmount) {
        this.txtComponentAmount = txtComponentAmount;
    }
}
