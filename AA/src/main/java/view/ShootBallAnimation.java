package view;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Player;

public class ShootBallAnimation extends Transition
{
    public Circle circle;

    public Pane pane;

    public int speedX, speedY, side;

    GameController gameController;

    public ShootBallAnimation(Pane _pane, Circle _circle, int _speedX, int _speedY, int _side, GameController _gameController)
    {
        pane = _pane;
        circle = _circle;
        speedX = _speedX;
        speedY = _speedY;
        side = _side;
        gameController = _gameController;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
    }

    public Long P2(int a)
    {
        Long ret = ((long) a * a);
        return ret;
    }

    public Long DistanceSquare(int x1, int y1, int x2, int y2)
    {
        return P2(x1 - x2) + P2(y1 - y2);
    }

    @Override
    protected void interpolate(double v)
    {
        if(DistanceSquare((int)circle.getCenterX(), (int)circle.getCenterY(), gameController.centerX, gameController.centerY) <= P2(gameController.distance + gameController.mainRadius))
        {
            /// hit the main Circle
            pane.getChildren().remove(circle);
            try {
                boolean success = gameController.AddNewBall(side);
                if(!success)
                {
                    gameController.EndGame();
                    TryAgainMenu tryAgainMenu = new TryAgainMenu();
                    tryAgainMenu.SET("You Lost :(", "Go to ScoreBoard", 1);
                    tryAgainMenu.start(new Stage());
                    this.stop();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.stop();
        }

        int y2 = (int) (circle.getCenterY() - speedY);
        int x2 = (int) (circle.getCenterX() - speedX);

        if(x2 <= 0 || y2 <= 0 || x2 >= gameController.centerX * 2 || y2 >= gameController.centerY * 2)
        {
            System.out.println("out of bands!");
            pane.getChildren().remove(circle);
            this.stop();
        }

        circle.setCenterY(y2);
        circle.setCenterX(x2);
    }

}
