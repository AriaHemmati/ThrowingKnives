package model;

import controller.GameController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import view.GameMenu;

public class Ball
{
    public GameController gameController;
    public int ballNumber;
    public Line line;
    public Circle circle;
    public double pos;
    public int owner;
    public Ball(int _ballNumber, int _owner, GameController _gameController)
    {
        gameController = _gameController;
        ballNumber = _ballNumber;
        owner = _owner;
        int delta2 = gameController.mainRadius + gameController.distance + gameController.smallRadius;
        int delta = (owner == 0? delta2 : -delta2);
        line = new Line(gameController.centerX, gameController.centerY, gameController.centerX, gameController.centerY + delta);
        circle = new Circle();
        circle.setCenterX(gameController.centerX);
        circle.setCenterY(gameController.centerY + delta);
        circle.setRadius(gameController.smallRadius);
        if(owner == 0)
        {
            pos = Math.PI / 2;
            circle.setFill(Color.BLUE);
        }
        else
        {
            pos = Math.PI * 3 / 2;
            circle.setFill(Color.RED);
        }
        GameMenu.pane.getChildren().addAll(line, circle);
    }
}
