package com.susiha.laboratory.shura.string
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * 通过测试String的regionMatches方法，深入学习和理解RegionMatches的应用和原理
 * 这个方法是通过与指定的字符串的局部对比来确定是否指定的局部内容是否一致，可忽略大小写
 * 如: Hello与welcome 的第五个字符是一样的
 * 使用场景:okHttp的Request添加Url时候 判断是否以ws:开头
 */
class TestRegionMatches {

    @Test
    fun test01(){
        println("this is first test")
    }

    @Test
    fun testRegionMatches(){

        assertTrue("断言失败!"){
            "Hello".regionMatches(4,"welcOme",4,1,true)
        }
        assertFalse {
            "Hello".regionMatches(0,"welcome",0,3,false)
        }
        assertFalse(){
            "Hello".regionMatches(4,"welcOme",4,1,false)
        }

        assertTrue(){
            "sunshine".regionMatches(3,"shura",0,2,false)
        }
    }

}