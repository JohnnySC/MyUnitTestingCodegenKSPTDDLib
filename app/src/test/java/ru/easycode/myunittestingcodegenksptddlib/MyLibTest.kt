package ru.easycode.myunittestingcodegenksptddlib

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.myunittestingcodegenksptddlib.two.TwoFunsNoArgsUnit

class MyLibTest {

    private lateinit var myLib: MyLib

    @Before
    fun setup() {
        myLib = MyLib()
    }

    @Test
    fun `two funs no arguments void type`() {
        val actual: String = myLib.generate(TwoFunsNoArgsUnit::class.java)
        val expected = "" +
                "package ru.easycode.myunittestingcodegenksptddlib.two\n" +
                "\n" +
                "import junit.framework.TestCase.assertEquals\n" +
                "\n" +
                "class FakeTwoFunsNoArgsUnit : TwoFunsNoArgsUnit {\n" +
                "\n" +
                "    private var logCalledTimes: Int = 0\n" +
                "\n" +
                "    fun assertLogCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, logCalledTimes)\n" +
                "    }\n" +
                "\n" +
                "    override fun log() {\n" +
                "        logCalledTimes++\n" +
                "    }\n" +
                "\n" +
                "    private var printCalledTimes: Int = 0\n" +
                "\n" +
                "    fun assertPrintCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, printCalledTimes)\n" +
                "    }\n" +
                "\n" +
                "    override fun print() {\n" +
                "        printCalledTimes++\n" +
                "    }\n" +
                "}"

                assertEquals(expected, actual)
    }

    @Test
    fun `one fun no arguments void type`() {
        val actual: String = myLib.generate(OneFunNoArgUnit::class.java)
        val expected: String = "" +
                "package ru.easycode.myunittestingcodegenksptddlib\n" +
                "\n" +
                "import junit.framework.TestCase.assertEquals\n" +
                "\n" +
                "class FakeOneFunNoArgUnit : OneFunNoArgUnit {\n" +
                "\n" +
                "    private var showCalledTimes: Int = 0\n" +
                "\n" +
                "    fun assertShowCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, showCalledTimes)\n" +
                "    }\n" +
                "\n" +
                "    override fun show() {\n" +
                "        showCalledTimes++\n" +
                "    }\n" +
                "}"

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