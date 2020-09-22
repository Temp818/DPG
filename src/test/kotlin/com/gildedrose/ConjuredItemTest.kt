package com.gildedrose

import com.gildedrose.item.ConjuredItem
import com.gildedrose.item.ItemWrapper
import com.gildedrose.item.ItemWrapper.Companion.BASIC_QUALITY_LOST_VALUE
import com.gildedrose.item.ItemWrapper.Companion.MAX_QUALITY
import com.gildedrose.item.ItemWrapper.Companion.OUT_OF_SELL_QUALITY_LOST_VALUE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class ConjuredItemTest {

    @Test
    fun testQualityNeverDropBelow0() {
        val item = ConjuredItem(Item("foo", 0, 0))
        item.updateItem()
        assertEquals(0, item.quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastAsNormalWhenSellInDateNotOver() {
        val baseQuality = 2
        val item = ConjuredItem(Item("foo", 2, baseQuality))
        item.updateItem()
        val expectedQuality = baseQuality - 2 * BASIC_QUALITY_LOST_VALUE
        assertEquals(expectedQuality, item.quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastAsNormalWhenSellInDateOver() {
        val baseQuality = 5
        val item = ConjuredItem(Item("foo", 0, baseQuality))
        item.updateItem()
        val expectedQuality = baseQuality - 2 * OUT_OF_SELL_QUALITY_LOST_VALUE
        assertEquals(expectedQuality, item.quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = ConjuredItem(Item("foo", 0, 2))
        item.updateItem()
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun testCheckQualityValidityFailed() {
        assertThrows<IllegalArgumentException> {
            arrayOf<ItemWrapper>(ConjuredItem(
                    Item("foo", 3, MAX_QUALITY + 1)))
        }
    }

    @Test
    fun testCheckQualityValiditySucceed() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(ConjuredItem(Item("foo", 3, MAX_QUALITY)))
        }
    }
}


