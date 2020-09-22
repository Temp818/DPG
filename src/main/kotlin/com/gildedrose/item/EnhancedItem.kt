package com.gildedrose.item

import com.gildedrose.Item

open class EnhancedItem(item: Item): ItemWrapper(item) {
    override fun updateQuality() {
        val tempQuality = computeQuality()
        quality = if (tempQuality >= MAX_QUALITY) MAX_QUALITY else tempQuality
    }

    override fun computeQuality() = if (sellIn <= LIMIT_DAY_TO_SELL) quality + 2 else quality + 1
}