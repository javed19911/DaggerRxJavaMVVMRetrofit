
package com.example.dagger_rxjava_mvvm_retrofit.ui.main;


public class NewsEmptyItemViewModel {

    private BlogEmptyItemViewModelListener mListener;

    public NewsEmptyItemViewModel(BlogEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface BlogEmptyItemViewModelListener {

        void onRetryClick();
    }
}
