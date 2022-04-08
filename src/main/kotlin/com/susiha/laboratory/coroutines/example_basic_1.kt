package com.susiha.laboratory.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    repeat(100_000){
        //同时创建了10万个协程，每一个都delay了5秒钟，几乎是同时delay
        launch {
            delay(5000L)
            print(".")
        }
    }
}
