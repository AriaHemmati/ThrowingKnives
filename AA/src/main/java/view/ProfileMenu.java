package view;

import controller.DataBase;
import controller.GameController;
import controller.URLFinder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.net.MalformedURLException;

public class ProfileMenu extends Application
{
    public static Stage stage;
    @Override
    public void start(Stage _stage) throws Exception
    {
        stage = _stage;
        stage.setTitle("Profile Menu");
        BorderPane borderPane = FXMLLoader.load(URLFinder.run("/fxml/ProfileMenu.fxml"));
        ImageView imageAvatar = new ImageView(new Image(URLFinder.run("/images/" + GameMenu.gameController.player.avatar).openStream()));
        imageAvatar.setFitHeight(100);
        imageAvatar.setFitWidth(100);
        /*Pane pane = new Pane();
        Label welcomeLabel = new Label("Profile Menu");
        pane.getChildren().add(welcomeLabel);
        borderPane.setTop(pane); */
        borderPane.setLeft(imageAvatar);

        VBox change = new VBox();
        change.setSpacing(12);
        HBox changeUserName = new HBox();
        HBox changePassword = new HBox();
        HBox changeAvatar = new HBox();
        changeUserName.setSpacing(20);
        changeAvatar.setSpacing(20);
        changePassword.setSpacing(20);
        TextField changeUserNameText = new TextField(GameMenu.gameController.player.userName);
        TextField changePasswordText = new TextField(GameMenu.gameController.player.password);
        TextField changeAvatarText = new TextField(GameMenu.gameController.player.avatar);
        Button changeUserNameButton = new Button("change");
        changeUserNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                Player alan = GameMenu.gameController.player;
                DataBase.deleteAccount("userName", alan.userName);
                Player newPlayer = new Player(changeUserNameText.getText(), alan.password, alan.avatar, alan.score);
                GameMenu.gameController.player = newPlayer;
            }
        });
        Button changePasswordButton = new Button("change");
        changePasswordButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                Player alan = GameMenu.gameController.player;
                DataBase.deleteAccount("password", alan.password);
                Player newPlayer = new Player(alan.userName, changePasswordText.getText(), alan.avatar, alan.score);
                GameMenu.gameController.player = newPlayer;
            }
        });
        Button changeAvatarButton = new Button("change");
        changeAvatarButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                Player alan = GameMenu.gameController.player;
                DataBase.deleteAccount("avatar", alan.avatar);
                Player newPlayer = new Player(alan.userName, alan.password, changeAvatarText.getText(), alan.score);
                GameMenu.gameController.player = newPlayer;
                try {
                    imageAvatar.setImage(new Image(URLFinder.run("/images/" + GameMenu.gameController.player.avatar).openStream()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        changeUserName.getChildren().addAll(changeUserNameText, changeUserNameButton);
        changePassword.getChildren().addAll(changePasswordText, changePasswordButton);
        changeAvatar.getChildren().addAll(changeAvatarText, changeAvatarButton);

        HBox hBox = new HBox();

        hBox.setSpacing(10);
        Button logoutButton = new Button("Logout!");
        logoutButton.setAlignment(Pos.CENTER);
        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                try {
                    new SignupLoginMenu().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button deleteButton = new Button("Delete Account!");
        deleteButton.setAlignment(Pos.CENTER);

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DataBase.deleteAccount("userName", GameMenu.gameController.player.userName);
                try {
                    new SignupLoginMenu().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button goToGameButton = new Button("Go To The Game");
        goToGameButton.setAlignment(Pos.CENTER);
        goToGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                try {
                    new GameMenu().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        hBox.getChildren().addAll(logoutButton, deleteButton, goToGameButton);


        change.getChildren().addAll(changeUserName, changePassword, changeAvatar, hBox);
        borderPane.setCenter(change);


        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
