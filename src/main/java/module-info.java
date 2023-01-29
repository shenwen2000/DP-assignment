module com.farm.dp_assignment {
    requires javafx.controls;
    requires javafx.fxml;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;

    opens com.farm.dp_assignment to javafx.fxml;
    exports com.farm.dp_assignment;
}