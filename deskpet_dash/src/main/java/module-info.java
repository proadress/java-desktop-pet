module org.example.deskpet_dash {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.deskpet_dash to javafx.fxml;
    exports org.example.deskpet_dash;
}