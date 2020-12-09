package com.inka.inkarestaurant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inka.inkarestaurant.R
import com.inka.inkarestaurant.activites.SecondActivity
import com.inka.inkarestaurant.model.MenuItems
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.card_row_menu_items.view.*

class AdapterOrder(private val listOrder: ArrayList<MenuItems>) :
    RecyclerView.Adapter<AdapterOrder.MyViewHolder>() {
    private var limit:Int= 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_row_menu_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindProduct(listOrder[position])
    }
    override fun getItemCount(): Int {
        return if (listOrder.size > limit){
            2
        }else{
            listOrder.size
        }
        //return listOrder.size
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindProduct(menu: MenuItems) {
            /* if (menu.quantity == 0) {
                try {
                    listOrder.indexOf(menu).apply {
                        listOrder.remove(menu)
                        notifyDataSetChanged()
                    }
                } catch (ex: Exception) {
                    ex.message
                }
            }
            else {*/
            itemView.btnAdd.visibility=View.GONE
            itemView.tvMenu.text = menu.menuName
            itemView.tvMenuDetails.text = menu.menuDetails
            itemView.tvmenuPrice.text = menu.price
            itemView.tvItemCount.text = menu.quantity.toString()
            if (menu.night == true) {
                itemView.tvNight.visibility = View.VISIBLE
            } else {
                itemView.tvNight.visibility = View.GONE
            }
            if (menu.day == true) {
                itemView.tvDay.visibility = View.VISIBLE
            } else {
                itemView.tvDay.visibility = View.GONE
            }

            itemView.tvAdd.setOnClickListener {
                val quantity: Int = (itemView.tvItemCount.text.toString()).toInt()
                try {
                    listOrder.indexOf(menu).apply {
                        menu.quantity = quantity + 1
                        (itemView.context as SecondActivity).totPrice()
                    }
                    itemView.tvItemCount.text = menu.quantity.toString()
                    var qty = 0
                    for (element in listOrder) {
                        qty += element.quantity!!
                    }
                } catch (ex: Exception) {
                    ex.message
                }
            }
            itemView.tvRemove.setOnClickListener {
                val quantity: Int = (itemView.tvItemCount.text.toString()).toInt()
                try {
                        listOrder.indexOf(menu).apply {
                            menu.quantity = quantity - 1
                            (itemView.context as SecondActivity).totPrice()
                        }
                        itemView.tvItemCount.text = menu.quantity.toString()

                    if (menu.quantity==0) {
                        listOrder.remove(menu)
                        notifyDataSetChanged()
                    }
                    if (listOrder.size == 0) {
                        (itemView.context as SecondActivity).intent(itemView.context)
                    }
                    /*var qty: Int = 0
                    for (element in listOrder) {
                        qty += element.quantity!!
                    }
                    (itemView.context as FirstScreen).updateViewCartCount(qty)*/
                } catch (ex: Exception) {
                    ex.message
                }
            }
            (itemView.context as SecondActivity).tvShowMore.setOnClickListener {
                try {
                    limit = listOrder.size
                    notifyDataSetChanged()
                    (itemView.context as SecondActivity).tvShowMore.visibility = View.GONE
                } catch (ex: Exception) {
                    ex.message
                }
            }
        }
    }
}