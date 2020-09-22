package com.gildedrose

import com.gildedrose.item.ItemWrapper
import com.gildedrose.item.ItemWrapper.Companion.MAX_QUALITY
import com.gildedrose.item.LegendaryItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class LegendaryItemTest {

    @Test
    fun testQualityNeverChange() {
        val item = LegendaryItem(Item("foo", 11, 2))
        item.updateItem()
        assertEquals(2, item.quality)
    }

    @Test
    fun testSellInNeverChange() {
        val item = LegendaryItem(Item("foo", 10, 2))
        item.updateItem()
        assertEquals(10, item.sellIn)
    }

    @Test
    fun testCheckQualityValiditySucceedWithQualityGreaterThanMaxQuality() {
        assertDoesNotThrow {
            arrayOf<ItemWrapper>(LegendaryItem(Item("foo", 3, MAX_QUALITY + 5)))
        }
    }
}


