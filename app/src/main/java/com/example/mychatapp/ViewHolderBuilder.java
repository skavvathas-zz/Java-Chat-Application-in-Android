package com.example.mychatapp;

import android.view.View;

public class ViewHolderBuilder {
    private View itemView;

    public ViewHolderBuilder setItemView(View itemView) {
        this.itemView = itemView;
        return this;
    }

    public UsersAdapter.ViewHolder createViewHolder() {
        return new UsersAdapter.ViewHolder(itemView);
    }
}