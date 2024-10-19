package ru.easycode.myunittestingcodegenksptddlib

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MyLibTest {

    private lateinit var myLib : MyLib

    @Before
    fun setup() {
        myLib = MyLib()
    }

    @Test
    fun `one fun no arguments void type`() {
        val actual : String = myLib.generate(OneFunNoArgUnit::class.java)
        val expected : String = "" +
                "package ru.easycode.myunittestingcodegenksptddlib\n" +
                "\n" +
                "import junit.framework.TestCase.assertEquals\n" +
                "\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `throw exception when generate from class`() {
        try {
            myLib.generate(clasz = LoginRepositoryImpl::class.java)
        } catch (e: Exception) {
            assertEquals(IllegalStateException::class.java, e.javaClass)
            assertEquals("LoginRepositoryImpl::class.java is not an interface!", e.message)
        }
    }
}