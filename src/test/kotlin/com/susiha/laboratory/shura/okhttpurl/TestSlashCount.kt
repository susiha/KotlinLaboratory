package com.susiha.laboratory.shura.okhttpurl

import com.susiha.laboratory.shura.okhttpurl.TestSlashCount.Company.slashCount
import org.junit.Test

class TestSlashCount {

    object Company{

        /** Returns the number of '/' and '\' slashes in {@code input}, starting at {@code pos}. */
        @JvmStatic
        fun slashCount(input:String,pos:Int,limit:Int):Int{
            var slashCount = 0
            var mPos = pos
            while (mPos<limit){
                var c = input[mPos]
                if(c =='\\'||c == '/'){
                    slashCount++
                    mPos++
                }else{
                    break
                }
            }
            return slashCount;
        }
    }


    @Test
    fun test01(){
        val input = "Http://1000029/1020/2330"
        val slashCount = slashCount(input,5,input.length)
        println("slashCount = $slashCount")
    }


    @Test
    fun test02(){
        val input = "Http:\\\\\\1000029/1020/2330"
        val slashCount = slashCount(input,5,input.length)
        println("slashCount = $slashCount")
    }


}