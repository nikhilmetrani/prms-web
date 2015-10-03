package sg.edu.nus.iss.phoenix.core.controller;

import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FCUtilities {

    //Get userId from the servlet pathInfo    
    public static String stripPath(String pathInfo) {
        int pos = pathInfo.indexOf("/");
        int len = pathInfo.length();
        String userId = pathInfo.substring(pos + 1, len);
        System.out.println("Path: " + userId);
        return userId;
    }

             
    public static String validatePassword(String password) {
        Pattern pattern;
        Matcher matcher;

        String PASSWORD_PATTERN
                = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        pattern = Pattern.compile(PASSWORD_PATTERN);

        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return "Password must contain one digit,one lowercase and,\n"
                    + "one uppercase character,anyone of special symbols \"@#$%\"\n"
                    + "and length must be > 6 and < 20 characters\n";
        }else{
            return null;
        }
    }
}

