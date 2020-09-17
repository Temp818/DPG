package com.gildedrose

fun main(args: Array<String>) {

    println("OMGHAI!")

    val items = arrayOf(ItemWrapper.BasicItem(Item("+5 Dexterity Vest", 10, 20)), //
            ItemWrapper.EnhancedItem(Item("Aged Brie", 2, 0)), //
            ItemWrapper.BasicItem(Item("Elixir of the Mongoose", 5, 7)), //
            ItemWrapper.LegendaryItem(Item("Sulfuras, Hand of Ragnaros", 0, 80)), //
            ItemWrapper.LegendaryItem(Item("Sulfuras, Hand of Ragnaros", -1, 80)),
            ItemWrapper.BackstageItem(Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)),
            ItemWrapper.BackstageItem(Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)),
            ItemWrapper.BackstageItem(Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)),
            ItemWrapper.ConjuredItem(Item("Conjured Mana Cake", 3, 6)))

    val app = GildedRose(items)

    var days = 5
    if (args.size > 0) {
        days = Integer.parseInt(args[0]) + 1
    }

    for (i in 0..days - 1) {
        println("-------- day $i --------")
        println("name, sellIn, quality")
        for (item in items) {
            println(item)
        }
        println()
        app.updateQuality()
    }


}
