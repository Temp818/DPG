package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class EnhancedItemTest {

    @Test
    fun testQualityIncreaseBy1WhenSellInDateNotOver() {
        val item = ItemWrapper.EnhancedItem(Item("foo", 2, 2))
        val quality = item.computeQuality()
        assertEquals(3, quality)
    }

    @Test
    fun testQualityIncreaseTwiceAsFastWhenSellInDateOver() {
        val item = ItemWrapper.EnhancedItem(Item("foo", 0, 2))
        val quality = item.computeQuality()
        assertEquals(4, quality)
    }

    @Test
    fun testQualityNeverIncreaseAboveMaxQuality() {
        val item = ItemWrapper.EnhancedItem(Item("foo", 0, ItemWrapper.MAX_QUALITY))
        item.updateQuality()
        assertEquals(ItemWrapper.MAX_QUALITY, item.quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = ItemWrapper.EnhancedItem(Item("foo", 0, 2))
        item.updateSellIn()
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun testCheckQualityValidityFailed() {
        assertThrows<IllegalArgumentException> {
            arrayOf<ItemWrapper>(ItemWrapper.EnhancedItem(
                    Item("foo", 3, ItemWrapper.MAX_QUALITY + 1)))
        }
    }

    @Test
    fun testCheckQualityValiditySucceed() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(ItemWrapper.EnhancedItem(Item("foo", 3, ItemWrapper.MAX_QUALITY)))
        }
    }
}


