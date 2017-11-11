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

package com.satsumasoftware.pokedex.framework

import android.content.Context
import com.satsumasoftware.pokedex.util.PrefUtils
import java.math.RoundingMode
import java.text.DecimalFormat

class Mass(val dbValue: Int) {

    val metricValue: Double

    init {
        metricValue = dbValue / 10.0
    }

    fun getDisplayedText(context: Context) = if (PrefUtils.useImperialUnits(context)) {
        val decimalFormat = DecimalFormat("#.#")  // one decimal place
        decimalFormat.roundingMode = RoundingMode.HALF_UP

        val kilogramsToPounds: Double = 2.2046226218
        val inPounds = (metricValue * kilogramsToPounds).toDouble()

        val formattedVal = decimalFormat.format(inPounds)
        "$formattedVal lbs"

    } else {
        "$metricValue kg"
    }

}