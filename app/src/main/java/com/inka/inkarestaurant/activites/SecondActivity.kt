package com.inka.inkarestaurant.activites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inka.inkarestaurant.R
import com.inka.inkarestaurant.adapter.AdapterOrder
import com.inka.inkarestaurant.model.MenuItems
import com.inka.inkarestaurant.other.Constants
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    lateinit var int:ArrayList<MenuItems>
    private var orderList= ArrayList<MenuItems>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            orderList =
                bundle.getParcelableArrayList<MenuItems>(Constants.myList) as ArrayList<MenuItems> // 1
            //Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
             arrayOf(orderList).size
            showMore()
            try {
                // Total price funtion
                totPrice()
                rvOrder.adapter= AdapterOrder(orderList)
            }catch (ex:Exception){
                ex.message
                 }
            }
        ibBack.setOnClickListener {
            intent(this)
        }
        btnPlaceOrder.setOnClickListener {
            val id: Int = rgOrderType.checkedRadioButtonId
            if (id!=-1){
                toast("Order Place successfully")
                finishActivity(this)
            }else{
                toast("Please Select the Delivery Option")
            }
        }
        rgOrderType.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbDineIn) {
                toast("Dinner in ")
            } else if (checkedId == R.id.rbTakeWay) {
                toast("Order is take way")
            }

        }

    }

    override fun onBackPressed() {
        intent(this)
    }
    fun intent(context: Context){
        val intent = Intent(context, SecondActivity::class.java)
        /*val bundle = Bundle()
        bundle.putParcelableArrayList(Constants.myList,orderList)
        intent.putExtras(bundle)*/
        intent.putParcelableArrayListExtra(Constants.keyList,orderList)
        setResult(RESULT_OK,intent)
        finish()
    }
    fun totPrice(){
        var totPrice =0.00
        for(n in 0 until orderList.size){
            orderList[n].price
            totPrice += (orderList[n].price?.toDouble()!! * orderList[n].quantity!!)
        }
        tvToalPrice.text=(String.format("%.2f", totPrice))
    }
    private fun showMore(){
        if(orderList.size <= 2){
            tvShowMore.visibility= View.GONE
        }else{
            tvShowMore.visibility= View.VISIBLE
        }
    }
    private fun Context.toast(info: String) {
        Toast.makeText(this, info, Toast.LENGTH_LONG).show()
    }
    private fun finishActivity(context: Context){
        val intent=Intent(context,FirstScreen::class.java)
        startActivity(intent)
        finish()
    }
}