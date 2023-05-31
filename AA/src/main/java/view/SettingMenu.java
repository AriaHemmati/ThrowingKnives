package view;

import controller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingMenu extends Application
{
    public GameController gameController;
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Settings");
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        TextField textField = new TextField();
        textField.setText(String.valueOf(GameMenu.gameController.level));
        textField.setPromptText("Level");
        textField.setMaxWidth(200);

        TextField initmap = new TextField();
        initmap.setText(String.valueOf(GameMenu.gameController.map));
        initmap.setPromptText("select initial map (0, 1, 2, 3)");
        initmap.setMaxWidth(200);

        CheckBox checkBox = new CheckBox("Music");

        vBox.setSpacing(5);
        vBox.getChildren().addAll(textField, initmap, checkBox);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        Button button = new Button("resume the game / Save the changes");
        button.setAlignment(Pos.CENTER);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    GameMenu.gameController.level = Integer.parseInt(textField.getText());
                    GameMenu.gameController.map = Integer.parseInt(initmap.getText());
                    GameMenu.gameController.musicOn = checkBox.hashCode();
                    new GameMenu().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        borderPane.setBottom(button);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();
    }
}
