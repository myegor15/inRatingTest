package com.melnichuk.inratingtest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.melnichuk.inratingtest.R;
import com.melnichuk.inratingtest.pojo.UserInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.UserItemViewHolder> {
    private List<UserInfo> userInfoList;

    public UserItemAdapter(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        UserItemViewHolder viewHolder = new UserItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        holder.bind(userInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    class UserItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImageImageView;
        private TextView userNicknameTextView;

        public UserItemViewHolder(@NonNull View itemView) {
            super(itemView);

            userImageImageView = itemView.findViewById(R.id.user_image_iv);
            userNicknameTextView = itemView.findViewById(R.id.user_nickname_tv);
        }

        public void bind(UserInfo userInfo) {
            Picasso.get().load(userInfo.getAvatarImage().getUrlMedium()).transform(new RoundedCornersTransformation(50, 0)).into(userImageImageView);
            userNicknameTextView.setText(userInfo.getNickname());
        }
    }
}