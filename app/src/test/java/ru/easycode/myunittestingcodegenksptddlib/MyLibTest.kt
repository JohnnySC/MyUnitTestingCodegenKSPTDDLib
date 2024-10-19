package ru.easycode.myunittestingcodegenksptddlib

import org.junit.Test

class MyLibTest {

    @Test(expected = IllegalStateException::class)
    fun `throw exception when generate from class`() {
        val myLib = MyLib()

        myLib.generate(clasz = LoginRepositoryImpl::class.java)
    }
}