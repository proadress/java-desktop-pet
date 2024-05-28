package main.dash;

import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class Setting {
    Stage stage;

    @FXML
    private ImageView close;

    ArrayList<String> jarCell = new ArrayList<>();

    @FXML
    private MFXListView<String> jarList;
    @FXML
    void addFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //指定只能打開何種檔案類型
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        jarList.getItems().add(String.valueOf(file));
        System.out.println(file);
    }

    @FXML
    void deleteFile(ActionEvent event) {
        int selectID = jarList.getSelectionModel().getSelectedIndex();
        jarList.getItems().remove(selectID);
    }


}
