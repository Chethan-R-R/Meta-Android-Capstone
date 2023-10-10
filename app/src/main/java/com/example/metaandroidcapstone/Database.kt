package com.example.metaandroidcapstone

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class MenuItemRoom(
    @PrimaryKey val id:Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface MenuDao{
    @Query("SELECT * FROM MenuItemRoom")
    fun getAll():LiveData<List<MenuItemRoom>>

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
    fun menuIsEmpty():Boolean

    @Insert
    fun insert(vararg menuItem: MenuItemRoom)
}

@Database(entities = [MenuItemRoom::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun menuDao():MenuDao
}