package com.susiha.laboratory.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking


fun CoroutineScope.numberFormat(start:Int):ReceiveChannel<Int> = produce {
    var x = start
    while (true){
        send(x++)
    }
}

fun CoroutineScope.filter(numbers:ReceiveChannel<Int>,prime:Int):ReceiveChannel<Int> = produce {
    for (x in numbers){
        if(x%prime ==0){
            send(x)
        }
    }
}

fun main() = runBlocking {

//    val numbers = numberFormat(0)
//    val filters = filter(numbers,3)
//
//    repeat(100){
//        println("3的倍数 = ${filters.receive()}")
//    }


    var cur = numberFormat(2)

    repeat(20){
        val prime = cur.receive()
        println("当前 prime = $prime")
        cur = filter(cur,prime)
    }
    println("Done")

    coroutineContext.cancelChildren()

}



