package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BasicItemTest {

    @Test
    fun testQualityNeverDropBelow0() {
        val item = ItemWrapper.BasicItem(Item("foo", 0, 0))
        item.updateItem()
        assertEquals(0, item.quality)
    }

    @Test
    fun testQualityDecreaseByBasicQualityLostValueWhenSellInDateNotOver() {
        val baseQuality = 2
        val item = ItemWrapper.BasicItem(Item("foo", 2, baseQuality))
        item.updateItem()
        val expectedQuality = baseQuality - ItemWrapper.BASIC_QUALITY_LOST_VALUE
        assertEquals(expectedQuality, item.quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastWhenSellInDateOver() {
        val baseQuality = 2
        val item = ItemWrapper.BasicItem(Item("foo", 0, baseQuality))
        item.updateItem()
        val expectedQuality = baseQuality - ItemWrapper.OUT_OF_SELL_QUALITY_LOST_VALUE
        assertEquals(expectedQuality, item.quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = ItemWrapper.BasicItem(Item("foo", 0, 2))
        item.updateItem()
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


