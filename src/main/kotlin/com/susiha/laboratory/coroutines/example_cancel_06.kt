package com.susiha.laboratory.coroutines

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {

    try {
        withTimeout(1300L){
            repeat(1000){
                println("I'm sleeping $it....")
                delay(500L)
            }
        }
    }catch (ex:TimeoutCancellationException){
        println("I'm catch a Exception")

    }finally {
        println("I'm run finally")
    }


}
