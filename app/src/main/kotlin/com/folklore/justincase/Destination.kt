package com.folklore.justincase

sealed class Destination(val route: String) {
    open fun createRoute(vararg args: String): String =
        if (args.isEmpty()) route else route + "/" + args.joinToString("/")

    object Home : Destination("home")
    object Note : Destination("note")
}
