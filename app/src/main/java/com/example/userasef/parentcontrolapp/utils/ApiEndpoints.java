package com.example.userasef.parentcontrolapp.utils;

public class ApiEndpoints {
    public final static String BASE_URL =  "https://protected-plateau-74640.herokuapp.com/api/";
    public final static String PARENT_LOGIN = "parents/login";
    public final static String PARENT_REGISTER = "parents/register";
    public final static String ADD_CHILD = "parents/addChild";
    public final static String GET_CHILDREN = "parents/getChildren";
    public final static String GET_CHILD_LOCATIONS = "parents/getChildLocations/{id}";
    public final static String GET_CHILD_CALL_LOGS = "parents/getChildCallLogs/{id}";
    public final static String GET_CHILD_SMS_LOGS = "parents/getChildSmsLogs/{id}";
    public final static String ADD_FORBIDDEN_LOCATION = "parents/addForbiddenLocation";
    public final static String GET_CHILD_FORBIDDEN_LOCATIONS = "parents/getChildForbiddenLocations/{id}";
}
