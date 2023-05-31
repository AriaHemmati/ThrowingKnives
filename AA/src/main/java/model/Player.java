package model;

import controller.DataBase;

public class Player
{
    public String userName, password, avatar;
    public long score;
    public Player(String _userName, String _password, String _avatar, long _score)
    {
        userName = _userName;
        password = _password;
        avatar = _avatar;
        score = _score;
        userName = _userName;
        password = _password;
        avatar = _avatar;
        score = _score;
        DataBase.addAccount(userName, password, avatar, score);
        System.out.println("New Player: " + userName + " " + password + " " +  avatar + " " + score);
    }
}
