package com.susiha.laboratory.shura.okhttp.util

import okhttp3.internal.Util
import kotlin.test.Test

/**
 * Test OkHttp 的Utils的SkipLeadingAsciiWhitespace方法，该方法是从指定的索引开始算起，
 * 返回第一个非空格和转义字符的索引，就是说跳过头部的转义字符和空格
 */
class TestSkipLeadingAsciiWhitespace{
    @Test
    fun test01(){
        var input = "https://hello.com"
        var pos =  Util.skipLeadingAsciiWhitespace(input,0,input.length)
        println("pos = $pos")
    }


    @Test
    fun test02(){
        var input = "\t  https://hello\t.com   "
        var pos =  Util.skipLeadingAsciiWhitespace(input,0,input.length)
        println("pos = $pos")
    }




}