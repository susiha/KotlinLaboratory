package com.susiha.laboratory.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()  = runBlocking {

    val channel = Channel<Int>()


    /**
     * launch delay了10s之后,开始发送数据
     */
    launch {
        delay(10000L)
        println("in Launch Scope")
        for (i in 1..5){
            channel.send(i*i)
        }
    }
    //1⃣ 首先执行打印语句
    println("Hello")

    repeat(5){
        // 执行到第二个打印语句的时候 在 channel.receive()处进行等待
        // 等待信息的发送，如果一直没有信息 就一直等待，
        println("welcome $it")
        println(channel.receive())
    }

    println("Done")

}








