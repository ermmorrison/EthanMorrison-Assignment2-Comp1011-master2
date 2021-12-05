module com.example.ethanmorrisonassignment2comp1011 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens com.example.ethanmorrisonassignment2comp1011 to javafx.fxml, com.google.gson;
    exports com.example.ethanmorrisonassignment2comp1011;
    exports com.example.ethanmorrisonassignment2comp1011.Utilities;
    opens com.example.ethanmorrisonassignment2comp1011.Utilities to com.google.gson, javafx.fxml;
    exports com.example.ethanmorrisonassignment2comp1011.Controllers;
    opens com.example.ethanmorrisonassignment2comp1011.Controllers to com.google.gson, javafx.fxml;
    exports com.example.ethanmorrisonassignment2comp1011.Models;
    opens com.example.ethanmorrisonassignment2comp1011.Models to com.google.gson, javafx.fxml;
}