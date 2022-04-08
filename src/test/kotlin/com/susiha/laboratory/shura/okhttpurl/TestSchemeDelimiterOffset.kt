package com.susiha.laboratory.shura.okhttpurl

import com.susiha.laboratory.shura.okhttpurl.TestSchemeDelimiterOffset.Company.schemeDelimiterOffset
import kotlin.test.Test

class TestSchemeDelimiterOffset {

    object Company {
        /**
         * 从okHttp HttpUrl 的copy过来的方法 在parseUrl的时候调用，用于返回:的索引，如果不存在 就返回-1
         */
        @JvmStatic
        fun schemeDelimiterOffset(input:String,pos:Int,limit:Int):Int{
            // 正确的scheme 应该是 A:B 至少应该保证limit-pos = 2
            if(limit-pos<2) return -1
            //获取第一个字符
            var c0 = input[pos]
            //如果第一个字符不是字母开头 返回-1
            if((c0<'a'||c0>'z')&&(c0<'A'||c0>'Z')) return -1
            for(i in pos+1 until limit){
                // 依次找到对应的字符
                val c = input[i]
                return if((c in 'a'..'z')
                    || (c in 'A'..'Z')
                    || (c in '0'..'9')
                    || c == '+'
                    || c == '-'
                    || c == '.'
                ){
                    continue
                }else if(c ==':'){
                    i
                }else {
                    -1
                }
            }
            return -1
        }
    }


    @Test
    fun test01(){
        val path = "Http:hello.com%20"
        val schemeDelimiterIndex = schemeDelimiterOffset(path,0,path.length)
        println("schemeDelimiterIndex = $schemeDelimiterIndex")
    }

    @Test
    fun test02(){
        val path = "1ttp:hello.com%20"
        val schemeDelimiterIndex = schemeDelimiterOffset(path,0,path.length)
        println("schemeDelimiterIndex = $schemeDelimiterIndex")
    }

    @Test
    fun test03(){
        val path = "ht+tp:hello.com%20"
        val schemeDelimiterIndex = schemeDelimiterOffset(path,0,path.length)
        println("schemeDelimiterIndex = $schemeDelimiterIndex")
    }


    @Test
    fun test04(){
        val path = "ht.+t-p:hello.com%20"
        val schemeDelimiterIndex = schemeDelimiterOffset(path,0,path.length)
        println("schemeDelimiterIndex = $schemeDelimiterIndex")
    }



}