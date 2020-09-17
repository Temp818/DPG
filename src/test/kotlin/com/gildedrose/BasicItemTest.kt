package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BasicItemTest {

    @Test
    fun testQualityNeverDropBelow0() {
        val item = ItemWrapper.BasicItem(Item("foo", 0, 0))
        item.updateQuality()
        assertEquals(0, item.quality)
    }

    @Test
    fun testQualityDecreaseBy1WhenSellInDateNotOver() {
        val item = ItemWrapper.BasicItem(Item("foo", 2, 2))
        item.updateQuality()
        assertEquals(1, item.quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastWhenSellInDateOver() {
        val item = ItemWrapper.BasicItem(Item("foo", 0, 2))
        item.updateQuality()
        assertEquals(0, item.quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = ItemWrapper.BasicItem(Item("foo", 0, 2))
        item.updateSellIn()
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun testCheckQualityValidityFailed() {
        assertThrows<IllegalArgumentException> {
            arrayOf<ItemWrapper>(ItemWrapper.BasicItem(
                    Item("foo", 3, ItemWrapper.MAX_QUALITY + 1)))
        }
    }

    @Test
    fun testCheckQualityValiditySucceed() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(ItemWrapper.BasicItem(Item("foo", 3, ItemWrapper.MAX_QUALITY)))
        }
    }
}


