package com.mycompany.naval_battle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MenuController implements Initializable {

    @FXML
    private Button register_btn;
    @FXML
    private Button tutorial_btn;
    @FXML
    private Button credits_btn;
    @FXML
    private Button quit_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToRegister(ActionEvent event) throws IOException {
        App.setRoot("register");
    }

    @FXML
    private void goToTutorial(ActionEvent event) throws IOException  {
        System.exit(0);
        App.setRoot("");
    }

    @FXML
    private void goToCredits(ActionEvent event) throws IOException  {
        System.exit(0);
        App.setRoot("");
    }

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }
    
}
