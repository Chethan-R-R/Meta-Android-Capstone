package com.example.metaandroidcapstone

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.metaandroidcapstone.ui.theme.MetaAndroidCapstoneTheme
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var isLoggedIn = mutableStateOf(false)

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private val appDb by lazy {
        Room.databaseBuilder(applicationContext,AppDatabase::class.java,"littlelemon").build()
    }

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val email = mutableStateOf("")

    lateinit var menuItems:State<List<MenuItemRoom>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstName.value = sharedPreferences.getString("firstName","") ?: ""
        lastName.value = sharedPreferences.getString("lastName","") ?: ""
        email.value = sharedPreferences.getString("email","") ?: ""

        isLoggedIn.value =
            !(firstName.value.isBlank() || lastName.value.isBlank() || email.value.isBlank())

        setContent {
            MetaAndroidCapstoneTheme {
                 menuItems = appDb.menuDao().getAll().observeAsState(initial = emptyList())
                Surface(modifier = Modifier.fillMaxSize().background(Color.White)) {
                    MyNavigation(this,isLoggedIn.value)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (appDb.menuDao().menuIsEmpty()) {
                val menuList = fetch()
                saveMenuToDatabase(menuList)
            }
        }
    }


    fun storeUserDetails(fname: String, lname: String, e: String) {
        firstName.value = fname
        lastName.value = lname
        email.value =e
        sharedPreferences.edit().putString("firstName",firstName.value).apply()
        sharedPreferences.edit().putString("lastName",lastName.value).apply()
        sharedPreferences.edit().putString("email",email.value).apply()
    }
    fun deleteUserDetails(){
        sharedPreferences.edit().clear().apply()
    }

    private suspend fun fetch():List<MenuItemNetwork>{

        val res:MenuNetwork = NetworkHelper.HttpClient
            .get("https://my-json-server.typicode.com/Chethan-R-R/mock-littlelemon-menu-api/db")
            .body()
        return res.menu
    }

    private fun saveMenuToDatabase(menuItems:List<MenuItemNetwork>){
        val menuRoom = menuItems.map { it.networkToRoomMenu() }
        appDb.menuDao().insert(*menuRoom.toTypedArray())
    }

}




