package ru.easycode.myunittestingcodegenksptddlib

import java.lang.reflect.Method

class MyLib {

    fun generate(clasz: Class<*>): String {
        val interfaceName = clasz.simpleName
        if (!clasz.isInterface) {
            throw IllegalStateException("$interfaceName::class.java is not an interface!")
        }

        val stringBuilder = StringBuilder()

        stringBuilder.append(clasz.`package`)
        stringBuilder.append("\n\n")
        stringBuilder.append("import junit.framework.TestCase.assertEquals")
        stringBuilder.append("\n\n")
        stringBuilder.append("class Fake")
        stringBuilder.append(interfaceName)
        stringBuilder.append(" : ")
        stringBuilder.append(interfaceName)
        stringBuilder.append(" {\n")

        clasz.declaredMethods.forEach { method ->
            if (method.parameters.isEmpty()) {
                noArgsUnit(stringBuilder, method)
            } else {
                argsUnit(stringBuilder, method)
            }
        }

        stringBuilder.append("}")

        return stringBuilder.toString()
    }

    private fun argsUnit(stringBuilder: java.lang.StringBuilder, method: Method) {
        method.parameters.forEachIndexed { index,  parameter ->
            val parameterType = parameter.type.name
            stringBuilder.append("\n")
            val methodName = method.name
            val methodNamePascalCase = methodName.replaceFirstChar { it.uppercase() }
            stringBuilder.append("    private val ")
            val funNameCalledList = methodName + "CalledList"
            stringBuilder.append(funNameCalledList)
            stringBuilder.append(": MutableList<")
            stringBuilder.append(parameterType)
            stringBuilder.append("> = mutableListOf()\n\n")
            stringBuilder.append("    fun assert")
            stringBuilder.append(methodNamePascalCase)
            stringBuilder.append("CalledTimes(expected: Int) {\n")
            stringBuilder.append("        assertEquals(expected, ")
            stringBuilder.append(funNameCalledList)
            stringBuilder.append(".size)\n")
            stringBuilder.append("    }\n\n")
            stringBuilder.append("    fun assert")
            stringBuilder.append(methodNamePascalCase)
            stringBuilder.append("CalledWith(position: Int, expected: ")
            stringBuilder.append(parameterType)
            stringBuilder.append(") {\n")
            stringBuilder.append("        assertEquals(expected, ")
            stringBuilder.append(funNameCalledList)
            stringBuilder.append("[position])\n")
            stringBuilder.append("    }\n\n")
            stringBuilder.append("    override fun ")
            stringBuilder.append(methodName)
            stringBuilder.append("(arg")
            stringBuilder.append(index)
            stringBuilder.append(": ")
            stringBuilder.append(parameterType)
            stringBuilder.append(") {\n")
            stringBuilder.append("        ")
            stringBuilder.append(funNameCalledList)
            stringBuilder.append(".add(arg")
            stringBuilder.append(index)
            stringBuilder.append(")\n")
            stringBuilder.append("    }\n")
        }
    }

    private fun noArgsUnit(stringBuilder: java.lang.StringBuilder, method: Method) {
        stringBuilder.append("\n")
        stringBuilder.append("    private var ")
        val methodName = method.name
        stringBuilder.append(methodName)
        stringBuilder.append("CalledTimes: Int = 0\n\n")
        stringBuilder.append("    fun assert")
        val methodNamePascalCase = methodName.replaceFirstChar { it.uppercase() }
        stringBuilder.append(methodNamePascalCase)
        stringBuilder.append("CalledTimes(expected: Int) {\n")
        stringBuilder.append("        assertEquals(expected, ")
        stringBuilder.append(methodName)
        stringBuilder.append("CalledTimes)\n")
        stringBuilder.append("    }\n\n")

        stringBuilder.append("    override fun ")
        stringBuilder.append(methodName)
        stringBuilder.append("() {\n")
        stringBuilder.append("        ")
        stringBuilder.append(methodName)
        stringBuilder.append("CalledTimes++\n")
        stringBuilder.append("    }\n")
    }
}