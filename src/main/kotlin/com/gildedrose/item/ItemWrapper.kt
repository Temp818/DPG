package com.gildedrose.item

import com.gildedrose.Item

abstract class ItemWrapper(private val item: Item) {

    val name: String
        get() = item.name
    var quality: Int = 0
        get() = item.quality
        protected set(value) {
            item.quality = value
            field = value
        }
    var sellIn: Int = 0
        get() = item.sellIn
        protected set(value) {
            item.sellIn = value
            field = value
        }

    init {
        checkDataValidity()
    }

    private fun checkDataValidity() {
        checkQualityValidity()
    }

    protected open fun checkQualityValidity() {
        if (quality > MAX_QUALITY) throw IllegalArgumentException("Quality cannot be greater than $MAX_QUALITY")
    }

    fun updateItem() {
        updateQuality()
        updateSellIn()
    }

    protected open fun updateQuality() {
        val tempQuality = computeQuality()
        quality = if (tempQuality < MIN_QUALITY) MIN_QUALITY else tempQuality
    }

    protected open fun computeQuality(): Int {
        return if (sellIn <= LIMIT_DAY_TO_SELL) {
            quality - OUT_OF_SELL_QUALITY_LOST_VALUE
        } else {
            quality - BASIC_QUALITY_LOST_VALUE
        }
    }

    protected open fun updateSellIn() {
        sellIn -= 1
    }

    override fun toString(): String {
        return item.toString()
    }

    companion object {
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0
        const val LIMIT_DAY_TO_SELL = 0
        const val BASIC_QUALITY_LOST_VALUE = 1
        const val OUT_OF_SELL_QUALITY_LOST_VALUE = 2
    }
}
