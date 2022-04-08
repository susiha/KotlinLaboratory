package com.susiha.laboratory.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    var startTime = System.currentTimeMillis()
    var job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 1
        while(isActive){
            if(System.currentTimeMillis()>nextPrintTime){
                println("job:I'm sleeping ${i++}....")
                nextPrintTime+=500L
            }
        }
    }

    delay(1300L)
    println("main:I'm tired to waiting")
    job.cancelAndJoin()
    println("main:Now I can quit")

}