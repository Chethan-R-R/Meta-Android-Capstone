package com.example.metaandroidcapstone

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun Profile(context: MainActivity,navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)) {
        Image(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),painter = painterResource(id = R.drawable.logo), contentDescription = "logo" )
        Spacer(modifier = Modifier.weight(1f))
        Text(modifier = Modifier.padding( vertical = 10.dp), text = "Profile Information", fontSize = 25.sp)

        LabelText(label = "First Name", text = context.firstName.value)
        LabelText(label = "Last Name", text = context.lastName.value)
        LabelText(label = "Email", text = context.email.value)

        Spacer(modifier = Modifier.weight(1f))

        Button(modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.primary1),
                shape = RoundedCornerShape(12.dp)
            ),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary2)),

            shape = RoundedCornerShape(12.dp), onClick = {
                context.deleteUserDetails()
                navController.navigate(Onboard.route)
            }) {
            Text( text = "Log Out", fontSize = 20.sp, color = colorResource(
                id = R.color.primary1
            ))
        }
    }
}

@Composable
fun LabelText(label:String,text:String){
    Text(text = label)
    Text(modifier = Modifier
        .padding(vertical = 10.dp)
        .border(
            width = 2.dp,
            color = colorResource(id = R.color.primary1),
            shape = RoundedCornerShape(8.dp)
        )
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 10.dp), text = text, fontSize = 18.sp)
}

//@Preview(showBackground = true)
//@Composable
//fun Preview(){
//    Profile()
//}