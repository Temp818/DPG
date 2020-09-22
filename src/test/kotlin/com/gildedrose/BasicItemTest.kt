package com.gildedrose

import com.gildedrose.item.BasicItem
import com.gildedrose.item.ItemWrapper
import com.gildedrose.item.ItemWrapper.Companion.MAX_QUALITY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BasicItemTest {

    @Test
    fun testQualityNeverDropBelow0() {
        val item = BasicItem(Item("foo", 0, 0))
        item.updateItem()
        assertEquals(0, item.quality)
    }

    @Test
    fun testQualityDecreaseByBasicQualityLostValueWhenSellInDateNotOver() {
        val item = BasicItem(Item("foo", 2, 2))
        item.updateItem()
        assertEquals(1, item.quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastWhenSellInDateOver() {
        val item = BasicItem(Item("foo", 0, 2))
        item.updateItem()
        assertEquals(0, item.quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = BasicItem(Item("foo", 0, 2))
        item.updateItem()
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun testCheckQualityValidityFailed() {
        assertThrows<IllegalArgumentException> {
            arrayOf<ItemWrapper>(BasicItem(
                    Item("foo", 3, MAX_QUALITY + 1)))
        }
    }

    @Test
    fun testCheckQualityValiditySucceed() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(BasicItem(Item("foo", 3, MAX_QUALITY)))
        }
    }
}


