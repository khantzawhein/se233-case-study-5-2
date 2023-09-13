module com.se233.chapter5_tdd {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;


    opens com.se233.chapter5_tdd to javafx.fxml;
    exports com.se233.chapter5_tdd;
}