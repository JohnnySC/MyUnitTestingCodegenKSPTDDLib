package ru.easycode.myunittestingcodegenksptddlib

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MyLibTest {

    @Test
    fun `throw exception when generate from class`() {
        val myLib = MyLib()

        try {
            myLib.generate(clasz = LoginRepositoryImpl::class.java)
        } catch (e: Exception) {
            assertEquals(IllegalStateException::class.java, e.javaClass)
            assertEquals("LoginRepositoryImpl::class.java is not an interface!", e.message)
        }
    }
}