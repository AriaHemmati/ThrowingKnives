package view;

import controller.GameController;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;
import model.Ball;

public class RotationAnimation extends Transition
{
    double Count;
    int rotate;
    int speed;
    GameController gameController;
    public RotationAnimation(int _rotate, int _speed, GameController _gameController)
    {
        this.setInterpolator(Interpolator.LINEAR);
        rotate = _rotate;
        speed = _speed;
        gameController = _gameController;
        setCycleCount(-1);
        setCycleDuration(javafx.util.Duration.millis(1000));
        setRate(10);
    }
    @Override
    protected void interpolate(double v)
    {
        // System.out.println(v);
        double eps = 0.001 * rotate * speed;
        double lenght = gameController.mainRadius + gameController.smallRadius + gameController.distance;
        // System.out.println("size " + gameController.Arr.size());
        for(Ball now : gameController.Arr)
        {
            double pos2 = now.pos + eps;
            double x2 = gameController.centerX + lenght * Math.cos(pos2);
            double y2 = gameController.centerY + lenght * Math.sin(pos2);
            now.circle.setCenterX(x2);
            now.circle.setCenterY(y2);
            now.line.setEndX(x2);
            now.line.setEndY(y2);
            now.pos = pos2;
        }
    }
}
