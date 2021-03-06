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

package com.satsumasoftware.pokedex.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.satsumasoftware.pokedex.R;
import com.satsumasoftware.pokedex.ui.adapter.ChangelogAdapter;
import com.satsumasoftware.pokedex.util.AdUtils;
import com.satsumasoftware.pokedex.util.ChangelogUtils;

import java.util.ArrayList;

public class ChangelogActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelog);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AdUtils.setupAds(this, R.id.adView);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_indeterminate);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setChangelogInformation();
    }

    private void setChangelogInformation() {
        mRecyclerView.setVisibility(View.GONE); // Note: progress is already visible
        ArrayList<Integer> arrayVersionCodes = ChangelogUtils.getListOfVersions(this);
        ArrayList<ArrayList<String>> arrayDescriptions = new ArrayList<>();
        for (int i = 0; i < arrayVersionCodes.size(); i++) {
            arrayDescriptions.add(ChangelogUtils.getVersionChanges(this, arrayVersionCodes.get(i)));
        }
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setAdapter(new ChangelogAdapter(arrayVersionCodes, arrayDescriptions, true));
    }
}
