package com.susiha.laboratory.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {

  var result =   withTimeoutOrNull(1300L){
      repeat(1000){
          println("I'm sleeping $it...in $this")
          delay(500L)
      }
      "Finished,the result type is Any,if TimeOut return null"
    }

    println("result = $result")

}