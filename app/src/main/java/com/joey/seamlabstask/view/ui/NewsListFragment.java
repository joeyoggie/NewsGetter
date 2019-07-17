package com.joey.seamlabstask.view.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.joey.seamlabstask.R;
import com.joey.seamlabstask.Utils;
import com.joey.seamlabstask.data.db.entities.NewsItem;
import com.joey.seamlabstask.view.adapter.NewsItemAdapter;
import com.joey.seamlabstask.viewmodel.NewsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsListFragment extends Fragment {
    private static final String TAG = NewsListFragment.class.getSimpleName();

    @BindView(R.id.news_listview)
    ListView newsListView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    SearchView mSearchView;
    NewsItemAdapter newsItemAdapter;
    NewsViewModel viewModel;

    public NewsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment NewsListFragment.
     */
    public static NewsListFragment newInstance() {
        NewsListFragment fragment = new NewsListFragment();
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
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.bind(this, view);
        MainActivity.setActionBarTitle(Utils.getString(getActivity(), R.string.news), getResources().getColor(R.color.whiteColor));
        setHasOptionsMenu(true);

        viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        loadNews();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNews();
            }
        });

        return view;
    }

    private void loadNews(){
        observeUI(viewModel.getProjectListObservable());
    }

    private void searchNews(String query){
        observeUI(viewModel.getFilteredProjectListObservable(query));
    }

    private void refreshNews(){
        new Utils.InternetChecker(getActivity(), new Utils.InternetChecker.OnConnectionCallback() {
            @Override
            public void onConnectionSuccess() {
                loadNews();
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onConnectionFail(String errorMsg) {
                Utils.showToast(getActivity(), Utils.getString(getActivity(), R.string.no_internet), false);
                refreshLayout.setRefreshing(false);
            }
        }).execute();
    }

    private void observeUI(LiveData<List<NewsItem>> liveData) {
        // Update the list when the data changes
        liveData.observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> myProducts) {
                if (myProducts != null) {
                    //mBinding.setIsLoading(false);
                    newsItemAdapter = new NewsItemAdapter(getActivity(), myProducts, getFragmentManager());
                    newsListView.setAdapter(newsItemAdapter);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_news_list, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        mSearchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchNews(newText);
                return false;
            }
        });

        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_refresh) {
            refreshNews();
        }

        return super.onOptionsItemSelected(item);
    }
}
