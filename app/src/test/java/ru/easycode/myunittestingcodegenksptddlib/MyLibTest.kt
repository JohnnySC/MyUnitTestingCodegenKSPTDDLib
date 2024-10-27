package ru.easycode.myunittestingcodegenksptddlib

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.myunittestingcodegenksptddlib.arg.OneFunOneArgUnit
import ru.easycode.myunittestingcodegenksptddlib.arg.TwoFunsOneArgEach
import ru.easycode.myunittestingcodegenksptddlib.two.TwoFunsNoArgsUnit

class MyLibTest {

    private lateinit var myLib: MyLib

    @Before
    fun setup() {
        myLib = MyLib()
    }

    @Test
    fun `two funs one argument each void type`() {
        val actual = myLib.generate(TwoFunsOneArgEach::class.java)
        val expected = "" +
                "package ru.easycode.myunittestingcodegenksptddlib.arg\n" +
                "\n" +
                "import junit.framework.TestCase.assertEquals\n" +
                "\n" +
                "class FakeTwoFunsOneArgEach : TwoFunsOneArgEach {\n" +
                "\n" +
                "    private val showCalledList: MutableList<java.lang.Integer> = mutableListOf()\n" +
                "\n" +
                "    fun assertShowCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, showCalledList.size)\n" +
                "    }\n" +
                "\n" +
                "    fun assertShowCalledWith(position: Int, expected: java.lang.Integer) {\n" +
                "        assertEquals(expected, showCalledList[position])\n" +
                "    }\n" +
                "\n" +
                "    override fun show(arg0: java.lang.Integer) {\n" +
                "        showCalledList.add(arg0)\n" +
                "    }\n" +
                "\n" +
                "    private val animateCalledList: MutableList<ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration> = mutableListOf()\n" +
                "\n" +
                "    fun assertAnimateCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, animateCalledList.size)\n" +
                "    }\n" +
                "\n" +
                "    fun assertAnimateCalledWith(position: Int, expected: ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration) {\n" +
                "        assertEquals(expected, animateCalledList[position])\n" +
                "    }\n" +
                "\n" +
                "    override fun run(arg0: ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration) {\n" +
                "        animateCalledList.add(arg0)\n" +
                "    }\n" +
                "}"
        assertEquals(expected, actual)
    }

    @Test
    fun `one fun one argument void type`() {
        val actual = myLib.generate(OneFunOneArgUnit::class.java)
        val expected = "" +
                "package ru.easycode.myunittestingcodegenksptddlib.arg\n" +
                "\n" +
                "import junit.framework.TestCase.assertEquals\n" +
                "\n" +
                "class FakeOneFunOneArgUnit : OneFunOneArgUnit {\n" +
                "\n" +
                "    private val runCalledList: MutableList<java.lang.String> = mutableListOf()\n" +
                "\n" +
                "    fun assertRunCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, runCalledList.size)\n" +
                "    }\n" +
                "\n" +
                "    fun assertRunCalledWith(position: Int, expected: java.lang.String) {\n" +
                "        assertEquals(expected, runCalledList[position])\n" +
                "    }\n" +
                "\n" +
                "    override fun run(arg0: java.lang.String) {\n" +//todo what is the name of arg?
                "        runCalledList.add(arg0)\n" +
                "    }\n" +
                "}"
        assertEquals(expected, actual)
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