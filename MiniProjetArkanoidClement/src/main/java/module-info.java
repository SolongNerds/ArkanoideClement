module org.example.miniprojetarkanoidclement {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.miniprojetarkanoidclement to javafx.fxml;
    exports org.example.miniprojetarkanoidclement;
}