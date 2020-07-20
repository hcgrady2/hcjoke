package com.hc.hcppjoke.ui.detail;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hc.hcppjoke.R;
import com.hc.hcppjoke.databinding.LayoutFeedDetailBottomInateractionBinding;
import com.hc.hcppjoke.model.Comment;
import com.hc.hcppjoke.model.Feed;
import com.hc.libcommon.utils.PixUtils;
import com.hc.libcommon.view.EmptyView;


/**
 * 图文详情页和视频详情页共同部分
 */
public abstract class ViewHandler {
    private final FeedDetailViewModel viewModel;
    protected FragmentActivity mActivity;
    protected Feed mFeed;
    protected RecyclerView mRecyclerView;
    protected LayoutFeedDetailBottomInateractionBinding mInateractionBinding;
    protected FeedCommentAdapter listAdapter;
    private CommentDialog commentDialog;

    public ViewHandler(FragmentActivity activity) {

        mActivity = activity;
        viewModel = ViewModelProviders.of(activity).get(FeedDetailViewModel.class);
    }


    //view 的数据初始化
    @CallSuper
    public void bindInitData(Feed feed) {
        mInateractionBinding.setOwner(mActivity);
        mFeed = feed;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(null);
        listAdapter = new FeedCommentAdapter(mActivity) {
            @Override
            public void onCurrentListChanged(@Nullable PagedList<Comment> previousList, @Nullable PagedList<Comment> currentList) {
                boolean empty = currentList.size() <= 0;
                handleEmpty(!empty);
            }
        };
        mRecyclerView.setAdapter(listAdapter);

        viewModel.setItemId(mFeed.itemId);
        viewModel.getPageData().observe(mActivity, new Observer<PagedList<Comment>>() {
            @Override
            public void onChanged(PagedList<Comment> comments) {
                //提交数据，数据才能展示到列表上
                listAdapter.submitList(comments);
                //处理空情况
                handleEmpty(comments.size() > 0);
            }
        });
        mInateractionBinding.inputView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });
    }

    private void showCommentDialog() {
        if (commentDialog == null) {
            commentDialog = CommentDialog.newInstance(mFeed.itemId);
        }
        commentDialog.setCommentAddListener(comment -> {
            handleEmpty(true);
            listAdapter.addAndRefreshList(comment);
        });
        commentDialog.show(mActivity.getSupportFragmentManager(), "comment_dialog");
    }

    private EmptyView mEmptyView;

    public void handleEmpty(boolean hasData) {
        if (hasData) {
            if (mEmptyView != null) {
                //隐藏
                listAdapter.removeHeaderView(mEmptyView);
            }
        } else {
            if (mEmptyView == null) {
                mEmptyView = new EmptyView(mActivity);
                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topMargin = PixUtils.dp2px(40);
                mEmptyView.setLayoutParams(layoutParams);
                mEmptyView.setTitle(mActivity.getString(R.string.feed_comment_empty));
            }
            listAdapter.addHeaderView(mEmptyView);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (commentDialog != null && commentDialog.isAdded()) {
            commentDialog.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onPause() {

    }

    public void onResume() {

    }

    public void onBackPressed() {

    }
}
