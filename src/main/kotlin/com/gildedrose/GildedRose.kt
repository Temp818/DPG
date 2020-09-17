package com.gildedrose

class GildedRose(var itemWrappers: Array<ItemWrapper>) {

    fun updateQuality() {
        itemWrappers.forEach {
            it.updateItem()
        }
    }
}

