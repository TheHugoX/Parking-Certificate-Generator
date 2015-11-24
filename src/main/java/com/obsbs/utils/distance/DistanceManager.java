package com.obsbs.utils.distance;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DistanceManager {

    public LocationInfo GetGeofromAdress(String City, String Street) {
        String urlString = "";

        URL url = null;
        String File = "";
        urlString = MessageFormat.format(urlString, City, Street);
        System.out.print(urlString);

        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            File = new Scanner(is, "UTF-8").useDelimiter("\\A").next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(File);
        //XML has to be parsd in location
        return new LocationInfo(File);

    }

    //Returns distance in KM
    public Double GetDistance(LocationInfo Start, LocationInfo Destinaiton) {

        //Request String
        String Urlstring = "";
        Urlstring = MessageFormat.format(Urlstring, Start.GetGPSPositon(), Destinaiton.GetGPSPositon());
        URL url = null;
        String File = "";

        try {
            url = new URL(Urlstring);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            File = new Scanner(is, "UTF-8").useDelimiter("\\A").next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String Patternstring = "((value=\")((\\d*)[/.](\\d*)))";
        // Create a Pattern object
        Pattern r = Pattern.compile(Patternstring);
        //Patch File
        File = File.replace("\n", " ");

        // Now create matcher object.
        Matcher m = r.matcher(File);
        if (m.find()) {
            String val = m.group(3);
            return Double.valueOf(val);
        } else {
            return -1.0;
        }

    }

    public Double GetDistance(String StartCity, String StartStreet, String DestCity, String Deststreet) {
        LocationInfo StartLocation = GetGeofromAdress(StartCity, StartStreet);
        LocationInfo DestLocation = GetGeofromAdress(DestCity, DestCity);
        return GetDistance(StartLocation, DestLocation);
    }
}
