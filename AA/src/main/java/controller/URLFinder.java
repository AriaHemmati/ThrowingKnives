package controller;

import java.net.MalformedURLException;
import java.net.URL;

public class URLFinder
{
    public static URL run(String location) throws MalformedURLException
    {
        return new URL(URLFinder.class.getResource(location).toExternalForm());
    }
}