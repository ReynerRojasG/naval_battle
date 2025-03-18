package com.mycompany.naval_battle;

import classes.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class GameController implements Initializable {

    @FXML
    private Button back_btn;
    @FXML
    private Label namePlayerGrid_lb;
    @FXML
    private Label dificultyGrid_lb;
    @FXML
    private ImageView imagen;
    public Player currentPlayer;
    @FXML
    private GridPane battleCamp1_gp;
    @FXML
    private Pane submarine_pane;
    @FXML
    private Pane destructor_pane;
    @FXML
    private Pane cruise_pane;
    @FXML
    private Pane battleship_pane;
    private Pane draggedPane;
    private Pane selectedPane;
    private final int TILE_SIZE = 40;
    @FXML
    private Button rotate_btn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Player currentPlayer = App.player;
        System.out.println(currentPlayer.getName() + " " + currentPlayer.getDificulty());
        namePlayerGrid_lb.setText(currentPlayer.getName());
        dificultyGrid_lb.setText(currentPlayer.getDificulty());
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }  

    @FXML
    private void handlePaneClick(MouseEvent event) {
        selectedPane = (Pane) event.getSource();
    }

    @FXML
    private void handleDragDetected(MouseEvent event) {
        draggedPane = (Pane) event.getSource(); 
        Dragboard db = draggedPane.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(draggedPane.getId());
        db.setContent(content);
        event.consume();
    }

    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() instanceof Pane) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }
    
    private boolean isCollision(int newColumn, int newRow, int colSpan, int rowSpan) {
    for (Pane pane : new Pane[]{submarine_pane, destructor_pane, cruise_pane, battleship_pane}) {
        if (pane != draggedPane) { // Evitar comparar con el pane en movimiento
            Integer paneColumn = GridPane.getColumnIndex(pane);
            Integer paneRow = GridPane.getRowIndex(pane);
            int paneColSpan = GridPane.getColumnSpan(pane) != null ? GridPane.getColumnSpan(pane) : 1;
            int paneRowSpan = GridPane.getRowSpan(pane) != null ? GridPane.getRowSpan(pane) : 1;

            if (paneColumn != null && paneRow != null) {
                // Verificar si hay solapamiento entre panes
                boolean overlapX = (newColumn < paneColumn + paneColSpan) && (newColumn + colSpan > paneColumn);
                boolean overlapY = (newRow < paneRow + paneRowSpan) && (newRow + rowSpan > paneRow);

                if (overlapX && overlapY) {
                    return true; // Hay colisión
                }
            }
        }
    }
    return false; // No hay colisión
}

    
   @FXML
    private void handleDragDropped(DragEvent event) {
        if (draggedPane != null) {
            int newColumn = (int) (event.getX() / TILE_SIZE);
            int newRow = (int) (event.getY() / TILE_SIZE);

            int colSpan = GridPane.getColumnSpan(draggedPane) != null ? GridPane.getColumnSpan(draggedPane) : 1;
            int rowSpan = GridPane.getRowSpan(draggedPane) != null ? GridPane.getRowSpan(draggedPane) : 1;

            // Evitar que se salga del grid
            int maxCols = 10 - colSpan;
            int maxRows = 10 - rowSpan;
            newColumn = Math.max(0, Math.min(newColumn, maxCols));
            newRow = Math.max(0, Math.min(newRow, maxRows));

            // Verificar colisión con otros Pane
            if (!isCollision(newColumn, newRow, colSpan, rowSpan)) {
                GridPane.setColumnIndex(draggedPane, newColumn);
                GridPane.setRowIndex(draggedPane, newRow);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
        }
        event.consume();
    }


    @FXML
    private void rotateSelectedPane(ActionEvent event) {
        if (selectedPane != null) {
            Integer colSpan = GridPane.getColumnSpan(selectedPane) != null ? GridPane.getColumnSpan(selectedPane) : 1;
            Integer rowSpan = GridPane.getRowSpan(selectedPane) != null ? GridPane.getRowSpan(selectedPane) : 1;
            
            GridPane.setColumnSpan(selectedPane, rowSpan);
            GridPane.setRowSpan(selectedPane, colSpan);
            
            double tempWidth = selectedPane.getPrefWidth();
            double tempHeight = selectedPane.getPrefHeight();
            selectedPane.setPrefWidth(tempHeight);
            selectedPane.setPrefHeight(tempWidth);
        }
    }   
}
