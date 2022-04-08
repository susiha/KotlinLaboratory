package com.susiha.laboratory.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1;
    while(true){
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.launchProcessor(id:Int,channel:ReceiveChannel<Int>) = launch {
    for (msg in channel){
        println("Processor #$id received $msg")
    }
}



fun main() = runBlocking {

    var producer = produceNumbers()
    repeat(5){
        launchProcessor(it,producer)
    }
    delay(950)
    producer.cancel()
}