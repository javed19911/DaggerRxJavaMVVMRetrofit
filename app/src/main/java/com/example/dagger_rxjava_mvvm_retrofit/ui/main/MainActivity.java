
package com.example.dagger_rxjava_mvvm_retrofit.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dagger_rxjava_mvvm_retrofit.R;
import com.example.dagger_rxjava_mvvm_retrofit.BR;
import com.example.dagger_rxjava_mvvm_retrofit.ViewModelProviderFactory;
import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;
import com.example.dagger_rxjava_mvvm_retrofit.databinding.ActivityMainBinding;
import com.example.dagger_rxjava_mvvm_retrofit.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator,NewsAdapter.BlogAdapterListener {

    @Inject
    NewsAdapter mNewsAdapter;

    ActivityMainBinding activityMainBinding;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    ViewModelProviderFactory factory;
    
    private MainViewModel mMainViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this,factory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    public void openWebViewActivity() {
//        Intent intent = LoginActivity.newIntent(MainActivity.this);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(this,throwable.getLocalizedMessage(),Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainViewModel.setNavigator(this);
        mNewsAdapter.setListener(this);

        activityMainBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activityMainBinding.newsRecyclerView.setLayoutManager(mLayoutManager);
        activityMainBinding.newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        activityMainBinding.newsRecyclerView.setAdapter(mNewsAdapter);


        mMainViewModel.getNewsListLiveData().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                if (mNewsAdapter != null) {
                    mNewsAdapter.clearItems();
                    mNewsAdapter.addItems(news);
                }
            }


        });
    }

    @Override
    public void onRetryClick() {
        setUp();
        mMainViewModel.fetchNews();
    }
}
