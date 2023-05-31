package view;

import controller.DataBase;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.security.KeyStore;
import java.util.*;

public class ScoreBoardMenu extends Application
{

    public Long MAX(Long a, Long b)
    {
        if(a > b) return a;
        return b;
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        HashMap < String, Long > scores = DataBase.getScoreboard();
        /// System.out.println(scores);
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        BorderPane initText = new BorderPane();
        Label label = new Label("ScoreBoard:");
        label.setAlignment(Pos.CENTER);
        initText.setCenter(label);
        vBox.getChildren().add(initText);

        ArrayList < String > Seen = new ArrayList<String>();
        for(int i = 0; i < scores.size() && i < 10; i ++)
        {
            long mx = (long) -1;
            for(Map.Entry < String, Long > E : scores.entrySet())
            {
                boolean flag = false;
                for (int j = 0; j < i; j++)
                {
                    if (E.getKey().equals(Seen.get(j)))
                    {
                        flag = true;
                    }
                }
                if (flag)
                {
                    continue;
                }
                mx = MAX(mx, E.getValue());
            }
            for(Map.Entry < String, Long > E : scores.entrySet())
            {
                if(mx != E.getValue())
                {
                    continue;
                }
                boolean flag = false;
                for(int j = 0; j < i; j ++)
                {
                    if(E.getKey().equals(Seen.get(j)))
                    {
                        flag = true;
                    }
                }
                if(flag)
                {
                    continue;
                }
                /// System.out.println(E.getKey());
                BorderPane borderPane = new BorderPane();
                Label label1 = new Label((i + 1) + " : " +  E.getKey() + " with the score of: " + E.getValue());
                if(i == 0) label1.setTextFill(Color.GREEN);
                else if(i == 1) label1.setTextFill(Color.BLUE);
                else if(i == 2) label1.setTextFill(Color.RED);
                else label1.setTextFill(Color.GRAY);
                borderPane.setCenter(label1);
                vBox.getChildren().add(borderPane);
                Seen.add(E.getKey());
                break;
            }
        }
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(400);
        stage.show();
    }
}
