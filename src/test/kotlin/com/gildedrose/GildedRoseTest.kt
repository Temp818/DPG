package com.gildedrose

import com.gildedrose.item.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun testQualityDecreaseForBasicItem() {
        val items = arrayOf<ItemWrapper>(BasicItem(Item("foo", 5, 5)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.itemWrappers[0].quality)
    }

    @Test
    fun testSellInDecreaseForBasicItem() {
        val items = arrayOf<ItemWrapper>(BasicItem(Item("foo", 5, 5)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.itemWrappers[0].sellIn)
    }

    @Test
    fun testQualityNeverDropBelow0() {
        val items = arrayOf<ItemWrapper>(BasicItem(Item("foo", 5, 0)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.itemWrappers[0].quality)
    }

    @Test
    fun testQualityDecreaseTwiceAsFastForBasicItemWhenSellInPassed() {
        val items = arrayOf<ItemWrapper>(BasicItem(Item("foo", 0, 4)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.itemWrappers[0].quality)
    }

    @Test
    fun testQualityIncreaseForEnhancedItem() {
        val items = arrayOf<ItemWrapper>(EnhancedItem(Item("Aged Brie", 1, 5)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.itemWrappers[0].quality)
    }

    @Test
    fun testSellInDecreaseForEnhancedItem() {
        val items = arrayOf<ItemWrapper>(EnhancedItem(Item("Aged Brie", 1, 5)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.itemWrappers[0].sellIn)
    }

    @Test
    fun testQualityIncreaseTwiceAsFastForEnhancedItemWhenSellInPassed() {
        val items = arrayOf<ItemWrapper>(EnhancedItem(Item("Aged Brie", 0, 5)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(7, app.itemWrappers[0].quality)
    }

    @Test
    fun testQualityNeverIncreaseAbove50() {
        val items = arrayOf<ItemWrapper>(EnhancedItem(Item("Aged Brie", 2, 50)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.itemWrappers[0].quality)
    }

    @Test
    fun testQualityAndSellInNeverDecreaseForLegendaryItem() {
        val items = arrayOf<ItemWrapper>(LegendaryItem(
                Item("Sulfuras, Hand of Ragnaros", 2, 50)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.itemWrappers[0].quality)
        assertEquals(2, app.itemWrappers[0].sellIn)
    }

    @Test
    fun testQualityIncreaseForBackstageItemWhenMoreThan10Days() {
        val items = arrayOf<ItemWrapper>(BackstageItem(
                Item("Backstage passes to a TAFKAL80ETC concert", 11, 4)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(5, app.itemWrappers[0].quality)
    }

    @Test
    fun testQualityIncreaseBy2ForBackstageItemWhen10Days() {
        val items = arrayOf<ItemWrapper>(BackstageItem(
                Item("Backstage passes to a TAFKAL80ETC concert", 10, 4)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.itemWrappers[0].quality)
    }

    @Test
    fun testQualityIncreaseBy3ForBackstageItemWhen5Days() {
        val items = arrayOf<ItemWrapper>(BackstageItem(
                Item("Backstage passes to a TAFKAL80ETC concert", 5, 4)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(7, app.itemWrappers[0].quality)
    }

    @Test
    fun testQualityDropTo0AfterConcert() {
        val items = arrayOf<ItemWrapper>(BackstageItem(
                Item("Backstage passes to a TAFKAL80ETC concert", 0, 4)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.itemWrappers[0].quality)
    }

    @Test
    fun testSellInDecreaseForBackstageItem() {
        val items = arrayOf<ItemWrapper>(BackstageItem(
                Item("Backstage passes to a TAFKAL80ETC concert", 3, 4)))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.itemWrappers[0].sellIn)
    }
}


