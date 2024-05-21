package org.example.deskpet_dash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL res = getClass().getResource("hello-view.fxml");
        System.out.println("hello");
        System.out.println(res.toString());
        if(res==null){
            System.out.println("hello");
            System.exit(0);
        }
        Parent root = FXMLLoader.load(res);

        Scene scene = new Scene(root);
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}