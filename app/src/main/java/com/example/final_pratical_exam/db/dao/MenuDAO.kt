package com.example.final_pratical_exam.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reza.roomapplication.db.entities.MenuDatabase

@Dao
interface MenuDAO {

    @Query("SELECT * FROM MenuDatabase")
    fun getAllMenu(): MutableList<MenuDatabase>

    @Query("SELECT * FROM MenuDatabase where Quantity >=1")
    fun getAllMenuFromQuantity(): MutableList<MenuDatabase>

    @Query("SELECT * FROM MenuDatabase where id like :ID")
    fun getUserLive(ID: Int): MenuDatabase

    @Query("SELECT COUNT(*) FROM MenuDatabase where Quantity like :fromCountryName and Description like :fromCurrency")
    fun authenticate(fromCountryName: String, fromCurrency: String): Int

//    @Query("SELECT * FROM currencybook where id = :id")
//    fun getUser(id: Long): LiveData<MenuDatabase>

    @Insert()
    fun insert(menuDatabase: MenuDatabase): Long

    @Query("Delete from MenuDatabase where MenuImage=:currencyBook and Price=:fromAmount")
    fun delete(currencyBook: String?, fromAmount: Double?)

    @Query("UPDATE MenuDatabase SET Quantity =:quantity WHERE id=:menuid;")
    fun update(quantity: Int?, menuid: Int)

    @Query("Update MenuDatabase set Quantity=0 where id=:id")
    fun UpdateToQuantity(id: Int)

}