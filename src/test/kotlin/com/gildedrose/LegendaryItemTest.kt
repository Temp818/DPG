package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class LegendaryItemTest {

    @Test
    fun testQualityNeverChange() {
        val item = ItemWrapper.LegendaryItem(Item("foo", 11, 2))
        item.updateItem()
        assertEquals(2, item.quality)
    }

    @Test
    fun testSellInNeverChange() {
        val item = ItemWrapper.LegendaryItem(Item("foo", 10, 2))
        item.updateItem()
        assertEquals(10, item.sellIn)
    }

    @Test
    fun testCheckQualityValiditySucceedWithQualityGreaterThanMaxQuality() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(ItemWrapper.LegendaryItem(Item("foo", 3, ItemWrapper.MAX_QUALITY + 5)))
        }
    }
}


