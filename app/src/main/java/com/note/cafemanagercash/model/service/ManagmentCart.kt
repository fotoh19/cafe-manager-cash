package com.note.cafemanagercash.model.service

import android.content.Context
import android.widget.Toast
import com.note.cafemanagercash.model.ItemsModel

class ManagmentCart(val context: Context) {

    private val cartDao = CartDatabase.getDatabase(context).itemtDao()

    fun insertItems(item: ItemsModel) {
        val listItem = getListCart()
        val existAlready = listItem.any { it.title == item.title }
        val index = listItem.indexOfFirst { it.title == item.title }

        if (existAlready) {
            val existingItem = listItem[index]
            existingItem.numberInCart = item.numberInCart
            cartDao.updateItem(existingItem)
        } else {
            cartDao.insertItem(item)
        }
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): List<ItemsModel> {
        return cartDao.getAllItems()
    }

    fun minusItem(
        listItems: List<ItemsModel>,
        position: Int,
        listener: ChangeNumberItemsListener
    ) {
        val item = listItems[position]
        if (item.numberInCart == 1) {
            cartDao.deleteItem(item)
        } else {
            item.numberInCart--
            cartDao.updateItem(item)
        }
        listener.onChanged()
    }

    fun plusItem(
        listItems: List<ItemsModel>,
        position: Int,
        listener: ChangeNumberItemsListener
    ) {
        val item = listItems[position]
        item.numberInCart++
        cartDao.updateItem(item)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        val listItem = getListCart()
        var fee = 0.0
        for (item in listItem) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}
