module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
    requires java.desktop;
    requires org.mybatis;
    opens org.data.Entity;
    opens org.data.Entity2;
    opens org.data.Mapper;
    opens org.data.materialFX;
    requires MaterialFX;

    requires fr.brouillard.oss.cssfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    // right key scenicview.jar , and add as library
//    requires org.scenicview.scenicview;

}