package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {
    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })
    private var autoIncrementId = 0
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    init {
        for (i in 0 until 10)
        {
            val item = ShopItem( "Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }
    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.add(shopItem)
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        autoIncrementId ++
        shopList.add(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem){
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find{it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
     shopListLD.value = shopList.toList()
    }

}