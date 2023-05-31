package view;

import controller.GameController;
import controller.URLFinder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ball;

public class GameMenu extends Application
{
    public static Pane pane;
    public static Stage stage;

    public static GameController gameController;
    @Override
    public void start(Stage _stage) throws Exception
    {
        stage = _stage;
        stage.setTitle("AA");
        stage.setHeight(700);
        stage.setWidth(500);
        pane = FXMLLoader.load(URLFinder.run("/fxml/GameMenu.fxml"));

        CreateMainCircle(pane);

        Button settingButton = new Button("Settings");
        settingButton.setMaxWidth(80);
        settingButton.setLayoutX(420);

        settingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    new SettingMenu().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        pane.getChildren().addAll(gameController.getStatusBar(), settingButton);

        for(Ball now : gameController.Arr)
        {
            pane.getChildren().add(now.line);
            pane.getChildren().add(now.circle);
        }

        /*ImageView background = new ImageView(new Image(URLFinder.run("/images/back1.png").openStream()));

        ImageView background2 = new ImageView(new Image(URLFinder.run("images/back2.png").openStream()));
         */
        /// pane.getChildren().add(background);

        /// pane.getChildren().get(1).requestFocus();
        Scene scene = new Scene(pane);
        stage.setScene(scene);

        pane.getChildren().get(0).requestFocus();

        stage.setTitle("AA Game");
        stage.show();
    }
    public void CreateMainCircle(Pane pane)
    {
        // Add Main Circle
        Circle mainCircle = new Circle();
        mainCircle.setCenterX(gameController.centerX);
        mainCircle.setCenterY(gameController.centerY);
        mainCircle.setRadius((double) gameController.mainRadius);
        mainCircle.setFill(Color.BLACK);
        System.out.println("wow!");
        pane.getChildren().add(mainCircle);
        pane.getChildren().get(0).requestFocus();
        RotationAnimation rotationAnimation = new RotationAnimation(1, 1, gameController);
        rotationAnimation.play();
        rotationAnimation.setRate(5);
        mainCircle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                String keyName = keyEvent.getCode().getName();
                if(keyName.equals("Space"))
                {
                    Circle circle =  new Circle();
                    circle.setRadius(gameController.smallRadius);
                    circle.setCenterY(gameController.centerY * 2 - gameController.smallRadius);
                    circle.setCenterX(gameController.centerX);
                    circle.setFill(Color.BLUE);
                    pane.getChildren().add(circle);
                    ShootBallAnimation shootBallAnimation = new ShootBallAnimation(pane, circle, 0, 1, 0, gameController);
                    shootBallAnimation.play();
                }
                if(keyName.equals("Q"))
                {
                    Circle circle =  new Circle();
                    circle.setRadius(gameController.smallRadius);
                    circle.setCenterY(gameController.smallRadius);
                    circle.setCenterX(gameController.centerX);
                    circle.setFill(Color.RED);
                    pane.getChildren().add(circle);
                    ShootBallAnimation shootBallAnimation = new ShootBallAnimation(pane, circle, 0, -1, 1, gameController);
                    shootBallAnimation.play();
                }
                if(keyName.equals("R"))
                {
                    rotationAnimation.rotate = -1 * rotationAnimation.rotate;
                }
                if(keyName.equals("F"))
                {
                    if(rotationAnimation.speed == 0)
                    {
                        rotationAnimation.speed = 1;
                    }
                    else rotationAnimation.speed = 0;
                }
            }
        });
    }
}
