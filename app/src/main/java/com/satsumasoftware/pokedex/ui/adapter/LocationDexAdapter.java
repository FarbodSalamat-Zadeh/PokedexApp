/*
 * Copyright 2016-2017 Farbod Salamat-Zadeh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.satsumasoftware.pokedex.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.satsumasoftware.pokedex.R;
import com.satsumasoftware.pokedex.framework.Region;
import com.satsumasoftware.pokedex.framework.location.Location;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.ArrayList;

public class LocationDexAdapter extends RecyclerView.Adapter<LocationDexAdapter.LocationViewHolder>
        implements INameableAdapter {

    @Override
    public Character getCharacterForElement(int element) {
        return mLocations.get(element).getName().charAt(0);
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tvName, tvRegion;

        LocationViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            tvName = (TextView) itemView.findViewById(R.id.text1);
            tvRegion = (TextView) itemView.findViewById(R.id.text2);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mOnEntryLongClickListener != null) {
                mOnEntryLongClickListener.onEntryLongClick(v, getLayoutPosition());
                return true;
            }
            return false;
        }
    }

    private ArrayList<Location> mLocations;

    public LocationDexAdapter(ArrayList<Location> locations) {
        mLocations = locations;
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        Location location = mLocations.get(position);

        holder.tvName.setText(location.getName());
        holder.tvRegion.setText(new Region(location.getRegionId()).getName());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }


    private OnEntryLongClickListener mOnEntryLongClickListener;

    public interface OnEntryLongClickListener {
        void onEntryLongClick(View view, int position);
    }

    public void setOnEntryLongClickListener(OnEntryLongClickListener onEntryLongClickListener) {
        mOnEntryLongClickListener = onEntryLongClickListener;
    }
}
