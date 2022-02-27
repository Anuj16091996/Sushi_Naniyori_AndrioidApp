package com.example.myapplication_discorg_album.recycleView

import android.content.Context
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.final_pratical_exam.R
import com.example.final_pratical_exam.db.AppDatabase
import com.reza.roomapplication.db.entities.MenuDatabase


class MenuAdapter() : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private val dataset = mutableListOf<MenuDatabase>()


    class MenuViewHolder(private val parent: MenuAdapter, private val containerView: View) :
        RecyclerView.ViewHolder(containerView) {


        var position: MenuDatabase? = null
        val positiveClick: Button = containerView.findViewById(R.id.custom_positive)
        val NegativeClick: Button = containerView.findViewById(R.id.custom_negative)
        val MenuImage: ImageView = containerView.findViewById(R.id.custom_menuImage)
        val menuTitle: TextView = containerView.findViewById(R.id.custom_menuTitle)
        val menuDescription: TextView = containerView.findViewById(R.id.custom_menuDescription)
        val menuPrice: TextView = containerView.findViewById(R.id.custom_menuPrice)
        val quantityHolder: TextView = containerView.findViewById(R.id.custom_quantity)
        val context: Context = menuTitle.context
        val dataBase = AppDatabase.getDatabaseInstance(context)

        init {
            positiveClick.setOnClickListener {

                AppDatabase.databaseWriteExecutor.execute {
                    val menu = position?.let { it1 -> dataBase.MenuDAO().getUserLive(it1.id) }
                    val quantity = menu?.Quantity?.plus(1)
                    position?.let { it1 -> dataBase.MenuDAO().update(quantity, it1.id) }
                    quantityHolder.setText(quantity.toString())

                }
            }

            NegativeClick.setOnClickListener {
                AppDatabase.databaseWriteExecutor.execute {
                    val menu = position?.let { it1 -> dataBase.MenuDAO().getUserLive(it1.id) }
                    if (menu != null) {
                        if (menu.Quantity!! >= 1) {
                            val quantity = menu?.Quantity?.minus(1)
                            position?.let { it1 -> dataBase.MenuDAO().update(quantity, it1.id) }
                            quantityHolder.setText(quantity.toString())
                        }
                    } else {
                        Toast.makeText(context, "Value Can not be less them 0", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return MenuViewHolder(this, view)
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentData = dataset[position]
        holder.menuTitle.text = currentData.MenuTitle
        holder.menuDescription.setText(currentData.Description)
        holder.menuPrice.setText(currentData.Price.toString())
        holder.quantityHolder.setText(currentData.Quantity.toString())
        holder.position = dataset[position]
        currentData.MenuImage?.let { holder.MenuImage.setImageResource(it) }
    }

    fun addData(menu: MenuDatabase) {
        this.dataset.add(menu)
        notifyDataSetChanged()
    }


    fun InsertAllData(data: MutableList<MenuDatabase>) {
        this.dataset.clear()
        this.dataset.addAll(data)
        notifyDataSetChanged()
    }

    fun removeData(menu: MenuDatabase) {
        this.dataset.remove(menu)
        notifyDataSetChanged()
    }

    fun removeAllData() {
        this.dataset.clear()
        notifyDataSetChanged()
    }


    override fun getItemCount() = dataset.count()

    override fun getItemViewType(position: Int): Int {

        return R.layout.custom_menu
    }


}



