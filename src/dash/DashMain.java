package dash;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.security.spec.ECField;

public class DashMain extends Application {

    double x,y;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainUI.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);

        root.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        root.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getSceneX() - x);
            primaryStage.setY(mouseEvent.getSceneY() - y);
        });



        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("deskPet");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
