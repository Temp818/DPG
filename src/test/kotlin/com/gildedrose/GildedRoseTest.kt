package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun testQualityDecreaseForNormalItem() {
        val items = arrayOf(Item("foo", 5, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.items[0].quality)
    }

    @Test
    fun testSellInDecreaseForNormalItem() {
        val items = arrayOf(Item("foo", 5, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.items[0].sellIn)
    }

    @Test
    fun testQualityNeverDropBelow0() {
        val items = arrayOf(Item("foo", 5, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastForNormalItemWhenSellInPassed() {
        val items = arrayOf(Item("foo", 0, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.items[0].quality)
    }

    @Test
    fun testQualityIncreaseForAgedBrieItem() {
        val items = arrayOf(Item("Aged Brie", 1, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.items[0].quality)
    }

    @Test
    fun testSellInDecreaseForAgedBrieItem() {
        val items = arrayOf(Item("Aged Brie", 1, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
    }

    @Test
    fun testQualityIncreaseTwiceAsFastForAgedBrieItemWhenSellInPassed() {
        val items = arrayOf(Item("Aged Brie", 0, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(7, app.items[0].quality)
    }

    @Test
    fun testQualityNeverIncreaseAbove50() {
        val items = arrayOf(Item("Aged Brie", 2, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun testQualityAndSellInNeverDecreaseForSulfuraItem() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 2, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
        assertEquals(2, app.items[0].sellIn)
    }

    @Test
    fun testQualityIncreaseForBackstagePassesItemWhenMoreThan10Days() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(5, app.items[0].quality)
    }

    @Test
    fun testQualityIncreaseBy2ForBackstagePassesItemWhenLessThan10Days() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.items[0].quality)
    }

    @Test
    fun testQualityIncreaseBy3ForBackstagePassesItemWhenLessThan5Days() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(7, app.items[0].quality)
    }

    @Test
    fun testQualityDropTo0AfterConcert() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testSellInDecreaseForBackstagePassesItem() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 3, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.items[0].sellIn)
    }
}


