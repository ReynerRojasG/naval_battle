package com.mycompany.naval_battle;

import classes.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController implements Initializable {

    @FXML
    private Button play_btn;
    @FXML
    private TextField name_tf;
    @FXML
    private Button create_btn;
    @FXML
    private Button back_btn;
    public Player currentPlayer;
    @FXML
    private ComboBox<String> dificulty_cb;
    @FXML
    private Label created_lb;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dificulty_cb.getItems().addAll("Facil", "Intermedio", "Dificil");
        created_lb.setVisible(false);
    }    

    @FXML
    private void goToPlay(ActionEvent event) throws IOException {
       created_lb.setVisible(false);
        App.setRoot("game");
    }

    @FXML
    private void createPlayer(ActionEvent event) {
       String namePlayer = name_tf.getText();
       String dificulty = dificulty_cb.getValue();
       currentPlayer = new Player(namePlayer, dificulty);
       App.player = currentPlayer;
       created_lb.setVisible(true);
    }
    
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }   
}
