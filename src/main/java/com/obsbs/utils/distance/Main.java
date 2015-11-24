package com.obsbs.utils.distance;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DistanceManager dist = new DistanceManager();
        //dist.GetGeofromAdress("Braunschweig","Spatzenstieg5");
        //LocationInfo Loc = new LocationInfo("test");
        //LocationInfo Start = dist.GetGeofromAdress("Hannover","Spartanerstraße");

        //LocationInfo End = dist.GetGeofromAdress("Braunschweig","Spatzenstieg");

        //System.out.print(dist.GetDistance(Start, End));

        //CheckInput inp = new CheckInput();
        //System.out.print(inp.CheckPostelCode("30519"));
        //System.out.print(CheckInput.CheckStreetName("Startstr."));
        List<CADummy> ApplicationForms = new ArrayList<>();

        ApplicationForms.add(new CADummy(20.0, 2.0, 40.0));

        List<CADummy> ResultList = PermisionEngine.GetListOfCAs(ApplicationForms);

        System.out.print(ResultList.toString());
        ResonCode re = ResonCode.Pflegefall;
        System.out.print(re.getCode());
    }
}
