package com.gildedrose

import com.gildedrose.item.EnhancedItem
import com.gildedrose.item.ItemWrapper
import com.gildedrose.item.ItemWrapper.Companion.MAX_QUALITY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class EnhancedItemTest {

    @Test
    fun testQualityIncreaseBy1WhenSellInDateNotOver() {
        val item = EnhancedItem(Item("foo", 2, 2))
        item.updateItem()
        assertEquals(3, item.quality)
    }

    @Test
    fun testQualityIncreaseTwiceAsFastWhenSellInDateOver() {
        val item = EnhancedItem(Item("foo", 0, 2))
        item.updateItem()
        assertEquals(4, item.quality)
    }

    @Test
    fun testQualityNeverIncreaseAboveMaxQuality() {
        val item = EnhancedItem(Item("foo", 0, MAX_QUALITY))
        item.updateItem()
        assertEquals(MAX_QUALITY, item.quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = EnhancedItem(Item("foo", 0, 2))
        item.updateItem()
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun testCheckQualityValidityFailed() {
        assertThrows<IllegalArgumentException> {
            arrayOf<ItemWrapper>(EnhancedItem(
                    Item("foo", 3, MAX_QUALITY + 1)))
        }
    }

    @Test
    fun testCheckQualityValiditySucceed() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(EnhancedItem(Item("foo", 3, MAX_QUALITY)))
        }
    }
}


