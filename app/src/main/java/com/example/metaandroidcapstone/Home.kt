package com.example.metaandroidcapstone


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(context: MainActivity, navController: NavHostController) {

    var searchText by remember {
        mutableStateOf("")
    }

    var categorySelected by remember {
        mutableStateOf("All")
    }
    Column(modifier = Modifier
        .fillMaxSize()) {
        Header(navController)

        LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)){
            item {
                Intro(searchText,fun(value:String){
                    searchText = value
                })
                Category(categorySelected,fun(value:String){categorySelected=value})
                Divider(modifier = Modifier.padding(horizontal = 15.dp), thickness = 2.dp)
            }
            items(context.menuItems.value.filter {
                it.title.lowercase().contains(searchText.lowercase())
                        && ( categorySelected == "All" || it.category==categorySelected)
            }){
                MenuItem(it)
                Divider(modifier = Modifier.padding(15.dp))
            }
        }
    }
}

@Composable
fun Header(navController: NavHostController) {
    Row(modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxWidth()
        .height(70.dp)) {
        Spacer(modifier = Modifier.weight(1f))
        Image(modifier = Modifier
            .fillMaxWidth(0.6f)
            .fillMaxHeight(), painter = painterResource(id = R.drawable.logo), contentDescription = "logo")
        Image(modifier = Modifier
            .padding(5.dp)
            .clickable { navController.navigate(Profile.route) },
            painter = painterResource(id = R.drawable.profile), contentDescription = "Profile pic")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Intro(searchText: String, function: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.primary1))
            .padding(15.dp)) {
        Text(text = "Little Lemon", color = colorResource(id = R.color.primary2), fontSize = 35.sp, fontWeight = FontWeight.Medium)

        Row(
            Modifier
                .padding(vertical = 0.dp)
                .height(150.dp)
                .fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                Text(text = "Chicago", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Medium)
                Text(modifier = Modifier.padding(vertical = 12.dp), text = "We are a family owned Mediterranean restaurant founded on traditional recipes served with a modern twist",
                    color = Color.White, fontSize = 13.sp)
            }
            Image(modifier = Modifier
                .padding(10.dp, 0.dp, 0.dp, 10.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp)), painter = painterResource(id = R.drawable.home), contentDescription = "Intro Image", contentScale = ContentScale.Crop)

        }
        TextField(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
            value = searchText, onValueChange = { function(it) },
            colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.partialWhite),
                textColor = colorResource(id = R.color.primary1)),
        placeholder = {
            Row() {
                Icon(Icons.Rounded.Search,contentDescription = "")
                Text(modifier = Modifier.padding(horizontal = 10.dp), text = "Enter Search phrase")
            }
        })

    }
}

@Composable
fun Category(categorySelected: String, function: (String) -> Unit) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Text(text = "ORDER FOR DELIVERY!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        LazyRow(modifier = Modifier.padding(vertical = 10.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)){
            items(categoriesList){
                Text(modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .clickable { function(it) }
                    .background(colorResource(id = if(categorySelected == it)R.color.primary2 else R.color.partialWhite))
                    .padding(vertical = 6.dp, horizontal = 20.dp), text = it,fontSize = 14.sp, fontWeight = FontWeight.Medium, color = colorResource(id = R.color.primary1))
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemRoom) {

    Column(modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxWidth()) {
        Text(modifier = Modifier, text = menuItem.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.65f)) {
                Text(modifier = Modifier.padding(vertical = 10.dp), text = menuItem.description, fontSize = 14.sp, color = colorResource(
                    id = R.color.primary1
                ), maxLines = 2 ,overflow = TextOverflow.Ellipsis)
                Text(text = "$${menuItem.price.toDouble()}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colorResource(
                    id = R.color.primary1
                ))
            }

            GlideImage(modifier = Modifier.padding( 15.dp,0.dp,0.dp,0.dp), model = menuItem.image, contentDescription = "", contentScale = ContentScale.Crop)
        }
    }
}

val categoriesList = listOf("All",
    "Starters",
    "Mains",
    "Beverages",
    "Desserts",
    "Salads",
    "Appetizers")


//@Preview(showBackground = true)
//@Composable
//fun Preview(){
//    Home( )
//}