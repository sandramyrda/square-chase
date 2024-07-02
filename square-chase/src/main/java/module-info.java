module io.nology {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens io.nology to javafx.fxml;

    exports io.nology;
}
