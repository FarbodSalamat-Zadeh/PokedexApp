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

package com.satsumasoftware.pokedex.ui.card;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.satsumasoftware.pokedex.R;
import com.satsumasoftware.pokedex.framework.encounter.CompactEncounterDataHolder;
import com.satsumasoftware.pokedex.framework.pokemon.MiniPokemon;
import com.satsumasoftware.pokedex.ui.DetailActivity;
import com.satsumasoftware.pokedex.util.PrefUtils;

public class LocationDetail implements DetailCard {

    private String mTitle;
    private SparseArray<CompactEncounterDataHolder> mCompactHolders;


    public LocationDetail(String title, SparseArray<CompactEncounterDataHolder> encounterDataHolders) {
        mTitle = title;
        mCompactHolders = encounterDataHolders;
    }


    @Override
    public void setupCard(final Context context, ViewGroup container) {
        LayoutInflater inflater = LayoutInflater.from(context);

        TextView title = (TextView) inflater.inflate(R.layout.card_pokemon_detail_title, container, false);
        title.setText(mTitle);
        container.addView(title);

        for (int i = 0; i < mCompactHolders.size(); i++) {
            CompactEncounterDataHolder compactHolder = mCompactHolders.valueAt(i);

            View row = inflater.inflate(R.layout.list_item_encounter, container, false);

            final MiniPokemon pokemonObject = MiniPokemon.create(context, compactHolder.getPokemonId());

            ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
            if (PrefUtils.showPokemonImages(context)) {
                pokemonObject.setPokemonImage(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }

            TextView pokemon = (TextView) row.findViewById(R.id.text1);
            pokemon.setText(pokemonObject.getName());

            TextView level = (TextView) row.findViewById(R.id.text2);
            String levelText = (compactHolder.getMinLevel() == compactHolder.getMaxLevel()) ?
                    "Lv. " + compactHolder.getMinLevel() :
                    "Lv. " + compactHolder.getMinLevel() + " - " + compactHolder.getMaxLevel();
            level.setText(levelText);

            int rarity = compactHolder.getRarity();
            TextView rate = (TextView) row.findViewById(R.id.text3);
            rate.setText(rarity + "%");

            ProgressBar progressBar = (ProgressBar) row.findViewById(R.id.progressBar);
            progressBar.setMax(100);
            progressBar.setProgress(rarity);

            // go to the Pokemon detail page when the row is clicked
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POKEMON, pokemonObject);
                    context.startActivity(intent);
                }
            });

            container.addView(row);
        }
    }
}
