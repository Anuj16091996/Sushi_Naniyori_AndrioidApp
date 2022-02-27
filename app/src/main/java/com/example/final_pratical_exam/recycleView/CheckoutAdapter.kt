package com.example.final_pratical_exam.recycleView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_pratical_exam.R
import com.example.final_pratical_exam.db.AppDatabase
import com.reza.roomapplication.db.entities.MenuDatabase

class CheckoutAdapter() : RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {
    private val dataset = mutableListOf<MenuDatabase>()

    class CheckoutViewHolder(private val parent: CheckoutAdapter, private val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        var position: MenuDatabase? = null
        val MenuImage: ImageView = containerView.findViewById(R.id.checkout_menuImage)
        val menuTitle: TextView = containerView.findViewById(R.id.checkout_menuTitle)
        val menuDescription: TextView = containerView.findViewById(R.id.checkout_menuDescription)
        val menuPrice: TextView = containerView.findViewById(R.id.checkout_menuPrice)
        val quantityHolder: TextView = containerView.findViewById(R.id.checkout_quantity)
        val finalPrice: TextView = containerView.findViewById(R.id.checkout_finalprice)
        val context: Context = menuTitle.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CheckoutViewHolder(this, view)
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        val currentData = dataset[position]
        holder.menuTitle.text = currentData.MenuTitle
        holder.menuDescription.setText(currentData.Description)
        holder.menuPrice.setText(currentData.Price.toString())
        holder.quantityHolder.setText(currentData.Quantity.toString())
        holder.position = dataset[position]
        currentData.MenuImage?.let { holder.MenuImage.setImageResource(it) }
        val price = (currentData.Quantity)?.times(currentData.Price!!)?.toDouble()
        holder.finalPrice.setText(price.toString())
    }

    fun InsertAllData(data: MutableList<MenuDatabase>) {
        this.dataset.clear()
        this.dataset.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataset.count()

    override fun getItemViewType(position: Int): Int {

        return R.layout.checkout_menu
    }
}