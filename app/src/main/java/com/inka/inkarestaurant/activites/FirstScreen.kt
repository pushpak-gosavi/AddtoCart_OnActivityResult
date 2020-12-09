package com.inka.inkarestaurant.activites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inka.inkarestaurant.R
import com.inka.inkarestaurant.adapter.AdapterMenuItems
import com.inka.inkarestaurant.model.MenuItems
import com.inka.inkarestaurant.other.Constants
import kotlinx.android.synthetic.main.activity_first.*

class FirstScreen : AppCompatActivity() {
    private val menuItems = ArrayList<MenuItems>()
    private val selectItems = ArrayList<MenuItems>()
    private var viewCartCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        tvCart.setOnClickListener {
                intent(this)
        }
        menuList()
    }

    private fun intent(context: Context) {
        try {
            if (selectItems.isEmpty().not()) {
                selectItems.clear()
            }
            menuItems.forEach { event ->
                if (event.quantity != 0) {
                    selectItems.add(event)
                }
            }
        } catch (ex: Exception) {
            ex.message
        }
        val intent = Intent(context, SecondActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelableArrayList(Constants.myList, selectItems)
        intent.putExtras(bundle)
        startActivityForResult(intent, Constants.REQUEST_CODE)
    }

    private fun menuList() {
        try {
            // menu items
            menuItems.add(
                MenuItems(
                    1,
                    "Guac de la Costa",
                    "tortllias de mais, fruit de la passion, mango",
                    "7",
                    night = true,
                    day = true,
                    quantity = 0
                )
            )
            menuItems.add(
                MenuItems(
                    2,
                    "Chincharron y Cerveza",
                    "citron vert / Corona sauce",
                    "7",
                    night = true,
                    day = false,
                    quantity = 0
                )
            )
            menuItems.add(
                MenuItems(
                    3,
                    "Guac de la Costa",
                    "tortlias de mais, fruit de la passion, mango",
                    "7",
                    night = true,
                    day = false,
                    quantity = 0
                )
            )
            rvHotelMenues.adapter = AdapterMenuItems(menuItems)
        } catch (ex: Exception) {
            ex.message
        }
    }

    fun updateViewCartCount(quantity: Int?) {
        val message = "View Cart ( $quantity ) Items"
        tvCart.text = message
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check that it is the SecondActivity with an OK result
        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Get list data from Intent
                if (null != data) {
                    /*val bundle: Bundle? = intent.extras
                    if (bundle != null) {
                        val returnList =
                            data.getParcelableArrayListExtra<MenuItems>(Constants.keyList)
                        // toast(returnList.toString())
                    }*/
                    try {
                        menuItems.clear()
                        menuList()
                        viewCartCount = 0
                        val returnList =
                            data.getParcelableArrayListExtra<MenuItems>(Constants.keyList)
                        /*if(returnList?.size==0){
                            menuItems.clear()
                            menuList()
                            rvHotelMenues.adapter=
                            AdapterMenuItems(menuItems)
                            updateViewCartCount(0)
                        }
                        else {*/

                            for (n in 0 until returnList?.size!!) {
                                val menuID = returnList[n].menuID
                                for (i in 0 until menuItems.size) {
                                    val innerMenuid = menuItems[i].menuID
                                    if (menuID == innerMenuid) {
                                        menuItems.apply {
                                            menuItems[i].quantity = 0
                                            menuItems[i].quantity =
                                                menuItems[i].quantity?.plus(returnList[n].quantity!!)
                                            viewCartCount += menuItems[i].quantity!!
                                        }
                                    } else {
                                        print("Fail")
                                        /*menuItems.apply {
                                            if (menuItems[i].quantity==1){
                                                menuItems[i].quantity =0
                                            }
                                        }*/
                                    }
                                }
                            }
                            updateViewCartCount(viewCartCount)
                            print(menuItems)
                            rvHotelMenues.adapter = AdapterMenuItems(menuItems)
                       // }

                        // returnList?.let { selectItems.addAll(it) }
                        /*val result= ArrayList<MenuItems>()
                        toast(result.toString())*/


                    } catch (ex: Exception) {
                        ex.message
                    }
                } else {
                    toast("No data found")
                    // val returnList = data?.getParcelableArrayListExtra<MenuItems>(Constants.keyList)
                    // Toast.makeText(this, returnList.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun Context.toast(info: String) {
        Toast.makeText(this, info, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}