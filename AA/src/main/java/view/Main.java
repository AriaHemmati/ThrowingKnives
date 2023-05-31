package view;

import controller.DataBase;
import controller.GameController;
import controller.URLFinder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

import static java.lang.Math.sin;

public class Main extends Application
{

    public static Stage stage;

    public static void main(String args[])
    {
        /*
        System.out.println("Hell yeah!");
        DataBase.addAccount("Ariya", "hello", 0);
        DataBase.addAccount("ghazal", "hello", 10);
        System.out.println(DataBase.getScoreboard());
        */

        launch(args);
    }

    @Override
    public void start(Stage PrimaryStage) throws Exception
    {
        stage = PrimaryStage;
        /// System.out.println(sin(Math.PI + 1) + " " + Math.cos(0));
        /// new ScoreBoardMenu().start(stage);
        new SignupLoginMenu().start(stage);
        /// new TryAgainMenu().start(stage);
        /// new SettingMenu().start(stage);
    }
}
