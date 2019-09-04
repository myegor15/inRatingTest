package com.melnichuk.inratingtest.api;

import com.melnichuk.inratingtest.pojo.PostDetailInfo;
import com.melnichuk.inratingtest.pojo.PostInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InRatingApi {

    @POST("get")
    Call<PostInfo> getPostInfo(@Header("Authorization") String token,
                               @Body PostInfo id);

    @POST("likers/all")
    Call<PostDetailInfo> getLikes(@Header("Authorization") String token,
                                  @Body PostInfo id);

    @POST("commentators/all")
    Call<PostDetailInfo> getCommentators(@Header("Authorization") String token,
                                         @Body PostInfo id);

    @POST("mentions/all")
    Call<PostDetailInfo> getMentions(@Header("Authorization") String token,
                                     @Body PostInfo id);

    @POST("reposters/all")
    Call<PostDetailInfo> getReposters(@Header("Authorization") String token,
                                      @Body PostInfo id);
}