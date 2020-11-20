
package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Mailbox;

/**
 *
 * @author cge19
 * I referenced sample code that was provided
 */
public class DetailModelController {
    

    @FXML
    private Button backButton; // Value injected by FXMLLoader

    @FXML 
    private Label labelID; // Value injected by FXMLLoader

    @FXML 
    private Label labelValue; // Value injected by FXMLLoader

    @FXML 
    private ImageView image; // Value injected by FXMLLoader

    
    @FXML
    void backButtonAction(ActionEvent event) {
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (previousScene != null) {
            stage.setScene(previousScene);
        }

    }

    Mailbox selectedModel;
    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

    }

    public void initData(Mailbox model) {
        selectedModel = model;
        labelID.setText(model.getCanvasaccountid().toString());
        labelValue.setText(model.getEmailsender());

        try {
            String imagename = "/resource/images/" + model.getEmailsender() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML // Got from sample code
    void initialize() {
        assert backButton != null : "fx:id=\"backButtong\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert labelID != null : "fx:id=\"labelID\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert labelValue != null : "fx:id=\"labelValue\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'DetailModelView.fxml'.";

        backButton.setDisable(true);

    }
}
