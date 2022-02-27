package com.example.final_pratical_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.example.final_pratical_exam.db.AppDatabase
import com.reza.roomapplication.db.entities.MenuDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var dataBase: AppDatabase
    private lateinit var logoImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataForMenu = listOf(
            MenuDatabase(1, "Kunsei Sake", "Smoked Salmon, rice", 3.95, R.drawable.i01),
            MenuDatabase(2, "Sake", "Fresh Salmon, rice", 2.95, R.drawable.i02),
            MenuDatabase(3, "Tai", "Tilapia, rice", 2.95, R.drawable.i03),
            MenuDatabase(4, "Elbi", "Shrimp, rice", 3.95, R.drawable.i04),
            MenuDatabase(5, "Maguro", "Tuna, rice", 4.5, R.drawable.i05),
            MenuDatabase(6, "Unagi", "Grilled eel, rice, sesame", 4.5, R.drawable.i06),
            MenuDatabase(7, "Kani Kama", "Pollock, rice", 3.95, R.drawable.i07),
            MenuDatabase(8, "Tamago", "Omelet, rice", 2.95, R.drawable.i08),
            MenuDatabase(
                9,
                "Kunsei Sale Tempura",
                "Smoked salmon, tempura, naniyori sauce",
                4.25,
                R.drawable.i09
            ),
            MenuDatabase(
                10,
                "Hotategai",
                "Scallops, tempura, naniyori sauce",
                4.25,
                R.drawable.i10
            ),
            MenuDatabase(11, "Tobiko", "Flying fish caviar", 4.25, R.drawable.i11),
            MenuDatabase(12, "Masago", "Caplin fish caviar", 2.95, R.drawable.i12),
            MenuDatabase(
                13,
                "Sake Tempura",
                "Fresh salmon, tempura, caviar, naniyori sauce",
                4.5,
                R.drawable.i13
            ),
            MenuDatabase(
                14,
                "Maguro Tempura",
                "Tuna, tempura, caviar, naniyori sauce",
                4.55,
                R.drawable.i14
            ),
            MenuDatabase(
                15,
                "Crab Tempura",
                "Crab, tempura, caviar, naniyori sauce",
                4.55,
                R.drawable.i15
            ),
            MenuDatabase(
                16,
                "Shrimp Tempura",
                "Shrimp, tempura, caviar, naniyori sauce",
                4.50,
                R.drawable.i16
            ),
            MenuDatabase(
                17,
                "Sakura",
                "Scallop, cucumber, caviar, naniyori sauce",
                4.25,
                R.drawable.i17
            ),
            MenuDatabase(18, "Furai", "Fried shrimp", 4.25, R.drawable.i18),
            MenuDatabase(19, "Kappa Makis", "Cucumber, sesame", 2.10, R.drawable.i19),
            MenuDatabase(20, "Avocado Caviar", "Avocado, sesame, red caviar", 2.35, R.drawable.i20),
            MenuDatabase(21, "Oshinko", "Pickled radish, sesame, sauce", 2.25, R.drawable.i21),
            MenuDatabase(22, "Sake Makis", "Fresh salmon, shallots, sesame", 4.05, R.drawable.i22),
            MenuDatabase(
                23,
                "Sake Makis spice",
                "Fresh salmon, shallots, sesame, naniyori sauce",
                4.05,
                R.drawable.i23
            ),
            MenuDatabase(24, "Tekka Makis", "Tuna, shallots, sesame", 4.65, R.drawable.i24),
            MenuDatabase(26, "Una Kuy", "Eel, cucumber, sesame", 4.35, R.drawable.i26),
            MenuDatabase(27, "Tai Makis", "Tilapia, shallots, sesame", 4.05, R.drawable.i27),
            MenuDatabase(
                28,
                "Smoked salmon",
                "Smoked salmon, avocado, cream cheese, sesame",
                6.05,
                R.drawable.i28
            ),
            MenuDatabase(
                29,
                "Shusi Naniori",
                "Tuna, avocado, masago, shallots, tempura, naniyori sauce",
                6.35,
                R.drawable.i29
            ),
            MenuDatabase(
                30,
                "Shushi Shrimp",
                "Shrimp, avocado, tempura, cucumber, caviar, sesame, naniyori sauce",
                6.05,
                R.drawable.i30
            )
        )
        dataBase = AppDatabase.getDatabaseInstance(this)
        AppDatabase.databaseWriteExecutor.execute {
            val detailsOfMenu = dataBase.MenuDAO().getAllMenu()
            if (detailsOfMenu.count() == 0) {
                for (pos in dataForMenu) {
                    dataBase.MenuDAO().insert(pos)
                }
            }
        }

        logoImage = findViewById(R.id.main_logo)
        logoImage.setOnClickListener(this::getImageClick)
    }


    private fun getImageClick(view: View) {
        val intent = Intent(this, Menu_Activity::class.java)
        startActivity(intent)
        finish()
    }
}