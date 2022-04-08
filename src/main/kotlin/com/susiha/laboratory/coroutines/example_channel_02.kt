package com.susiha.laboratory.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()  = runBlocking {
    val channel  = Channel<Int>()
    launch {
        for(i in 1..5){
            channel.send(i*i)
        }
        channel.close()
    }
    for(y in channel){
        println("this is $y")
    }
//    repeat(5){
//        println("received = ${channel.receive()}")
//    }

    println("Done")

}