package com.hc.hcppjoke.ui.detail;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;


import com.alibaba.fastjson.TypeReference;
import com.hc.hcppjoke.model.Comment;
import com.hc.hcppjoke.ui.AbsViewModel;
import com.hc.hcppjoke.ui.login.UserManager;
import com.hc.libnetwork.ApiResponse;
import com.hc.libnetwork.ApiService;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeedDetailViewModel extends AbsViewModel<Comment> {
    private long itemId;

    @Override
    public DataSource createDataSource() {
        return new DataSource();
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    //todo ，ItemKeyedDataSource 分页学习
    class DataSource extends ItemKeyedDataSource<Integer, Comment> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Comment> callback) {
            loadData(params.requestedInitialKey, params.requestedLoadSize, callback);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Comment> callback) {
            if (params.key > 0) {
                loadData(params.key, params.requestedLoadSize, callback);
            }
        }

        /**
         *  请求数据（同步请求）
         * @param key
         * @param requestedLoadSize
         * @param callback
         */
        private void loadData(Integer key, int requestedLoadSize, LoadCallback<Comment> callback) {
            ApiResponse<List<Comment>> response = ApiService.get("/comment/queryFeedComments")
                    .addParam("id", key)
                    .addParam("itemId", itemId)
                    .addParam("userId", UserManager.get().getUserId())
                    .addParam("pageCount", requestedLoadSize)
                    .responseType(new TypeReference<ArrayList<Comment>>() {
                    }.getType())
                    .execute();

            List<Comment> list = response.body == null ? Collections.emptyList() : response.body;
            callback.onResult(list);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Comment> callback) {
            callback.onResult(Collections.emptyList());
        }

        @NonNull
        @Override
        public Integer getKey(@NonNull Comment item) {
            return item.id;
        }
    }
}
