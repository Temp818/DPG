package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class EnhancedItemTest {

    @Test
    fun testQualityIncreaseBy1WhenSellInDateNotOver() {
        val item = ItemWrapper.EnhancedItem(Item("foo", 2, 2))
        item.updateItem()
        assertEquals(3, item.quality)
    }

    @Test
    fun testQualityIncreaseTwiceAsFastWhenSellInDateOver() {
        val item = ItemWrapper.EnhancedItem(Item("foo", 0, 2))
        item.updateItem()
        assertEquals(4, item.quality)
    }

    @Test
    fun testQualityNeverIncreaseAboveMaxQuality() {
        val item = ItemWrapper.EnhancedItem(Item("foo", 0, ItemWrapper.MAX_QUALITY))
        item.updateItem()
        assertEquals(ItemWrapper.MAX_QUALITY, item.quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = ItemWrapper.EnhancedItem(Item("foo", 0, 2))
        item.updateItem()
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


