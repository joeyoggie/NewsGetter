package com.joey.seamlabstask.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.joey.seamlabstask.data.model.NewsItem;
import com.joey.seamlabstask.data.repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private static final String TAG = NewsViewModel.class.getSimpleName();

    private final LiveData<List<NewsItem>> projectListObservable;

    public NewsViewModel(Application application) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = NewsRepository.getInstance().getNews();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<NewsItem>> getProjectListObservable() {
        return projectListObservable;
    }

}
