package com.susiha.laboratory.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    var job = launch {
        try {
            repeat(1000){
                println("job:I'm sleeping $it....")
                delay(500L)
            }
        }finally {
            withContext(NonCancellable){
                println("job:I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")

            }
        }
    }
    delay(1300L)
    println("main:I'm tired to waiting")
    job.cancelAndJoin()
    println("main:Now I can quit")
}