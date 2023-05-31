package view;

import controller.DataBase;
import controller.GameController;
import controller.URLFinder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Player;
import org.json.simple.JSONObject;

public class SignupLoginMenu extends Application
{
    public static Stage stage;
    public TextField username;
    public TextField password;
    public TextField avatar;

    @Override
    public void start(Stage _stage) throws Exception
    {
        SignupLoginMenu.stage = _stage;
        stage.setTitle("SignUp Login Menu");
        BorderPane curPane = FXMLLoader.load(URLFinder.run("/fxml/SignupLoginMenu.fxml"));
        Scene scene = new Scene(curPane);
        stage.setScene(scene);
        stage.show();
    }
    public void startTheGame() throws Exception
    {
        JSONObject cur = DataBase.getFromDataBase("userName", username.getText());
        if(cur == null)
        {
            if(!username.getText().equals(""))
            {
                DataBase.addAccount(username.getText(), password.getText(), avatar.getText(), 0);
                cur = DataBase.getFromDataBase("userName", username.getText());
            }
            else
            {
                DataBase.addAccount("guest", "", "p1.png", 0);
                avatar.setText("p1.png");
                cur = DataBase.getFromDataBase("userName", "guest");
            }

        }
        else if(!cur.get("password").equals(password.getText()))
        {
            TryAgainMenu tryAgainMenu = new TryAgainMenu();
            tryAgainMenu.SET("Wrong Password", "ok!", 0);
            tryAgainMenu.start(stage);
            return;
        }
        System.out.println(cur.get("password") + " " + password.getText());
        GameMenu.gameController = new GameController(new Player((String)cur.get("userName"), (String)cur.get("password"), avatar.getText(), (long)cur.get("score")));

        new ProfileMenu().start(stage);
    }
}
