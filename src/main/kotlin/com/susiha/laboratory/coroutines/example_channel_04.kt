package com.susiha.laboratory.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun CoroutineScope.productNumbers() = produce<Int> {
    var x = 1
    while (true){
        delay(1000L)
        send(x++) // 发送无限的数据流 从1 开始
    }
}

fun CoroutineScope.square(numbers:ReceiveChannel<Int>):ReceiveChannel<Int> = produce {
    for(x in numbers){
        send(x*x)
    }
}

fun main() = runBlocking {

    val numbers = productNumbers()
    val squares = square(numbers)

    repeat(5){
        println(squares.receive())
    }

    println("Done")

    // cancel all children tro let main finish
    coroutineContext.cancelChildren()

}