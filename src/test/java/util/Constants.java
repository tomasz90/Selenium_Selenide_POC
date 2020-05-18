package util;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.hidden;

public class Constants {

    public static final int DEFAULT_WAIT = 4000; //ms
    public static final int LONG_WAIT = 8000; //ms
    public static final int SHORT_WAIT = 1000; //ms
    public static final String SELENIUM = "selenium";
    public static final String SELENIDE = "selenide";
    public static final String TOKEN = "token";
    public static final String TOKEN_VALUE = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb21wYW55LmRldi5iZW5jaG91dEB0dHBzYy5wbCIsImV4cCI6MTU5MDI" +
            "zNTE5MX0.uiEgur8ot_11JCYjkLhPM02W6TqgkLQEmrYOePXZQiokGDWX5JLTvappjPZkrKtfRMPBjRU3t_JneFYexhKQoQ";

    public static final Condition NOT_VISIBLE = hidden;
}
