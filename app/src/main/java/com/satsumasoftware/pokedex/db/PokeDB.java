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

package com.satsumasoftware.pokedex.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

@SuppressWarnings("unused")
public final class PokeDB extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "pokedex.db";
    private static final int DATABASE_VERSION = 11;


    private static PokeDB sInstance;

    public static synchronized PokeDB getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PokeDB(context.getApplicationContext());
        }
        return sInstance;
    }

    private PokeDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }


    protected class Abilities {
        static final String TABLE_NAME = "abilities";
        static final String COL_ID = "id";
        static final String COL_IDENTIFIER = "identifier";
        static final String COL_GENERATION_ID = "generation_id";
        static final String COL_IS_MAIN_SERIES = "is_main_series";
    }

    public class AbilityFlavorText {
        public static final String TABLE_NAME = "ability_flavor_text";
        public static final String COL_ABILITY_ID = "ability_id";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_LANGUAGE_ID = "language_id";
        public static final String COL_FLAVOR_TEXT = "flavor_text";
    }

    protected class AbilityNames {
        static final String TABLE_NAME = "ability_names";
        static final String COL_ABILITY_ID = "ability_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_NAME = "name";
    }

    public class AbilityProse {
        public static final String TABLE_NAME = "ability_prose";
        public static final String COL_ABILITY_ID = "ability_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_SHORT_EFFECT = "short_effect";
        public static final String COL_EFFECT = "effect";
    }


    protected class EncounterConditionProse {
        static final String TABLE_NAME = "encounter_condition_prose";
        static final String COL_ENCOUNTER_CONDITION_ID = "encounter_condition_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_NAME = "name";
    }

    public class EncounterConditionValueMap {
        public static final String TABLE_NAME = "encounter_condition_value_map";
        public static final String COL_ENCOUNTER_ID = "encounter_id";
        public static final String COL_ENCOUNTER_CONDITION_VALUE_ID = "encounter_condition_value_id";
    }

    protected class EncounterConditionValueProse {
        static final String TABLE_NAME = "encounter_condition_value_prose";
        static final String COL_ENCOUNTER_CONDITION_VALUE_ID = "encounter_condition_value_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_NAME = "name";
    }

    protected class EncounterConditionValues {
        static final String TABLE_NAME = "encounter_condition_values";
        static final String COL_ID = "id";
        static final String COL_ENCOUNTER_CONDITION_ID = "encounter_condition_id";
        static final String COL_IDENTIFIER = "identifier";
        static final String COL_IS_DEFAULT = "is_default";
    }

    public class EncounterMethodProse {
        public static final String TABLE_NAME = "encounter_method_prose";
        public static final String COL_ENCOUNTER_METHOD_ID = "encounter_method_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
    }

    public class EncounterSlots {
        public static final String TABLE_NAME = "encounter_slots";
        public static final String COL_ID = "id";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_ENCOUNTER_METHOD_ID = "encounter_method_id";
        public static final String COL_SLOT = "slot";
        public static final String COL_RARITY = "rarity";
    }

    public class Encounters {
        public static final String TABLE_NAME = "encounters";
        public static final String COL_ID = "id";
        public static final String COL_VERSION_ID = "version_id";
        public static final String COL_LOCATION_AREA_ID = "location_area_id";
        public static final String COL_ENCOUNTER_SLOT_ID = "encounter_slot_id";
        public static final String COL_POKEMON_ID = "pokemon_id";
        public static final String COL_MIN_LEVEL = "min_level";
        public static final String COL_MAX_LEVEL = "max_level";
    }


    public class EvolutionChains {
        public static final String TABLE_NAME = "evolution_chains";
        public static final String COL_ID = "id";
        public static final String COL_BABY_TRIGGER_ITEM_ID = "baby_trigger_item_id";
    }


    public class Experience {
        public static final String TABLE_NAME = "experience";
        public static final String COL_GROWTH_RATE_ID = "growth_rate_id";
        public static final String COL_LEVEL = "level";
        public static final String COL_EXPERIENCE = "experience";
    }


    public class ItemCategories {
        public static final String TABLE_NAME = "item_categories";
        public static final String COL_ID = "id";
        public static final String COL_POCKET_ID = "pocket_id";
        public static final String COL_IDENTIFIER = "identifier";
    }

    public class ItemFlagMap {
        public static final String TABLE_NAME = "item_flag_map";
        public static final String COL_ITEM_ID = "item_id";
        public static final String COL_ITEM_FLAG_ID = "item_flag_id";
    }

    public class ItemFlavorText {
        public static final String TABLE_NAME = "item_flavor_text";
        public static final String COL_ITEM_ID = "item_id";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_LANGUAGE_ID = "language_id";
        public static final String COL_FLAVOR_TEXT = "flavor_text";
    }

    public class ItemNames {
        public static final String TABLE_NAME = "item_names";
        public static final String COL_ITEM_ID = "item_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
    }

    public class ItemProse {
        public static final String TABLE_NAME = "item_prose";
        public static final String COL_ITEM_ID = "item_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_SHORT_EFFECT = "short_effect";
        public static final String COL_EFFECT = "effect";
    }

    public class Items {
        public static final String TABLE_NAME = "items";
        public static final String COL_ID = "id";
        public static final String COL_IDENTIFIER = "identifier";
        public static final String COL_CATEGORY_ID = "category_id";
        public static final String COL_COST = "cost";
        public static final String COL_FLING_POWER = "fling_power";
        public static final String COL_FLING_EFFECT_ID = "fling_effect_id";
    }


    public class LocationAreaEncounterRates {
        public static final String TABLE_NAME = "location_area_encounter_rates";
        public static final String COL_LOCATION_AREA_ID = "location_area_id";
        public static final String COL_ENCOUNTER_METHOD_ID = "encounter_method_id";
        public static final String COL_VERSION_ID = "version_id";
        public static final String COL_RATE = "rate";
    }

    protected class LocationAreaProse {
        static final String TABLE_NAME = "location_area_prose";
        static final String COL_LOCATION_AREA_ID = "location_area_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_NAME = "name";
    }

    protected class LocationAreas {
        static final String TABLE_NAME = "location_areas";
        static final String COL_ID = "id";
        static final String COL_LOCATION_ID = "location_id";
        static final String COL_GAME_INDEX = "game_index";
        static final String COL_IDENTIFIER = "identifier";
    }

    protected class LocationNames {
        static final String TABLE_NAME = "location_names";
        static final String COL_LOCATION_ID = "location_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_NAME = "name";
    }

    protected class Locations {
        static final String TABLE_NAME = "locations";
        static final String COL_ID = "id";
        static final String COL_REGION_ID = "region_id";
        static final String COL_IDENTIFIER = "identifier";
    }


    public class Machines {
        public static final String TABLE_NAME = "machines";
        public static final String COL_MACHINE_NUMBER = "machine_number";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_ITEM_ID = "item_id";
        public static final String COL_MOVE_ID = "move_id";
    }


    public class MoveEffectProse {
        public static final String TABLE_NAME = "move_effect_prose";
        public static final String COL_MOVE_EFFECT_ID = "move_effect_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_SHORT_EFFECT = "short_effect";
        public static final String COL_EFFECT = "effect";
    }

    public class MoveFlagMap {
        public static final String TABLE_NAME = "move_flag_map";
        public static final String COL_MOVE_ID = "move_id";
        public static final String COL_MOVE_FLAG_ID = "move_flag_id";
    }

    public class MoveFlagProse {
        public static final String TABLE_NAME = "move_flag_prose";
        public static final String COL_MOVE_FLAG_ID = "move_flag_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
        public static final String COL_DESCRIPTION = "description";
    }

    public class MoveFlavorText {
        public static final String TABLE_NAME = "move_flavor_text";
        public static final String COL_MOVE_ID = "move_id";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_LANGUAGE_ID = "language_id";
        public static final String COL_FLAVOR_TEXT = "flavor_text";
    }

    public class MoveMeta {
        public static final String TABLE_NAME = "move_meta";
        public static final String COL_MOVE_ID = "move_id";
        public static final String COL_META_CATEGORY_ID = "meta_category_id";
        public static final String COL_META_AILMENT_ID = "meta_ailment_id";
        public static final String COL_MIN_HITS = "min_hits";
        public static final String COL_MAX_HITS = "max_hits";
        public static final String COL_MIN_TURNS = "min_turns";
        public static final String COL_MAX_TURNS = "max_turns";
        public static final String COL_DRAIN = "drain";
        public static final String COL_HEALING = "healing";
        public static final String COL_CRIT_RATE = "crit_rate";
        public static final String COL_AILMENT_CHANCE = "ailment_chance";
        public static final String COL_FLINCH_CHANCE = "flinch_chance";
        public static final String COL_STAT_CHANCE = "stat_chance";
    }

    public class MoveMetaStatChanges {
        public static final String TABLE_NAME = "move_meta_stat_changes";
        public static final String COL_MOVE_ID = "move_id";
        public static final String COL_STAT_ID = "stat_id";
        public static final String COL_CHANGE = "change";
    }

    protected class MoveNames {
        static final String TABLE_NAME = "move_names";
        static final String COL_MOVE_ID = "move_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_NAME = "name";
    }

    public class MoveTargetProse {
        public static final String TABLE_NAME = "move_target_prose";
        public static final String COL_MOVE_TARGET_ID = "move_target_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
        public static final String COL_DESCRIPTION = "description";
    }

    protected class Moves {
        static final String TABLE_NAME = "moves";
        static final String COL_ID = "id";
        static final String COL_IDENTIFIER = "identifier";
        static final String COL_GENERATION_ID = "generation_id";
        static final String COL_TYPE_ID = "type_id";
        static final String COL_POWER = "power";
        static final String COL_PP = "pp";
        static final String COL_ACCURACY = "accuracy";
        static final String COL_PRIORITY = "priority";
        static final String COL_TARGET_ID = "target_id";
        static final String COL_DAMAGE_CLASS_ID = "damage_class_id";
        static final String COL_EFFECT_ID = "effect_id";
        static final String COL_EFFECT_CHANCE = "effect_chance";
        static final String COL_CONTEST_TYPE_ID = "contest_type_id";
        static final String COL_CONTEST_EFFECT_ID = "contest_effect_id";
        static final String COL_SUPER_CONTEST_EFFECT_ID = "super_contest_effect_id";
    }


    public class NatureBattleStylePreferences {
        public static final String TABLE_NAME = "nature_battle_style_preferences";
        public static final String COL_NATURE_ID = "nature_id";
        public static final String COL_MOVE_BATTLE_STYLE_ID = "move_battle_style_id";
        public static final String COL_LOW_HP_PREFERENCE = "low_hp_preference";
        public static final String COL_HIGH_HP_PREFERENCE = "high_hp_preference";
    }

    protected class NatureNames {
        static final String TABLE_NAME = "nature_names";
        static final String COL_NATURE_ID = "nature_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_NAME = "name";
    }

    public class NaturePokeathlonStats {
        public static final String TABLE_NAME = "nature_pokeathlon_stats";
        public static final String COL_NATURE_ID = "nature_id";
        public static final String COL_POKEATHLON_STAT_ID = "pokeathlon_stat_id";
        public static final String COL_MAX_CHANGE = "max_change";
    }

    protected class Natures {
        static final String TABLE_NAME = "natures";
        static final String COL_ID = "id";
        static final String COL_IDENTIFIER = "identifier";
        static final String COL_DECREASED_STAT_ID = "decreased_stat_id";
        static final String COL_INCREASED_STAT_ID = "increased_stat_id";
        static final String COL_HATES_FLAVOR_ID = "hates_flavor_id";
        static final String COL_LIKES_FLAVOR_ID = "likes_flavor_id";
        static final String COL_GAME_INDEX = "game_index";
    }


    public class PokedexProse {
        public static final String TABLE_NAME = "pokedex_prose";
        public static final String COL_POKEDEX_ID = "pokedex_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
        public static final String COL_DESCRIPTION = "description";
    }

    public class PokedexVersionGroups {
        public static final String TABLE_NAME = "pokedex_version_groups";
        public static final String COL_POKEDEX_ID = "pokedex_id";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
    }

    public class Pokedexes {
        public static final String TABLE_NAME = "pokedexes";
        public static final String COL_ID = "id";
        public static final String COL_REGION_ID = "region_id";
        public static final String COL_IDENTIFIER = "identifier";
        public static final String COL_IS_MAIN_SERIES = "is_main_series";
    }


    protected class Pokemon {
        static final String TABLE_NAME = "pokemon";
        static final String COL_ID = "id";
        static final String COL_IDENTIFIER = "identifier";
        static final String COL_SPECIES_ID = "species_id";
        static final String COL_HEIGHT = "height";
        static final String COL_WEIGHT = "weight";
        static final String COL_BASE_EXP = "base_experience";
        static final String COL_ORDER = "pokemon_order";
        static final String COL_IS_DEFAULT = "is_default";
    }

    protected class PokemonAbilities {
        static final String TABLE_NAME = "pokemon_abilities";
        static final String COL_POKEMON_ID = "pokemon_id";
        static final String COL_ABILITY_ID = "ability_id";
        static final String COL_IS_HIDDEN = "is_hidden";
        static final String COL_SLOT = "slot";
    }

    protected class PokemonDexNumbers {
        static final String TABLE_NAME = "pokemon_dex_numbers";
        static final String COL_SPECIES_ID = "species_id";
        static final String COL_POKEDEX_ID = "pokedex_id";
        static final String COL_POKEDEX_NUMBER = "pokedex_number";
    }

    protected class PokemonEggGroups {
        static final String TABLE_NAME = "pokemon_egg_groups";
        static final String COL_SPECIES_ID = "species_id";
        static final String COL_EGG_GROUP_ID = "egg_group_id";
    }

    public class PokemonEvolution {
        public static final String TABLE_NAME = "pokemon_evolution";
        public static final String COL_ID = "id";
        public static final String COL_EVOLVED_SPECIES_ID = "evolved_species_id";
        public static final String COL_EVOLUTION_TRIGGER_ID = "evolution_trigger_id";
        public static final String COL_TRIGGER_ITEM_ID = "trigger_item_id";
        public static final String COL_MINIMUM_LEVEL = "minimum_level";
        public static final String COL_GENDER_ID = "gender_id";
        public static final String COL_LOCATION_ID = "location_id";
        public static final String COL_HELD_ITEM_ID = "held_item_id";
        public static final String COL_TIME_OF_DAY = "time_of_day";
        public static final String COL_KNOWN_MOVE_ID = "known_move_id";
        public static final String COL_KNOWN_MOVE_TYPE_ID = "known_move_type_id";
        public static final String COL_MINIMUM_HAPPINESS = "minimum_happiness";
        public static final String COL_MINIMUM_BEAUTY = "minimum_beauty";
        public static final String COL_MINIMUM_AFFECTION = "minimum_affection";
        public static final String COL_RELATIVE_PHYSICAL_STATS = "relative_physical_stats";
        public static final String COL_PARTY_SPECIES_ID = "party_species_id";
        public static final String COL_PARTY_TYPE_ID = "party_type_id";
        public static final String COL_TRADE_SPECIES_ID = "trade_species_id";
        public static final String COL_NEEDS_OVERWORLD_RAIN = "needs_overworld_rain";
        public static final String COL_TURN_UPSIDE_DOWN = "turn_upside_down";
    }

    protected class PokemonFormNames {
        static final String TABLE_NAME = "pokemon_form_names";
        static final String COL_POKEMON_FORM_ID = "pokemon_form_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_FORM_NAME = "form_name";
        static final String COL_POKEMON_NAME = "pokemon_name";
    }

    public class PokemonFormPokeathlonStats {
        public static final String TABLE_NAME = "pokemon_form_pokeathlon_stats";
        public static final String COL_POKEMON_FORM_ID = "pokemon_form_id";
        public static final String COL_POKEATHLON_STAT_ID = "pokeathlon_stat_id";
        public static final String COL_MINIMUM_STAT = "minimum_stat";
        public static final String COL_BASE_STAT = "base_stat";
        public static final String COL_MAXIMUM_STAT = "maximum_stat";
    }

    protected class PokemonForms {
        static final String TABLE_NAME = "pokemon_forms";
        static final String COL_ID = "id";
        static final String COL_IDENTIFIER = "identifier";
        static final String COL_FORM_IDENTIFIER = "form_identifier";
        static final String COL_POKEMON_ID = "pokemon_id";
        static final String COL_INTRODUCED_IN_VERSION_GROUP_ID = "introduced_in_version_group_id";
        static final String COL_IS_DEFAULT = "is_default";
        static final String COL_IS_BATTLE_ONLY = "is_battle_only";
        static final String COL_IS_MEGA = "is_mega";
        static final String COL_FORM_ORDER = "form_order";
        static final String COL_ORDER = "list_order";
    }

    public class PokemonItems {
        public static final String TABLE_NAME = "pokemon_items";
        public static final String COL_POKEMON_ID = "pokemon_id";
        public static final String COL_VERSION_ID = "version_id";
        public static final String COL_ITEM_ID = "item_id";
        public static final String COL_RARITY = "rarity";
    }

    public class PokemonMoveMethodProse {
        public static final String TABLE_NAME = "pokemon_move_method_prose";
        public static final String COL_POKEMON_MOVE_METHOD_ID = "pokemon_move_method_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
        public static final String COL_DESCRIPTION = "description";
    }

    public class PokemonMoves {
        public static final String TABLE_NAME = "pokemon_moves";
        public static final String COL_POKEMON_ID = "pokemon_id";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_MOVE_ID = "move_id";
        public static final String COL_POKEMON_MOVE_METHOD_ID = "pokemon_move_method_id";
        public static final String COL_LEVEL = "level";
        public static final String COL_ORDER = "move_order";
    }

    protected class PokemonSpecies {
        static final String TABLE_NAME = "pokemon_species";
        static final String COL_ID = "id";
        static final String COL_IDENTIFIER = "identifier";
        static final String COL_GENERATION_ID = "generation_id";
        static final String COL_EVOLVES_FROM_SPECIES_ID = "evolves_from_species_id";
        static final String COL_EVOLUTION_CHAIN_ID = "evolution_chain_id";
        static final String COL_COLOR_ID = "color_id";
        static final String COL_SHAPE_ID = "shape_id";
        static final String COL_HABITAT_ID = "habitat_id";
        static final String COL_GENDER_RATE = "gender_rate";
        static final String COL_CAPTURE_RATE = "capture_rate";
        static final String COL_BASE_HAPPINESS = "base_happiness";
        static final String COL_IS_BABY = "is_baby";
        static final String COL_HATCH_COUNTER = "hatch_counter";
        static final String COL_HAS_GENDER_DIFFERENCES = "has_gender_differences";
        static final String COL_GROWTH_RATE_ID = "growth_rate_id";
        static final String COL_FORMS_SWITCHABLE = "forms_switchable";
        static final String COL_ORDER = "species_order";
        static final String COL_CONQUEST_ORDER = "conquest_order";
    }

    public class PokemonSpeciesFlavorText {
        public static final String TABLE_NAME = "pokemon_species_flavor_text";
        public static final String COL_SPECIES_ID = "species_id";
        public static final String COL_VERSION_ID = "version_id";
        public static final String COL_LANGUAGE_ID = "language_id";
        public static final String COL_FLAVOR_TEXT = "flavor_text";
    }

    protected class PokemonSpeciesNames {
        static final String TABLE_NAME = "pokemon_species_names";
        static final String COL_POKEMON_SPECIES_ID = "pokemon_species_id";
        static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        static final String COL_NAME = "name";
        static final String COL_GENUS = "genus";
    }

    public class PokemonSpeciesProse {
        public static final String TABLE_NAME = "pokemon_species_prose";
        public static final String COL_POKEMON_SPECIES_ID = "pokemon_species_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_FORM_DESCRIPTION = "form_description";
    }

    protected class PokemonStats {
        static final String TABLE_NAME = "pokemon_stats";
        static final String COL_POKEMON_ID = "pokemon_id";
        static final String COL_STAT_ID = "stat_id";
        static final String COL_BASE_STAT = "base_stat";
        static final String COL_EFFORT = "effort";
    }

    protected class PokemonTypes {
        static final String TABLE_NAME = "pokemon_types";
        static final String COL_POKEMON_ID = "pokemon_id";
        static final String COL_TYPE_ID = "type_id";
        static final String COL_SLOT = "slot";
    }


    public class RegionNames {
        public static final String TABLE_NAME = "region_names";
        public static final String COL_REGION_ID = "region_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
    }

    public class Regions {
        public static final String TABLE_NAME = "regions";
        public static final String COL_ID = "id";
        public static final String COL_IDENTIFIER = "identifier";
    }


    public class StatNames {
        public static final String TABLE_NAME = "stat_names";
        public static final String COL_STAT_ID = "stat_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
    }

    public class Stats {
        public static final String TABLE_NAME = "stats";
        public static final String COL_ID = "id";
        public static final String COL_DAMAGE_CLASS_ID = "damage_class_id";
        public static final String COL_IDENTIFIER = "identifier";
        public static final String COL_IS_BATTLE_ONLY = "is_battle_only";
        public static final String COL_GAME_INDEX = "game_index";
    }


    public class TypeEfficacy {
        public static final String TABLE_NAME = "type_efficacy";
        public static final String COL_DAMAGE_TYPE_ID = "damage_type_id";
        public static final String COL_TARGET_TYPE_ID = "target_type_id";
        public static final String COL_DAMAGE_FACTOR = "damage_factor";
    }

    public class TypeNames {
        public static final String TABLE_NAME = "type_names";
        public static final String COL_TYPE_ID = "type_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
    }

    public class Types {
        public static final String TABLE_NAME = "types";
        public static final String COL_ID = "id";
        public static final String COL_IDENTIFIER = "identifier";
        public static final String COL_GENERATION_ID = "generation_id";
        public static final String COL_DAMAGE_CLASS_ID = "damage_class_id";
    }


    public class VersionGroupPokemonMoveMethods {
        public static final String TABLE_NAME = "version_group_pokemon_move_methods";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_POKEMON_MOVE_METHOD_ID = "pokemon_move_method_id";
    }

    public class VersionGroupRegions {
        public static final String TABLE_NAME = "version_group_regions";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_REGION_ID = "region_id";
    }

    public class VersionGroups {
        public static final String TABLE_NAME = "version_groups";
        public static final String COL_ID = "id";
        public static final String COL_IDENTIFIER = "identifier";
        public static final String COL_GENERATION_ID = "generation_id";
        public static final String COL_ORDER = "group_order";
    }

    public class VersionNames {
        public static final String TABLE_NAME = "version_names";
        public static final String COL_VERSION_ID = "version_id";
        public static final String COL_LOCAL_LANGUAGE_ID = "local_language_id";
        public static final String COL_NAME = "name";
    }

    public class Versions {
        public static final String TABLE_NAME = "versions";
        public static final String COL_ID = "id";
        public static final String COL_VERSION_GROUP_ID = "version_group_id";
        public static final String COL_IDENTIFIER = "identifier";
    }

}
