package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application
{
    public static void main(String args[])
    {
        System.out.println("Hell yeah!");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        URL url = Main.class.getResource("/FXML/LoginMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}