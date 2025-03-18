package classes;

import javafx.scene.control.Button;

public class Ship {
    private String name;        // Nombre del barco (Ejemplo: Submarino, Destructor)
    private int size;           // Número de casillas que ocupa el barco
    private int startX;         // Posición inicial en el GridPane (columna)
    private int startY;         // Posición inicial en el GridPane (fila)
    private boolean isVertical; // Orientación: true = vertical, false = horizontal
    private int hitsTaken;      // Cantidad de impactos recibidos
    private boolean isSunk;     // Indica si el barco está hundido
    private Button button;      // Botón asociado al barco en la interfaz gráfica
    
    // Constructor
    public Ship(String name, int size, int startX, int startY, boolean isVertical, Button button) {
        this.name = name;
        this.size = size;
        this.startX = startX;
        this.startY = startY;
        this.isVertical = isVertical;
        this.hitsTaken = 0;
        this.isSunk = false;
        this.button = button;
    }

    // Métodos para registrar un impacto en el barco
    public void hit() {
        hitsTaken++;
        if (hitsTaken >= size) {
            isSunk = true;
        }
    }

    // Getters y Setters
    public String getName() { return name; }
    public int getSize() { return size; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public boolean isVertical() { return isVertical; }
    public boolean isSunk() { return isSunk; }
    public Button getButton() { return button; }

    public void setVertical(boolean vertical) { this.isVertical = vertical; }
    public void setStartPosition(int x, int y) { 
        this.startX = x; 
        this.startY = y; 
    }
}
