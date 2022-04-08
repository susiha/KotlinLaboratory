package com.susiha.laboratory.coroutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    var job = launch {
        try {
            repeat(1000){
                println("job:I'm sleeping $it....")
                delay(500L)
            }
        }finally {
            println("job:I'm running finally")

        }
    }
    delay(1300L)
    println("main:I'm tired to waiting")
    //job.cancel() 会执行finally中的语句,但是不能保证finally中的语句先于后面的语句执行
    //job.cancelAndJoin 同样也会执行finally中的语句，同时也能保证finally中的语句一定先于后面的语句的执行
    job.cancelAndJoin()
    println("main:Now I can quit")
}