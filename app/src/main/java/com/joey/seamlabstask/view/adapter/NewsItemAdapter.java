package com.joey.seamlabstask.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.joey.seamlabstask.R;
import com.joey.seamlabstask.Utils;
import com.joey.seamlabstask.data.db.entities.NewsItem;
import com.joey.seamlabstask.view.ui.NewsItemFragment;
import com.joey.seamlabstask.view.ui.NewsListFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemAdapter extends ArrayAdapter {
    Activity activity ;
    List<com.joey.seamlabstask.data.db.entities.NewsItem> newsItems;
    ViewHolder vHolder = null;
    FragmentManager fragmentManager;

    public NewsItemAdapter(Activity activity, List<NewsItem> newsItems, FragmentManager fragmentManager){
        super(activity, R.layout.list_item_news, newsItems);
        this.activity = activity;
        this.newsItems = newsItems;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return newsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return newsItems.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_news, null);
            vHolder = new ViewHolder(rowView);
            rowView.setTag(vHolder);
        }
        else{
            vHolder = (ViewHolder) rowView.getTag();
        }

        NewsItem item = newsItems.get(position);

        vHolder.titleTextView.setText(""+item.getTitle());
        vHolder.descriptionTextView.setText(""+item.getDescription());
        vHolder.timestampTextView.setText(""+Utils.getTimeStringDateHoursMinutes(Utils.getTimestamp(item.getTimestamp().replace("T", " "))));
        Glide.with(activity).load(item.getUrlToImage()).into(vHolder.imageView);

        vHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
                fragmentTransaction.replace(R.id.fragment_view, NewsItemFragment.newInstance(item), "newsItemFragment");
                fragmentTransaction.addToBackStack("newsItemFragment");
                fragmentTransaction.commit();
            }
        });

        return rowView;
    }

    public static class ViewHolder{
        @BindView(R.id.news_item_layout)
        RelativeLayout itemLayout;
        @BindView(R.id.news_item_title_textview)
        TextView titleTextView;
        @BindView(R.id.news_item_description_textview)
        TextView descriptionTextView;
        @BindView(R.id.news_item_timestamp_textview)
        TextView timestampTextView;
        @BindView(R.id.news_item_imageview)
        ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
