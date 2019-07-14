package com.joey.seamlabstask.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.joey.seamlabstask.data.db.entities.NewsItem;
import com.joey.seamlabstask.data.repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private static final String TAG = NewsViewModel.class.getSimpleName();

    private MediatorLiveData<List<NewsItem>> projectListObservable;

    public NewsViewModel(Application application) {
        super(application);

        projectListObservable = new MediatorLiveData<>();

        projectListObservable.addSource(NewsRepository.getInstance().getNews(), projectListObservable::setValue);
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<NewsItem>> getProjectListObservable() {
        return projectListObservable;
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<NewsItem>> getFilteredProjectListObservable(String query) {
        return NewsRepository.getInstance().searchNews(query);
    }

}
