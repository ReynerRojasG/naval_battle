module com.mycompany.naval_battle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.naval_battle to javafx.fxml;
    exports com.mycompany.naval_battle;
}
