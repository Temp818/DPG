package com.gildedrose

import com.gildedrose.item.ItemWrapper

class GildedRose(var itemWrappers: Array<ItemWrapper>) {

    fun updateQuality() {
        itemWrappers.forEach {
            it.updateItem()
        }
    }
}

