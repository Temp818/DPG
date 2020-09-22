package com.gildedrose.item

import com.gildedrose.Item

class BackstageItem(item: Item): EnhancedItem(item) {
    override fun computeQuality(): Int {
        return when {
            sellIn > 10 -> quality + 1
            sellIn in 10 downTo 6 -> quality + 2
            sellIn in 5 downTo 1 -> quality + 3
            else -> 0
        }
    }
}