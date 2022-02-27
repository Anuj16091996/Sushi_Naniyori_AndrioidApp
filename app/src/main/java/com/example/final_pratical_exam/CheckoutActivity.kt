package com.example.final_pratical_exam

import android.content.DialogInterface
import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.final_pratical_exam.db.AppDatabase
import com.example.final_pratical_exam.recycleView.CheckoutAdapter

class CheckoutActivity : AppCompatActivity() {
    private lateinit var dataBase: AppDatabase
    private lateinit var purchaseButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var checkOutTotal: TextView
    var checkoutAdapter = CheckoutAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        dataBase = AppDatabase.getDatabaseInstance(this)
        recyclerView = findViewById<RecyclerView>(R.id.checkout_recycle)
        checkOutTotal = findViewById(R.id.check_out_total)
        val formatter = NumberFormat.getCurrencyInstance()
        AppDatabase.databaseWriteExecutor.execute {
            val detailsOfMenu = dataBase.MenuDAO().getAllMenuFromQuantity()
            recyclerView.adapter = checkoutAdapter
            checkoutAdapter.InsertAllData(detailsOfMenu)
            var finalAmount: Double = 0.00
            for (pos in detailsOfMenu) {
                val price = (pos.Quantity)?.times(pos.Price!!)?.toDouble()
                finalAmount = finalAmount + price!!
            }
            val moneyString: String = formatter.format(finalAmount)
            checkOutTotal.setText(" ${moneyString}")
        }

        purchaseButton = findViewById(R.id.checkout_purchase)
        purchaseButton.setOnClickListener(this::checkOutConfirmation)
    }


    private fun checkOutConfirmation(view: View) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this).apply {
            setTitle("Select Your Options")
        }
        builder.setPositiveButton("Accept", this::userSelect)
        builder.setNegativeButton("Modify", this::userSelect)
        builder.setNeutralButton("Cancel", this::userSelect)
        builder.show()
    }

    private fun userSelect(dialog: DialogInterface, which: Int) {
        when (which) {


            DialogInterface.BUTTON_POSITIVE -> {
                AppDatabase.databaseWriteExecutor.execute {
                    val detailsOfMenu = dataBase.MenuDAO().getAllMenuFromQuantity()
                    for (pos in detailsOfMenu) {
                        dataBase.MenuDAO().UpdateToQuantity(pos.id)
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            DialogInterface.BUTTON_NEGATIVE -> {
                finish()
            }
            DialogInterface.BUTTON_NEUTRAL -> {

            }
        }

    }
}