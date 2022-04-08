package com.susiha.laboratory.shura.okhttp.util

import okhttp3.internal.Util
import kotlin.test.Test
/**
 * Test OkHttp 的Utils的skipTrailingAsciiWhitespace方法，该方法是尾部开始计算，直到指定的位置位置，
 * 返回第一个非空格和转义字符的索引，也就是说跳过尾部的转移字符和空格
 */
class TestSkipTrailingAsciiWhitespace {
    @Test
    fun test01(){
        var input = "https://hello.com"
        var limit =  Util.skipTrailingAsciiWhitespace(input,0,input.length)
        println("limit = $limit")
    }


    @Test
    fun test02(){
        var input = "https://hello.com     \t"
        var limit =  Util.skipTrailingAsciiWhitespace(input,0,input.length)
        println("limit = $limit")
    }




}