package com.gildedrose.item

import com.gildedrose.Item

class ConjuredItem(item: Item): BasicItem(item) {
    override fun getNormalQualityFactor() = super.getNormalQualityFactor() * 2
    override fun getOutOfSellQualityFactor() = super.getOutOfSellQualityFactor() * 2
}