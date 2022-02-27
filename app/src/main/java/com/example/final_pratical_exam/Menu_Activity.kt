package com.example.final_pratical_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.final_pratical_exam.db.AppDatabase
import com.example.myapplication_discorg_album.recycleView.MenuAdapter

class Menu_Activity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataBase: AppDatabase
    private lateinit var checkoutButton: Button
    private var dataBoolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val menuAdapter = MenuAdapter()
        dataBase = AppDatabase.getDatabaseInstance(this)
        recyclerView = findViewById<RecyclerView>(R.id.menu_recycle)
        AppDatabase.databaseWriteExecutor.execute {
            val detailsOfMenu = dataBase.MenuDAO().getAllMenu()
            recyclerView.adapter = menuAdapter
            menuAdapter.InsertAllData(detailsOfMenu)
        }
        checkoutButton = findViewById(R.id.menu_checkout)
        checkoutButton.setOnClickListener(this::checkOutItems)
    }


    private fun checkOutItems(view: View) {
        val intent = Intent(this, CheckoutActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        dataBoolean = false
    }

    override fun onResume() {
        super.onResume()
        if (!dataBoolean) {
            finish();
            startActivity(getIntent());
        }
    }
}