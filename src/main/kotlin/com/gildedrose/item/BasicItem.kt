package com.gildedrose.item

import com.gildedrose.Item

open class BasicItem(item: Item): ItemWrapper(item) {
    override fun getOutOfSellQualityFactor(): Int = 2
    override fun getNormalQualityFactor(): Int = 1
}