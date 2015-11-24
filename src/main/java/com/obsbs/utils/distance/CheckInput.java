package com.obsbs.utils.distance;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.MessageFormat;

public class CheckInput {
    //Check the Date for Ilegal date String
    public static boolean CheckDateInput(String InputText) {
        String Patternstring = "((\\d{2})[/.](\\d{2})[/.](\\d{4}))";
        Pattern r = Pattern.compile(Patternstring);
        Matcher m = r.matcher(InputText);

        if (m.find()) {
            if (m.groupCount() > 3) {
                int Day = Integer.valueOf(m.group(2).toString());
                int Month = Integer.valueOf(m.group(3).toString());
                int Year = Integer.valueOf(m.group(4).toString());


                if ((Day < 32) && (Day > 0) && (Month > 0) && (Month < 13) && (Year > 1900) && (Year < 2200)) {
                    try {
                        Date ExpDate = new Date(Day, Month, Year);

                        return true;
                    } catch (Exception e) {
                        System.out.print(e);
                        return false;
                    }
                }

            }
        }
        return false;
    }

    //Check the Char content ov the string for illegal characters
    public static boolean CheckName(String InputText) {
        String Patternstring = "([aA-zZ]{1,30})";
        Pattern r = Pattern.compile(Patternstring);
        Matcher m = r.matcher(InputText);
        m.find();

        return m.group(0).toString().equals(InputText);
    }

    public static boolean CheckPostelCode(String InputText) {
        String PatternString = "([0-9]{5})";
        Pattern r = Pattern.compile(PatternString);
        Matcher m = r.matcher(InputText);
        return m.find() && (InputText.length() == 5);
    }

    public static boolean CheckStreetName(String InputText) {
        String Patternstring = "([0-9])";
        Pattern r = Pattern.compile(Patternstring);
        Matcher m = r.matcher(InputText);

        return !m.find();
    }


}
