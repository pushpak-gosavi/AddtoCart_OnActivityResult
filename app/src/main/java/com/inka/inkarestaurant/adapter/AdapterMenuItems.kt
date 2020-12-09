package com.inka.inkarestaurant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inka.inkarestaurant.R
import com.inka.inkarestaurant.activites.FirstScreen
import com.inka.inkarestaurant.model.MenuItems
import kotlinx.android.synthetic.main.card_row_menu_items.view.*

class AdapterMenuItems(private val listMenuItems: List<MenuItems>) :
    RecyclerView.Adapter<AdapterMenuItems.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_row_menu_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindProduct(listMenuItems[position])
    }

    override fun getItemCount(): Int {
        return listMenuItems.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindProduct(menu: MenuItems) {
            itemView.tvMenu.text= menu.menuName
            itemView.tvMenuDetails.text=menu.menuDetails
            itemView.tvmenuPrice.text= menu.price
            itemView.tvItemCount.text= menu.quantity.toString()
            if(menu.quantity!=0){
                itemView.btnAdd.visibility=View.GONE
            }else{
                itemView.btnAdd.visibility=View.VISIBLE
            }
            if (menu.night==true){
                itemView.tvNight.visibility=View.VISIBLE
            }else{
                itemView.tvNight.visibility=View.GONE
            }
            if (menu.day==true){
                itemView.tvDay.visibility=View.VISIBLE
            }else{
                itemView.tvDay.visibility=View.GONE
            }
            itemView.btnAdd.setOnClickListener {
                val quantity:Int=(itemView.tvItemCount.text.toString()).toInt()
                try{
                    listMenuItems.indexOf(menu).apply {
                        menu.quantity= quantity+1
                    }
                    itemView.tvItemCount.text= menu.quantity.toString()
                    var qty=0
                    for(element in listMenuItems){
                        qty+= element.quantity!!
                    }
                    (itemView.context as FirstScreen).updateViewCartCount(qty)
                    itemView.btnAdd.visibility=View.GONE
                }catch (ex:Exception){
                    ex.message
                }
            }
            itemView.tvAdd.setOnClickListener {
                val quantity:Int=(itemView.tvItemCount.text.toString()).toInt()
                try{
                    listMenuItems.indexOf(menu).apply {
                        menu.quantity= quantity+1
                    }
                    itemView.tvItemCount.text= menu.quantity.toString()
                    var qty=0
                    for(element in listMenuItems){
                        qty+= element.quantity!!
                    }
                    (itemView.context as FirstScreen).updateViewCartCount(qty)
                }catch (ex:Exception){
                    ex.message
                }
            }
            itemView.tvRemove.setOnClickListener {
                val quantity:Int=(itemView.tvItemCount.text.toString()).toInt()
                if(quantity!=0){
                    try{
                        listMenuItems.indexOf(menu).apply {
                            menu.quantity= quantity-1
                        }
                        itemView.tvItemCount.text= menu.quantity.toString()
                        if(menu.quantity==0){
                            itemView.btnAdd.visibility=View.VISIBLE
                        }
                        var qty=0
                        for(element in listMenuItems){
                            qty+= element.quantity!!
                        }
                        (itemView.context as FirstScreen).updateViewCartCount(qty)

                    }catch (ex:Exception){
                        ex.message
                    }
                }else{
                    itemView.btnAdd.visibility=View.VISIBLE
                }
            }
        }
    }
}