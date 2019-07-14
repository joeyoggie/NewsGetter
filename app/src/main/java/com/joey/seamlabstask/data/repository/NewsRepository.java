package com.joey.seamlabstask.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joey.seamlabstask.Constants;
import com.joey.seamlabstask.HttpConnector;
import com.joey.seamlabstask.MyApp;
import com.joey.seamlabstask.Utils;
import com.joey.seamlabstask.data.db.AppDatabase;
import com.joey.seamlabstask.data.db.entities.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsRepository {
    private static final String TAG = NewsRepository.class.getSimpleName();

    private static NewsRepository mInstance;
    private static AppDatabase mDatabase;

    private NewsRepository(){
        initDB();
    }

    public synchronized static NewsRepository getInstance(){
        if(mInstance == null){
            mInstance = new NewsRepository();
        }
        return mInstance;
    }

    public LiveData<List<NewsItem>> getNews(){
        refreshNews();
        return mDatabase.newsItemDao().getAll();
    }

    public LiveData<List<NewsItem>> searchNews(String query){
        searchNewsApi(query);
        return mDatabase.newsItemDao().getAll();
    }

    private void refreshNews(){
        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_NEWS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "getNews response: " + response);
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.has(Constants.PARAMETER_STATUS)){
                        if(jsonObject.getString(Constants.PARAMETER_STATUS).equals(Constants.PARAMETER_STATUS_OK)){
                            if(jsonObject.has(Constants.PARAMETER_ARTICLES)){
                                //parse NewsItems and add them to the Room DB
                                JSONArray jsonArray = jsonObject.getJSONArray(Constants.PARAMETER_ARTICLES);
                                int length = jsonArray.length();
                                Gson gson = Utils.getGson();
                                Type type = new TypeToken<NewsItem>() {}.getType();
                                NewsItem newsItem;
                                mDatabase.newsItemDao().deleteAll();
                                for (int x = 0; x < length; x++) {
                                    JSONObject articleJsonObject = jsonArray.getJSONObject(x);
                                    newsItem = gson.fromJson(articleJsonObject.toString(), type);
                                    mDatabase.newsItemDao().insertAll(newsItem);
                                }
                                //MyApp.initDB().newsItemDao().insertAll(newsItems);
                            }
                        }
                    }
                }catch (JSONException e){
                    Log.d(TAG, "JSON Exception: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Volley Error: " + error.getMessage());
                //Utils.showToast(getActivity(), getResources().getString(R.string.server_connection_error), true);
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        request.setShouldCache(false);
        HttpConnector.getInstance(MyApp.getInstance()).addToRequestQueue(request);
    }

    private void searchNewsApi(String query){
        String url = Constants.GET_NEWS_URL + "&q=" + query;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "searchNews response: " + response);
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.has(Constants.PARAMETER_STATUS)){
                        if(jsonObject.getString(Constants.PARAMETER_STATUS).equals(Constants.PARAMETER_STATUS_OK)){
                            if(jsonObject.has(Constants.PARAMETER_ARTICLES)){
                                //parse NewsItems and add them to the Room DB
                                JSONArray jsonArray = jsonObject.getJSONArray(Constants.PARAMETER_ARTICLES);
                                int length = jsonArray.length();
                                Gson gson = Utils.getGson();
                                Type type = new TypeToken<NewsItem>() {}.getType();
                                NewsItem newsItem;
                                mDatabase.newsItemDao().deleteAll();
                                for (int x = 0; x < length; x++) {
                                    JSONObject articleJsonObject = jsonArray.getJSONObject(x);
                                    newsItem = gson.fromJson(articleJsonObject.toString(), type);
                                    mDatabase.newsItemDao().insertAll(newsItem);
                                }
                                //MyApp.initDB().newsItemDao().insertAll(newsItems);
                            }
                        }
                    }
                }catch (JSONException e){
                    Log.d(TAG, "JSON Exception: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Volley Error: " + error.getMessage());
                //Utils.showToast(getActivity(), getResources().getString(R.string.server_connection_error), true);
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        request.setShouldCache(false);
        HttpConnector.getInstance(MyApp.getInstance()).addToRequestQueue(request);
    }

    public static AppDatabase initDB(){
        if(mDatabase != null){
            return mDatabase;
        }else{
            mDatabase = Room.databaseBuilder(MyApp.getInstance(), AppDatabase.class, Constants.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
            return mDatabase;
        }
    }
}
