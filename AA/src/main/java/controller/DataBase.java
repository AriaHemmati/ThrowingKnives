package controller;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase
{
    public static String location = "src/main/java/data.json";

    /* data: userName, password, score */

    public static JSONObject getJSONObject(String userName, String password, String avatar, long score)
    {
        JSONObject cur = new JSONObject();
        cur.put("userName", userName);
        cur.put("password", password);
        cur.put("avatar", avatar);
        cur.put("score", score);
        return cur;
    }

    public static JSONObject getFromDataBase(String type, String S)
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(location))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray data = (JSONArray) obj;
            for (Object datum : data)
            {
                JSONObject currentObject = (JSONObject) datum;
                String thisObjectByType = (String) currentObject.get(type);
                if (thisObjectByType.equals(S))
                {
                    return currentObject;
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteAccount(String type, String S)
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(location))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray data = (JSONArray) obj;
            JSONArray newData = new JSONArray();
            int yetDeleted = 0;
            for (Object datum : data)
            {
                JSONObject currentObject = (JSONObject) datum;
                String thisObjectUserName = (String) currentObject.get(type);
                if(yetDeleted > 0 || !thisObjectUserName.equals(S))
                {
                    newData.add(currentObject);
                }
                else
                {
                    yetDeleted = 1;
                }
            }
            reader.close() ;
            try (FileWriter file = new FileWriter(location))
            {
                file.write(newData.toJSONString());
                file.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean addAccount(String userName, String password, String avatar, long score)
    {
        JSONObject cur = getJSONObject(userName, password, avatar, score);
        /*JSONObject alan = getFromDataBase("userName", userName);
        if(alan != null)
        {
            return false;
        }
         */
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(location))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray data = (JSONArray) obj;
            data.add(cur);
            reader.close() ;
            try (FileWriter file = new FileWriter(location))
            {
                file.write(data.toJSONString());
                file.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return true;
    }
    public static HashMap< String, Long > getScoreboard()
    {
        HashMap < String, Long > ret = new HashMap<String, Long>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(location))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray data = (JSONArray) obj;
            for (Object datum : data)
            {
                JSONObject currentObject = (JSONObject) datum;
                ret.put((String)currentObject.get("userName"), (Long)currentObject.get("score"));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return ret;
    }
}
