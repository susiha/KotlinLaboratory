package com.susiha.laboratory.coroutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()  = runBlocking {

    //job 协程会执行 执行的代码块是 重复1000次 每次delay 0.5s
    var job = launch {
        repeat(1000){
            println("job: i'm sleeping $it ")
            delay(500L)
        }
    }
    //当前协程是BlockingCoroutine的对象
    println("当前的协程:$this")
    // delay 当前协程1.3s
    delay(1300L)
    println("main:I'm tired to waiting")
    //job 协程取消,job 协程内部代码块将不会执行
    job.cancel()
    // 如果执行了 cancel ,join 将不会起作用，如果未执行cancel,join方法将会将job 协程执行完成之后 才会执行后面的代码
//    job.join()
    println("main:now I can quit")




}