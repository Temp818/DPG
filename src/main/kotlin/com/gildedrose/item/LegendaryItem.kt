package com.gildedrose.item

import com.gildedrose.Item

class LegendaryItem(item: Item) : ItemWrapper(item) {
    override fun checkQualityValidity() {

    }

    override fun updateQuality() {

    }

    override fun updateSellIn() {

    }

    override fun getOutOfSellQualityFactor(): Int = 0

    override fun getNormalQualityFactor(): Int = 0
}