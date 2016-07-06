package com.satsumasoftware.pokedex.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.satsumasoftware.pokedex.R;
import com.satsumasoftware.pokedex.framework.detail.DetailInfo;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        ViewGroup rootView;
        LinearLayout container;

        DetailViewHolder(View itemView) {
            super(itemView);
            rootView = (ViewGroup) itemView.findViewById(R.id.rootView);
            container = (LinearLayout) itemView.findViewById(R.id.container);
        }
    }

    private Context mContext;
    private ArrayList<DetailInfo> mDetailArray;

    // Allows to remember the last item shown on screen
    private int mLastPosition = -1;

    public DetailAdapter(Context context, ArrayList<DetailInfo> detailArray) {
        mContext = context;
        mDetailArray = detailArray;
    }

    @Override
    public int getItemCount() {
        return mDetailArray.size();
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pokemon_detail, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        DetailInfo info = mDetailArray.get(position);
        info.setupCard(mContext, holder.container);
        setAnimation(holder.rootView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > mLastPosition) {
            // If the bound view wasn't previously displayed on screen, it's animated
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            mLastPosition = position;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
