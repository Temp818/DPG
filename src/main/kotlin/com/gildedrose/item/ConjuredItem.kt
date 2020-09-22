package com.gildedrose.item

import com.gildedrose.Item

class ConjuredItem(item: Item): ItemWrapper(item) {
    override fun computeQuality(): Int {
        return if (sellIn <= LIMIT_DAY_TO_SELL) {
            quality - 2 * OUT_OF_SELL_QUALITY_LOST_VALUE
        } else {
            quality - 2 * BASIC_QUALITY_LOST_VALUE
        }
    }
}