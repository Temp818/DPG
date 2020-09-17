package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BackstageItemTest {

    @Test
    fun testQualityIncreaseBy1WhenSellInDateOver10Days() {
        val item = ItemWrapper.BackstageItem(Item("foo", 11, 2))
        item.updateItem()
        assertEquals(3, item.quality)
    }

    @Test
    fun testQualityIncreaseBy2WhenSellInDateIs10Days() {
        val item = ItemWrapper.BackstageItem(Item("foo", 10, 2))
        item.updateItem()
        assertEquals(4, item.quality)
    }

    @Test
    fun testQualityIncreaseBy2WhenSellInDateIs6Days() {
        val item = ItemWrapper.BackstageItem(Item("foo", 6, 2))
        item.updateItem()
        assertEquals(4, item.quality)
    }

    @Test
    fun testQualityIncreaseBy2WhenSellInDateIsBetween10And6Days() {
        val item = ItemWrapper.BackstageItem(Item("foo", 7, 2))
        item.updateItem()
        assertEquals(4, item.quality)
    }

    @Test
    fun testQualityIncreaseBy3WhenSellInDateIs5Days() {
        val item = ItemWrapper.BackstageItem(Item("foo", 5, 2))
        item.updateItem()
        assertEquals(5, item.quality)
    }

    @Test
    fun testQualityIncreaseBy3WhenSellInDateIs1Day() {
        val item = ItemWrapper.BackstageItem(Item("foo", 1, 2))
        item.updateItem()
        assertEquals(5, item.quality)
    }

    @Test
    fun testQualityIncreaseBy3WhenSellInDateIsBetween5And1Days() {
        val item = ItemWrapper.BackstageItem(Item("foo", 2, 2))
        item.updateItem()
        assertEquals(5, item.quality)
    }

    @Test
    fun testQualityDropTo0WhenSellInDateIs0() {
        val item = ItemWrapper.BackstageItem(Item("foo", 0, 2))
        item.updateItem()
        assertEquals(0, item.quality)
    }

    @Test
    fun testQualityIncreaseBy3WhenSellInDateIsBelow0() {
        val item = ItemWrapper.BackstageItem(Item("foo", -1, 2))
        item.updateItem()
        assertEquals(0, item.quality)
    }

    @Test
    fun testQualityNeverIncreaseAboveMaxQuality() {
        val item = ItemWrapper.BackstageItem(Item("foo", 3, ItemWrapper.MAX_QUALITY))
        item.updateItem()
        assertEquals(ItemWrapper.MAX_QUALITY, item.quality)
    }

    @Test
    fun testSellInDateDecrease() {
        val item = ItemWrapper.BackstageItem(Item("foo", 0, 2))
        item.updateItem()
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun testCheckQualityValidityFailed() {
        assertThrows<IllegalArgumentException> {
            arrayOf<ItemWrapper>(ItemWrapper.BackstageItem(
                    Item("foo", 3, ItemWrapper.MAX_QUALITY + 1)))
        }
    }

    @Test
    fun testCheckQualityValiditySucceed() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(ItemWrapper.BackstageItem(Item("foo", 3, ItemWrapper.MAX_QUALITY)))
        }
    }
}


