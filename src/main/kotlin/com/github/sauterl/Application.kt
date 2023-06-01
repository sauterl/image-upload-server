package com.github.sauterl

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.github.sauterl.plugins.*


lateinit var directory : String
    internal set

fun main(args: Array<String>) {
    if(args.isNotEmpty()){
        directory = args[0]
    }
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureHTTP()
    configureRouting()
}
