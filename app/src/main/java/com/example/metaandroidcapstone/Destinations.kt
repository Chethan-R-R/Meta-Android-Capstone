package com.example.metaandroidcapstone

interface Destinations{
    val route:String
}

object Onboard:Destinations{
    override val route = "Onboard"
}

object Home:Destinations{
    override val route = "Home"
}

object Profile:Destinations{
    override val route = "Profile"
}