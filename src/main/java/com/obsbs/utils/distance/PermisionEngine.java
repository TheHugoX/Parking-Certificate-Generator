package com.obsbs.utils.distance;

import java.util.ArrayList;
import java.util.List;

public class PermisionEngine {
    /**
     * @param Listcheck list wiht the aspirants
     * @return the list with the given CAs
     */
    public static List<CADummy> GetListOfCAs(List<CADummy> Listcheck) {
        List<CADummy> CAList = new ArrayList<>();

        for (CADummy o : Listcheck) {
            if (GetresonCode(o) > 0) {
                if (CAList.size() < 23) {
                    CAList.add(o);
                } else {
                    if (RemoveLowerEntry(GetresonCode(o), CAList)) {
                        CAList.add(o);
                    }
                }
            }
        }
        return CAList;
    }

    /**
     * @param Entry The entry to calculate teh Score
     * @return The Score of the CADummy
     */
    public static int GetresonCode(CADummy Entry) {
        int Score = 0;
        if (Entry.getResonCode() != 0) {
            Score = 1;
        }
        //if the Train time is longer then 1.5 H
        if (Entry.getDistanceTrain() > 1.5) {
            Score = 2;
        }
        //if SChool is to near score is null
        if (Entry.getDistanceCompany() > 10) {
            Score = 3;
        }
        return Score;
    }

    /**
     * REmoves the first Lower entry then the given object
     *
     * @param Score  Score tho check find a lower etry
     * @param CAlist The list to check in
     * @return return if a lower was found
     */
    public static boolean RemoveLowerEntry(int Score, List<CADummy> CAlist) {
        int index = 0;
        for (CADummy o : CAlist) {
            int ValEntry = GetresonCode(o);
            if (ValEntry < Score) {
                index = CAlist.indexOf(o);
                break;
            }

        }
        CAlist.remove(index);
        return index != 0;
    }

    //public static List<CADummy>[] CheckWeekDays(List<CADummy> Dummylsit){
    //    Map<Day,List<CADummy
    //            >>
    //    for (CADummy caDummy : Dummylsit) {
    //        List<Day> days = caDummy.getDays();
    //        for (Day day : days) {
    //
    //        }
    //    }
    //}
}
