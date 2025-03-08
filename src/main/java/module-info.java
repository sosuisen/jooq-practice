module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jooq;
    requires org.jooq.meta;

    opens com.example to javafx.fxml, javafx.graphics;
    opens jooq.tables.pojos to org.jooq;
    // jOOQの内部クラスが `jooq.tables.records` にアクセスできるようにする
    exports jooq.tables.records to org.jooq;
}
