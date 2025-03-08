package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import jooq.tables.daos.TodosDao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var url = "jdbc:sqlite:./todoapp.db";
        try (Connection conn = DriverManager.getConnection(url)) {           
            var configuration = new DefaultConfiguration().set(conn).set(SQLDialect.SQLITE);
            var dao = new TodosDao(configuration);
            var todos = dao.findAll();
            for (var todo: todos) {
                System.out.println(todo.toString());
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        // Initialise the DAO with the Configuration
        
        /*
         * var url = "jdbc:sqlite:./todoapp.db";
         * try (Connection conn = DriverManager.getConnection(url)) {
         * var context = DSL.using(conn, SQLDialect.SQLITE);
         * var result = context.select().from(TODOS).fetch();
         * for (var r : result) {
         * var id = r.getValue(TODOS.ID);
         * var title = r.getValue(TODOS.TITLE);
         * var date = r.getValue(TODOS.DATE);
         * var priority = r.getValue(TODOS.PRIORITY);
         * System.out.println("ID: " + id + " title: " + title + " date: " + date +
         * " priority: " + priority);
         * }
         * }
         * catch (Exception e) {
         * e.printStackTrace();
         * }
         */
        var loader = new FXMLLoader(getClass().getResource("main.fxml"));
        var scene = new Scene(loader.load(), 640, 480);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("MyApp");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
