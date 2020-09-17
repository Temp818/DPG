package com.gildedrose

sealed class ItemWrapper(private val item: Item) {

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

    open fun checkQualityValidity() {
        if (quality > MAX_QUALITY) throw IllegalArgumentException("Quality cannot be greater than $MAX_QUALITY")
    }

    open fun updateQuality() {
        val tempQuality = computeQuality()
        quality = if (tempQuality < MIN_QUALITY) MIN_QUALITY else tempQuality
    }

    open fun computeQuality(): Int {
        return if (sellIn <= LIMIT_DAY_TO_SELL) {
            quality - OUT_OF_SELL_QUALITY_LOST_VALUE
        } else {
            quality - BASIC_QUALITY_LOST_VALUE
        }
    }

    open fun updateSellIn() {
        sellIn -= 1
    }

    fun updateItem() {
        updateQuality()
        updateSellIn()
    }

    override fun toString(): String {
        return item.toString()
    }

    class BasicItem(item: Item) : ItemWrapper(item)

    open class EnhancedItem(item: Item) : ItemWrapper(item) {
        override fun updateQuality() {
            val tempQuality = computeQuality()
            quality = if (tempQuality >= MAX_QUALITY) MAX_QUALITY else tempQuality
        }

        override fun computeQuality() = if (sellIn <= LIMIT_DAY_TO_SELL) quality + 2 else quality + 1
    }

    class BackstageItem(item: Item) : EnhancedItem(item) {
        override fun computeQuality(): Int {
            return when {
                sellIn > 10 -> quality + 1
                sellIn in 10 downTo 6 -> quality + 2
                sellIn in 5 downTo 1 -> quality + 3
                else -> 0
            }
        }
    }

    class LegendaryItem(item: Item) : ItemWrapper(item) {
        override fun checkQualityValidity() {

        }

        override fun updateQuality() {

        }

        override fun updateSellIn() {

        }
    }

    class ConjuredItem(item: Item) : ItemWrapper(item) {
        override fun computeQuality(): Int {
            return if (sellIn <= LIMIT_DAY_TO_SELL) {
                quality - 2 * OUT_OF_SELL_QUALITY_LOST_VALUE
            } else {
                quality - 2 * BASIC_QUALITY_LOST_VALUE
            }
        }
    }

    companion object {
        const val MAX_QUALITY = 50
        private const val MIN_QUALITY = 0
        private const val LIMIT_DAY_TO_SELL = 0
        const val BASIC_QUALITY_LOST_VALUE = 1
        const val OUT_OF_SELL_QUALITY_LOST_VALUE = 2
    }
}
