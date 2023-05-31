package view;

import controller.URLFinder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TryAgainMenu extends Application
{
    public static Stage stage;
    public Text comment;
    public Button button;
    public int currentState;

    public void SET(String _comment, String but, int _currentState)
    {
        comment = new Text(_comment);
        button = new Button(but);
        currentState = _currentState;
    }

    @Override
    public void start(Stage _stage) throws Exception {
        stage = _stage;
        BorderPane borderPane = FXMLLoader.load(URLFinder.run("/fxml/TryAgainMenu.fxml"));
        borderPane.setTop(comment);
        borderPane.setCenter(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(currentState == 0)
                {
                    try {
                        new SignupLoginMenu().start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(currentState == 1)
                {
                    try {
                        new ScoreBoardMenu().start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

}
