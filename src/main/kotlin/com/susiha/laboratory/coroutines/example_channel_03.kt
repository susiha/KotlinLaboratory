package com.susiha.laboratory.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking


fun CoroutineScope.productSquares():ReceiveChannel<Int> = produce {
    for (i in 1..5){
        send(i*i)
    }
}
fun main()  = runBlocking {
    var squares = productSquares()

    squares.consumeEach {
        println("平方 = $it")
    }
    println("Done")


}