module ovh.fejker.lottostatistics {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires org.jsoup;
    requires org.json;
    requires org.apache.commons.io;
    requires java.net.http;

    opens ovh.fejker.lottostatistics to javafx.fxml;
    exports ovh.fejker.lottostatistics;
}