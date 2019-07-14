package com.joey.seamlabstask;

public class Constants {
    public static final String PACKAGE_NAME = "com.vhorus.mongezworker";

    public static final String NEWS_API_TOKEN = "f2f12646b82e4286adffbfa725b2df5a";

    //url constants
    private static final String DOMAIN_URl = "https://newsapi.org/v2";

    public static final String GET_NEWS_URL = DOMAIN_URl + "/top-headlines" + "?country=us&apiKey=" + Constants.NEWS_API_TOKEN;


    public static final String PARAMETER_NEXT_PAGE_URL = "next_page_url";

    public static final String PARAMETER_STATUS = "status";
    public static final String PARAMETER_ARTICLES = "articles";

    public static final String PARAMETER_STATUS_OK = "ok";

    //news item parameters
    public static final String PARAMETER_NEWS_ITEM_ID = "id";
    public static final String PARAMETER_NEWS_ITEM__TITLE = "title";
    public static final String PARAMETER_NEWS_ITEM_NEWS_DATA = "data";


    public static final String DB_NAME = "news-db";
}
