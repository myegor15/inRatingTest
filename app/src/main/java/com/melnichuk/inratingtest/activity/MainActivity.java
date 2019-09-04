package com.melnichuk.inratingtest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melnichuk.inratingtest.R;
import com.melnichuk.inratingtest.adapter.UserItemAdapter;
import com.melnichuk.inratingtest.api.NetworkService;
import com.melnichuk.inratingtest.pojo.PostDetailInfo;
import com.melnichuk.inratingtest.pojo.PostInfo;
import com.melnichuk.inratingtest.pojo.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public TextView viewCountTextView;
    public TextView likesCountTextView;
    public TextView commentsCountTextView;
    public TextView mentionsCountTextView;
    public TextView repostsCountTextView;
    public TextView bookmarksCountTextView;
    public RecyclerView likesRecyclerView;
    public RecyclerView commentsRecyclerView;
    public RecyclerView mentionsRecyclerView;
    public RecyclerView repostsRecyclerView;

    public static final String TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjJmNGU5ZDA1MzU3MDI3MmFlMGZhZTMzM2Y4ZTY4ZWVlMWNiMzc0NmM0Mjg5NzI0ZTExNzJjM2Q4ODYzNDNkNDkyY2ZjZjI4Njg0NzQ0MGEwIn0.eyJhdWQiOiIyIiwianRpIjoiMmY0ZTlkMDUzNTcwMjcyYWUwZmFlMzMzZjhlNjhlZWUxY2IzNzQ2YzQyODk3MjRlMTE3MmMzZDg4NjM0M2Q0OTJjZmNmMjg2ODQ3NDQwYTAiLCJpYXQiOjE1MzY4MzE4ODcsIm5iZiI6MTUzNjgzMTg4NywiZXhwIjoxNTY4MzY3ODg3LCJzdWIiOiIzOCIsInNjb3BlcyI6W119.dRitRnoqNFS3xUgtLdLiDjDVVe7ZFNrh24Qm2ML9m-V7kZpgQgajArYoS44kMa1dz_MHUhq3pqk8SnAYIsULgfrOvewTUzmH1C92-yL64Uqnv7lqWizldX2fbJ2IbB8khOCtQ-CCNA_fGY_zEBJXLsOqr4Z00tbZE6fa0PX4Mu0SsuUakLeygXbXnKOmFyZmLJZWoXKpbqiSBU239nrcyqJftBon8DL1BAUuFiadap-gpVSXj8h6BX-FsJx5cgPHFiijIalcEgzOq4VCMkwbQE8xbTsmmxkZUOnM7oKab5inzl8EV5iUgcExeSbHT6k_phOkA7XUaR6PhVoKrSQTPcfdijhME1IHfPVDPGO0vhd6hKszRrhjEPEpoothBoB8ss0lmuCFURdxFv17q97rfpDn1OfO_Y3wYuRW2lqFAnw7sLd92CHjfONwQKswLDzwE4hiQhB8iS_UEbuL_UamNOiCLfjNnVWbVc9BvoReEa8jG4coc0Kv9VNJVWh3D_hGf8dLRZBd1a7zB6-nSpKGf0eAzB0_rBXsyBepjudC-5EFDjloJOxy1Mdruoq6mQa_tFcO99JRteUSd0CXHZO-CN4Bp4xND9kstdutjBn2UWT5xhNq_QRBmBsBDAwp647dUCyQofutN9GUlu2LxmhL0ojydazdND_d9rHtY9t-ndw";
    public static final String POST_ID = "2720";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMainInfo();
        loadLikes();
        loadCommentators();
        loadMetions();
        loadReposters();
    }

    private void loadMainInfo() {
        viewCountTextView = findViewById(R.id.views_count_tv);
        bookmarksCountTextView = findViewById(R.id.bookmarks_count_tv);
        NetworkService
                .getInstance()
                .getInRatingApi()
                .getPostInfo(TOKEN, new PostInfo(POST_ID))
                .enqueue(new Callback<PostInfo>() {
                    @Override
                    public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {
                        if (response.code() == 200) {
                            PostInfo postInfo = response.body();
                            if (postInfo != null) {
                                viewCountTextView.setText(postInfo.getViewsCount());
                                bookmarksCountTextView.setText(postInfo.getBookmarksCount());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PostInfo> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "ERROR CONNECTION", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadReposters() {
        repostsCountTextView = findViewById(R.id.reposts_count_tv);
        repostsRecyclerView = findViewById(R.id.reposts_user_rv);
        repostsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        NetworkService
                .getInstance()
                .getInRatingApi()
                .getReposters(TOKEN, new PostInfo(POST_ID))
                .enqueue(new Callback<PostDetailInfo>() {
                    @Override
                    public void onResponse(Call<PostDetailInfo> call, Response<PostDetailInfo> response) {
                        if (response.code() == 200 && response.body() != null) {
                            List<UserInfo> data = response.body().getData();
                            repostsCountTextView.setText(Integer.toString(data.size()));
                            if (data.size() != 0) {
                                mentionsRecyclerView.setAdapter(new UserItemAdapter(data));
                                mentionsRecyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PostDetailInfo> call, Throwable t) {

                    }
                });
    }

    private void loadMetions() {
        mentionsCountTextView = findViewById(R.id.mentions_count_tv);
        mentionsRecyclerView = findViewById(R.id.mentions_user_rv);
        mentionsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        NetworkService
                .getInstance()
                .getInRatingApi()
                .getMentions(TOKEN, new PostInfo(POST_ID))
                .enqueue(new Callback<PostDetailInfo>() {
                    @Override
                    public void onResponse(Call<PostDetailInfo> call, Response<PostDetailInfo> response) {
                        if (response.code() == 200 && response.body() != null) {
                            List<UserInfo> data = response.body().getData();
                            mentionsCountTextView.setText(Integer.toString(data.size()));
                            if (data.size() != 0) {
                                mentionsRecyclerView.setAdapter(new UserItemAdapter(data));
                                mentionsRecyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PostDetailInfo> call, Throwable t) {

                    }
                });
    }

    private void loadCommentators() {
        commentsCountTextView = findViewById(R.id.comments_count_tv);
        commentsRecyclerView = findViewById(R.id.comments_user_rv);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        NetworkService
                .getInstance()
                .getInRatingApi()
                .getCommentators(TOKEN, new PostInfo(POST_ID))
                .enqueue(new Callback<PostDetailInfo>() {
                    @Override
                    public void onResponse(Call<PostDetailInfo> call, Response<PostDetailInfo> response) {
                        if (response.code() == 200 && response.body() != null) {
                            List<UserInfo> data = response.body().getData();
                            commentsCountTextView.setText(Integer.toString(data.size()));
                            if (data.size() != 0) {
                                commentsRecyclerView.setAdapter(new UserItemAdapter(data));
                                commentsRecyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PostDetailInfo> call, Throwable t) {

                    }
                });
    }

    private void loadLikes() {
        likesCountTextView = findViewById(R.id.likes_count_tv);
        likesRecyclerView = findViewById(R.id.like_user_rv);
        likesRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        NetworkService
                .getInstance()
                .getInRatingApi()
                .getLikes(TOKEN, new PostInfo(POST_ID))
                .enqueue(new Callback<PostDetailInfo>() {
                    @Override
                    public void onResponse(Call<PostDetailInfo> call, Response<PostDetailInfo> response) {
                        if (response.code() == 200 && response.body() != null) {
                            List<UserInfo> data = response.body().getData();
                            likesCountTextView.setText(Integer.toString(data.size()));
                            if (data.size() != 0) {
                                likesRecyclerView.setAdapter(new UserItemAdapter(data));
                                likesRecyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PostDetailInfo> call, Throwable t) {

                    }
                });
    }
}