package com.example.metaandroidcapstone

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(context: MainActivity,navController: NavHostController){
    var firstName by remember {
      mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp)) {

        Image(modifier = Modifier
            .fillMaxWidth(1f)
            .height(50.dp), painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")

        Text(modifier = Modifier
            .padding(vertical = 15.dp)
            .background(colorResource(id = R.color.primary1))
            .fillMaxWidth()
            .padding(vertical = 50.dp), textAlign = TextAlign.Center, fontSize = 25.sp, color = Color.White, text = "Let's get to know you")

        Text(modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp), fontSize = 22.sp, fontWeight = FontWeight.Bold, text = "Personal information")

        OutlinedTextField(modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .fillMaxWidth(), value = firstName, onValueChange = {firstName = it}, label = { Text(text = "First Name")})

        OutlinedTextField(modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .fillMaxWidth(), value = lastName, onValueChange = {lastName = it}, label = { Text(text = "Last Name")})

        OutlinedTextField(modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .fillMaxWidth(), value = email, onValueChange = {email = it}, label = { Text(text = "Email Address")})

        Spacer(modifier = Modifier.weight(1f))

        Button(modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.primary1),
                shape = RoundedCornerShape(12.dp)
            ),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary2)),

            shape = RoundedCornerShape(12.dp), onClick = {
                if(firstName.isBlank() || lastName.isBlank() || email.isBlank()){
                    Toast.makeText(context,"Registration unsuccessful. Please enter all data",Toast.LENGTH_SHORT).show()
                }else{
                    context.storeUserDetails(firstName,lastName,email)
                    navController.navigate(Home.route)
                }

            }) {
            Text( text = "Register", fontSize = 20.sp, color = colorResource(
                id = R.color.primary1
            ))
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun Preview(){
//    Onboarding()
//}