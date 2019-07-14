package com.joey.seamlabstask.view.ui;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joey.seamlabstask.R;
import com.joey.seamlabstask.Utils;
import com.joey.seamlabstask.data.db.entities.NewsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsItemFragment extends Fragment {
    private static final String TAG = NewsItemFragment.class.getSimpleName();

    @BindView(R.id.news_item_title_textview)
    TextView titleTextView;
    @BindView(R.id.news_item_description_textview)
    TextView descriptionTextView;
    @BindView(R.id.news_item_timestamp_textview)
    TextView timestampTextView;
    @BindView(R.id.news_item_imageview)
    ImageView imageView;
    @BindView(R.id.news_item_content_textview)
    TextView contentTextView;

    private NewsItem newsItem;

    public NewsItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment NewsItemFragment.
     */
    public static NewsItemFragment newInstance(NewsItem newsItem) {
        NewsItemFragment fragment = new NewsItemFragment();
        fragment.setNewsItem(newsItem);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_item, container, false);
        ButterKnife.bind(this, view);
        MainActivity.setActionBarTitle(newsItem.getTitle(), getResources().getColor(R.color.whiteColor));
        setHasOptionsMenu(true);

        titleTextView.setText(""+newsItem.getTitle());
        descriptionTextView.setText(""+newsItem.getDescription());
        timestampTextView.setText(""+Utils.getTimeStringDateHoursMinutes(Utils.getTimestamp(newsItem.getTimestamp().replace("T", " "))));
        contentTextView.setText(""+newsItem.getContent());
        Glide.with(getActivity()).load(newsItem.getUrlToImage()).into(imageView);

        return view;
    }

    public void setNewsItem(NewsItem newsItem){
        this.newsItem = newsItem;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        //inflater.inflate(R.menu.menu_news_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if(id == R.id.action_search) {

        }*/

        return super.onOptionsItemSelected(item);
    }
}
