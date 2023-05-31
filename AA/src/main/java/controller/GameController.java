package controller;

import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ball;
import model.Player;
import view.GameMenu;
import view.TryAgainMenu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class GameController
{
    public Player player;
    public int musicOn;
    public int map;
    public int rotate;
    public int centerX;
    public int centerY;
    public int mainRadius;
    public int smallRadius;
    public int distance;
    public int totalBalls;
    public int totalBalls2;
    public int level;
    public ArrayList < Ball > Arr = new ArrayList<>();
    public GameController(Player _player)
    {
        player = _player;
        level = 20;
        totalBalls = totalBalls2 = 0;
        mainRadius = 80;
        smallRadius = 12;
        distance = 40;
        centerX = 250;
        centerY = 350;
        rotate = 1;

    }

    public void EndGame() throws IOException {
        DataBase.deleteAccount("userName", player.userName);
        DataBase.addAccount(player.userName, player.password, player.avatar, totalBalls);
        GameMenu.pane.getChildren().clear();
        ImageView lost = new ImageView(new Image(URLFinder.run("/images/back1.png").openStream()));
        GameMenu.pane.getChildren().add(lost);
        /*Scene scene = new Scene(GameMenu.pane, Color.RED);
        GameMenu.stage.setScene(scene);
        GameMenu.stage.show();
        GameMenu.stage.close();*/
    }

    public boolean AddNewBall(int side) throws Exception /// side 0, 1
    {
        Ball hala;
        if(side == 0)
        {
            totalBalls += 1;
            hala = new Ball(totalBalls, side, this);
        }
        else
        {
            totalBalls2 += 1;
            hala = new Ball(totalBalls2, side, this);
        }
        if(totalBalls >= level)
        {
            EndGame();
            TryAgainMenu tryAgainMenu = new TryAgainMenu();
            tryAgainMenu.SET("You Won1", "Go to ScoreBoard", 1);
            tryAgainMenu.start(new Stage());
            return true;
        }
        if(totalBalls2 >= level)
        {
            EndGame();
            TryAgainMenu tryAgainMenu = new TryAgainMenu();
            tryAgainMenu.SET("You Won2", "Go to ScoreBoard", 1);
            tryAgainMenu.start(new Stage());
            return true;
        }
        GameMenu.pane.getChildren().remove(1);
        GameMenu.pane.getChildren().add(1, getStatusBar());
        for(Ball now : Arr)
        {
            if(now.circle.getBoundsInParent().intersects(hala.circle.getBoundsInParent()))
            {
                return false;
            }
        }
        Arr.add(hala);
        return true;
    }

    public Text getCorrectText()
    {
        return new Text(this.player.userName + "'s level: " + this.level);
    }

    public ProgressBar getCorrectProgressBar()
    {
        return new ProgressBar((double) this.totalBalls / this.level);
    }

    public HBox getStatusBar()
    {
        HBox statusBar = new HBox();
        Text text1 = this.getCorrectText();
        ProgressBar progressBar1 = this.getCorrectProgressBar();
        BorderPane borderPane1 = new BorderPane();
        borderPane1.setLeft(text1);
        borderPane1.setRight(progressBar1);
        statusBar.getChildren().addAll(borderPane1);
        return statusBar;
    }

    public void setTotalBalls(int x)
    {
        totalBalls = x;
    }
    public int getTotalBalls()
    {
        return totalBalls;
    }

}
