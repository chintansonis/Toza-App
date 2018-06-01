package com.app.toza.helper;

/**
 * Created by Shriji on 2/17/2018.
 */

public class AppConstants {
    public static final String DB_NAME = "db_SkyPamphlets";

    public static final int DB_VERSION = 1;

    public static final String VALUE_DIRECTORY_USER_PROFILE = "userprofile";

    public static String SPACE = " ";

    public static final String DefaultID = "-1";

    public static String DEFAULT_STRING = "";

    private static final String BASE_HOST = String.format("%s", "http://ws-srv-net.in.webmyne.com:80/Applications/eatup/EatUp_Services/api/");
    public static String getBaseHost() {
        return BASE_HOST;
    }
}
