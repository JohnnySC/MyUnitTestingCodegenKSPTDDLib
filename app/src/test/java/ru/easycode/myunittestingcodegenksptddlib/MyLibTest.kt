package ru.easycode.myunittestingcodegenksptddlib

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.myunittestingcodegenksptddlib.arg.OneFunOneArgUnit
import ru.easycode.myunittestingcodegenksptddlib.arg.OneFunTwoArgsUnit
import ru.easycode.myunittestingcodegenksptddlib.arg.TwoFunsOneArgEach
import ru.easycode.myunittestingcodegenksptddlib.two.TwoFunsNoArgsUnit

class MyLibTest {

    private lateinit var myLib: MyLib

    @Before
    fun setup() {
        myLib = MyLib()
    }

    @Test
    fun `one fun two args void type`() {
        val actual = myLib.generate(OneFunTwoArgsUnit::class.java)
        val excepted = "" +
                "package ru.easycode.myunittestingcodegenksptddlib.arg\n" +
                "\n" +
                "import junit.framework.TestCase.assertEquals\n" +
                "\n" +
                "class FakeOneFunTwoArgsUnit : OneFunTwoArgsUnit {\n" +
                "\n" +
                "    private val logArg0CalledList: MutableList<java.lang.String> = mutableListOf()\n" +
                "    private val logArg1CalledList: MutableList<ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration> = mutableListOf()\n" +
                "\n" +
                "    fun assertLogCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, logArg0CalledList.size)\n" +
                "    }\n" +
                "\n" +
                "    fun assertLogCalledWith(position: Int, expectedArg0: java.lang.String, expectedArg1: ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration) {\n" +
                "        assertEquals(expectedArg0, logArg0CalledList[position])\n" +
                "        assertEquals(expectedArg1, logArg1CalledList[position])\n" +
                "    }\n" +
                "\n" +
                "    override fun log(arg0: java.lang.String, arg1: ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration) {\n" +
                "        logArg0CalledList.add(arg0)\n" +
                "        logArg1CalledList.add(arg1)\n" +
                "    }\n" +
                "}"

        assertEquals(excepted, actual)
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
                "    private val animateArg0CalledList: MutableList<ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration> = mutableListOf()\n" +
                "\n" +
                "    fun assertAnimateCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, animateArg0CalledList.size)\n" +
                "    }\n" +
                "\n" +
                "    fun assertAnimateCalledWith(position: Int, expectedArg0: ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration) {\n" +
                "        assertEquals(expectedArg0, animateArg0CalledList[position])\n" +
                "    }\n" +
                "\n" +
                "    override fun animate(arg0: ru.easycode.myunittestingcodegenksptddlib.data.CustomDuration) {\n" +
                "        animateArg0CalledList.add(arg0)\n" +
                "    }\n" +
                "\n" +
                "    private val showArg0CalledList: MutableList<java.lang.Integer> = mutableListOf()\n" +
                "\n" +
                "    fun assertShowCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, showArg0CalledList.size)\n" +
                "    }\n" +
                "\n" +
                "    fun assertShowCalledWith(position: Int, expectedArg0: java.lang.Integer) {\n" +
                "        assertEquals(expectedArg0, showArg0CalledList[position])\n" +
                "    }\n" +
                "\n" +
                "    override fun show(arg0: java.lang.Integer) {\n" +
                "        showArg0CalledList.add(arg0)\n" +
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
                "    private val runArg0CalledList: MutableList<java.lang.String> = mutableListOf()\n" +
                "\n" +
                "    fun assertRunCalledTimes(expected: Int) {\n" +
                "        assertEquals(expected, runArg0CalledList.size)\n" +
                "    }\n" +
                "\n" +
                "    fun assertRunCalledWith(position: Int, expectedArg0: java.lang.String) {\n" +
                "        assertEquals(expectedArg0, runArg0CalledList[position])\n" +
                "    }\n" +
                "\n" +
                "    override fun run(arg0: java.lang.String) {\n" +//todo what is the name of arg?
                "        runArg0CalledList.add(arg0)\n" +
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