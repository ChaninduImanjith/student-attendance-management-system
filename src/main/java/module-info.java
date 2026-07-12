module com.sams {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.sams.controller to javafx.fxml;
    opens com.sams.entity to org.hibernate.orm.core;
    
    exports com.sams;
}
