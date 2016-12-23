package com.satsumasoftware.pokedex.ui.filter

/**
 * Used to store an SQLite statement that can be used to filter database queries
 */
data class Filter(var sqlStatement: String) {

    init {
        sqlStatement = "($sqlStatement)"
    }

}
