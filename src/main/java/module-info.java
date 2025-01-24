module com.example.informesgabrielaworld {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;


    opens com.example.informesgabrielaworld to javafx.fxml;
    exports com.example.informesgabrielaworld;
}