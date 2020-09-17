package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class ConjuredItemTest {

    @Test
    fun testQualityNeverDropBelow0() {
        val item = ItemWrapper.ConjuredItem(Item("foo", 0, 0))
        item.updateQuality()
        assertEquals(0, item.quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastAsNormalWhenSellInDateNotOver() {
        val baseQuality = 2
        val item = ItemWrapper.ConjuredItem(Item("foo", 2, baseQuality))
        val quality = item.computeQuality()
        val expectedQuality = baseQuality - 2 * ItemWrapper.BASIC_QUALITY_LOST_VALUE
        assertEquals(expectedQuality, quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastAsNormalWhenSellInDateOver() {
        val baseQuality = 5
        val item = ItemWrapper.ConjuredItem(Item("foo", 0, baseQuality))
        val quality = item.computeQuality()
        val expectedQuality = baseQuality - 2 * ItemWrapper.OUT_OF_SELL_QUALITY_LOST_VALUE
        assertEquals(expectedQuality, quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = ItemWrapper.ConjuredItem(Item("foo", 0, 2))
        item.updateSellIn()
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun testCheckQualityValidityFailed() {
        assertThrows<IllegalArgumentException> {
            arrayOf<ItemWrapper>(ItemWrapper.ConjuredItem(
                    Item("foo", 3, ItemWrapper.MAX_QUALITY + 1)))
        }
    }

    @Test
    fun testCheckQualityValiditySucceed() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(ItemWrapper.ConjuredItem(Item("foo", 3, ItemWrapper.MAX_QUALITY)))
        }
    }
}


