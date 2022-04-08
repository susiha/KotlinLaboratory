package com.susiha.laboratory.shura.string

import kotlin.test.Test

class TestAsciiChar {
    @Test
    fun test01(){
        val c0 = 'a'
        val c1 = 'z'
        val c2 = 'A'
        val c3 = 'Z'
        print("a = ${c0.toInt()}")
        print("z = ${c1.toInt()}")
        print("A = ${c2.toInt()}")
        print("Z = ${c3.toInt()}")


    }
}