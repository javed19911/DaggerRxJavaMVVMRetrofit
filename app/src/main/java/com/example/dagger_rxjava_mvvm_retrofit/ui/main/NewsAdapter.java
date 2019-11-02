package com.example.dagger_rxjava_mvvm_retrofit.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;
import com.example.dagger_rxjava_mvvm_retrofit.databinding.ItemNewsEmptyViewBinding;
import com.example.dagger_rxjava_mvvm_retrofit.databinding.ItemNewsViewBinding;
import com.example.dagger_rxjava_mvvm_retrofit.ui.base.BaseViewHolder;
import com.example.dagger_rxjava_mvvm_retrofit.utils.AppLogger;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<News> mNewsList;

    private BlogAdapterListener mListener;

    public NewsAdapter(List<News> mNewsList) {
        this.mNewsList = mNewsList;
    }

    @Override
    public int getItemCount() {
        if (mNewsList != null && mNewsList.size() > 0) {
            return mNewsList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mNewsList != null && !mNewsList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemNewsViewBinding blogViewBinding = ItemNewsViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new BlogViewHolder(blogViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemNewsEmptyViewBinding emptyViewBinding = ItemNewsEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<News> newsList) {
        if (newsList != null) {
            mNewsList.addAll(newsList);
            notifyDataSetChanged();
        }
    }

    public void clearItems() {
        mNewsList.clear();
    }

    public void setListener(BlogAdapterListener listener) {
        this.mListener = listener;
    }

    public interface BlogAdapterListener {

        void onRetryClick();
    }

    public class BlogViewHolder extends BaseViewHolder implements NewsItemViewModel.NewsItemViewModelListener {

        private ItemNewsViewBinding mBinding;

        private NewsItemViewModel mBlogItemViewModel;

        public BlogViewHolder(ItemNewsViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final News news = mNewsList.get(position);
            mBlogItemViewModel = new NewsItemViewModel(news, this);
            mBinding.setViewModel(mBlogItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(String blogUrl) {
            if (blogUrl != null) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(blogUrl));
                    itemView.getContext().startActivity(intent);
                } catch (Exception e) {
                    AppLogger.d("url error");
                }
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements NewsEmptyItemViewModel.BlogEmptyItemViewModelListener {

        private ItemNewsEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemNewsEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            NewsEmptyItemViewModel emptyItemViewModel = new NewsEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}