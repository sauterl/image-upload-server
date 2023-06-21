package com.github.sauterl.plugins

import com.github.sauterl.directory
import com.github.sauterl.persistentDir
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.io.File

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {

        var fileDescription = ""
        var fileName = ""
        var path = ""
        var persistentPath = ""

        post("/upload"){
            val multipartData = call.receiveMultipart()

            multipartData.forEachPart {
                when(it){
                    is PartData.FormItem -> {
                        fileDescription = it.value
                    }
                    is PartData.FileItem -> {
                        fileName = it.originalFileName as String
                        val bytes = it.streamProvider().readBytes()
                        path = "${directory}/uploads/$fileName"
                        persistentPath = "${persistentDir}/i_$fileName"
                        val file = File(path)
                        val pFile = File(persistentPath)
                        file.mkdirs()
                        pFile.mkdirs()
                        file.writeBytes(bytes)
                        pFile.writeBytes(bytes)
                    }

                    else -> {}
                }
                it.dispose()
            }
            call.respondText(path)
        }
    }
}
