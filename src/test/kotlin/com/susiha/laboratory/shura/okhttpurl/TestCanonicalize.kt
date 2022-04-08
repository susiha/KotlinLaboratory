package com.susiha.laboratory.shura.okhttpurl
import com.susiha.laboratory.shura.okhttpurl.TestCanonicalize.Company.canonicalize
import okhttp3.internal.Util
import okio.Buffer
import java.nio.charset.Charset
import kotlin.test.Test

class TestCanonicalize {
    object Company{

       private val HEX_DIGITS = charArrayOf('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F')

        /**
         * 规范化,在ASCII中可显示的字符 从0x20(空格)到0x7E(~)
         */
        @JvmStatic
        fun canonicalize(input:String,pos:Int,limit:Int,encodeSet:String,
                         alreadyEncoded:Boolean = true,strict:Boolean = false,plusIsSpace:Boolean = false,
                         asciiOnly:Boolean = true,charset: Charset? = null):String{
            var codePoint = 0
            for(i in pos until limit step Character.charCount(codePoint)){
                codePoint = input.codePointAt(i)
                if(codePoint < 0x20|| // 小于可显示字符范围
                            codePoint == 0x7f|| // 不可显示字符
                            codePoint >= 0x80 &&asciiOnly || // 声明为ascii字符大于可显示范围
                            encodeSet.indexOf(codePoint.toChar()) != -1|| // 当前字符不在encodeSet里面
                            // 当前字符是%，但是没有进行过编码
                            codePoint.toChar() == '%' && (!alreadyEncoded || strict ||!percentEncoded(input,i,limit))||
                            // 当前字符是 + 并且plusIsSpace为true
                            codePoint.toChar() =='+' && plusIsSpace){
                    // 在i位置上的字符需要进行编码
                    var out = Buffer()
                    // 当定位到i位置的字符的时候
                    out.writeUtf8(input,pos,i)
                    canonicalize(out, input, i, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly, charset)
                    return out.readUtf8()
                }
            }
            // 在pos和limit之间的字符都不需要编码
            return input.substring(pos,limit)
        }


        fun canonicalize(out:Buffer,input: String,pos: Int,
                         limit: Int,encodeSet: String,alreadyEncoded: Boolean,
                         strict: Boolean,plusIsSpace: Boolean,asciiOnly: Boolean,charset: Charset?){
            var encodedCharBuffer:Buffer? = null
            var codePoint = 0
            for(i in pos until limit step Character.charCount(codePoint)){
                codePoint = input.codePointAt(i)
                if(alreadyEncoded && (codePoint.toChar() == '\t' || // 水平制表 tab
                            codePoint.toChar() == '\n' || // 换行
                            codePoint.toChar() == '\r')){ // 回车
                    // 跳过 这个字符
                }else if(codePoint.toChar() == '+' && plusIsSpace){ // 如果当前字符是'+' 并且这个'+'是空格编码的
                    // 编码'+'为'%2B'因为我们允许空格' ' 编码为'+'或者是'%20'
                    if(alreadyEncoded){
                        out.writeUtf8("+") // 如果已经编码了还是+
                    }else{
                        out.writeUtf8("%2B") // 如果没有编码 转变为%2B
                    }
                }else if(codePoint < 0x20
                       || codePoint == 0x7f
                       || codePoint >= 0x80 && asciiOnly
                       || encodeSet.indexOf(codePoint.toChar()) != -1
                       || codePoint.toChar() == '%' && (!alreadyEncoded || strict && !percentEncoded(input, i, limit))){

                    if(encodedCharBuffer == null){
                        encodedCharBuffer = Buffer()
                    }

                    if(charset == null|| charset == Util.UTF_8){
                        encodedCharBuffer.writeUtf8CodePoint(codePoint)
                    }else{
                        encodedCharBuffer.writeString(input,i,i+Character.charCount(codePoint),charset)
                    }
                    while(!encodedCharBuffer.exhausted()){
                        var b = encodedCharBuffer.readByte().toInt() and 0xff
                        out.writeByte('%'.toInt())
                        out.writeByte(HEX_DIGITS[((b shr 4) and 0xf)].toInt())
                        out.writeByte(HEX_DIGITS[b and 0x0f].toInt())
                    }
                }else{
                    // 不需要编码 直接copy 过来
                    out.writeUtf8CodePoint(codePoint)
                }
            }
        }

        /**
         * 是否进行了百分号(%)编码 ，进行了百分号编码的条件必须满足以下全部条件
         * 1 必须包含%
         * 2 %后面至少还包含两个字符
         * 3 %后面的两个字符必须是0～9 或者 a~f或者A~F
         */
        private fun percentEncoded(encoded:String,pos: Int,limit: Int):Boolean{
            return pos+2<limit &&
                    encoded[pos] == '%' &&
                    Util.decodeHexDigit(encoded[pos+1])!=-1 &&
                    Util.decodeHexDigit(encoded[pos+2])!=-1
        }


    }
    @Test
    fun testUserNameCanonical(){

        //http://username:password@host:8080/directory/file?query#ref:
        val input = "twitter.com/search?q=cute%20%23puppies&f=images"
        val canonicalUsername = canonicalize(input,0,input.length," \"':;<=>@[]^`{}|/\\?#")
        println("canonicalUsername = $canonicalUsername")
    }



}