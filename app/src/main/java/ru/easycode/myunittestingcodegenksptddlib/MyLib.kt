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

        clasz.declaredMethods.sortedBy { it.name }.forEach { method ->
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
        val methodName = method.name
        val methodNamePascalCase = methodName.replaceFirstChar { it.uppercase() }
        stringBuilder.append("\n")
        method.parameters.forEachIndexed { index, parameter ->
            val parameterType = parameter.type.name
            stringBuilder.append("    private val ")
            val funNameCalledList = methodName + "Arg${index}CalledList"
            stringBuilder.append(funNameCalledList)
            stringBuilder.append(": MutableList<")
            stringBuilder.append(parameterType)
            stringBuilder.append("> = mutableListOf()\n")
        }
        stringBuilder.append("\n")

        stringBuilder.append("    fun assert")
        stringBuilder.append(methodNamePascalCase)
        stringBuilder.append("CalledTimes(expected: Int) {\n")
        stringBuilder.append("        assertEquals(expected, ")
        stringBuilder.append(methodName)
        stringBuilder.append("Arg0CalledList")
        stringBuilder.append(".size)\n")
        stringBuilder.append("    }\n\n")

        stringBuilder.append("    fun assert")
        stringBuilder.append(methodNamePascalCase)
        stringBuilder.append("CalledWith(position: Int")

        method.parameters.forEachIndexed { index, parameter ->
            stringBuilder.append(", expectedArg$index: ")
            stringBuilder.append(parameter.type.name)
        }

        stringBuilder.append(") {\n")

        method.parameters.forEachIndexed { index, parameter ->
            stringBuilder.append("        assertEquals(expectedArg$index, ")
            stringBuilder.append(methodName)
            stringBuilder.append("Arg${index}CalledList")
            stringBuilder.append("[position])\n")
        }
        stringBuilder.append("    }\n\n")
        stringBuilder.append("    override fun ")
        stringBuilder.append(methodName)
        stringBuilder.append("(")
        method.parameters.forEachIndexed { index, parameter ->
            stringBuilder.append("arg")
            stringBuilder.append(index)
            stringBuilder.append(": ")
            stringBuilder.append(parameter.type.name)
            if (index != method.parameters.size - 1)
                stringBuilder.append(", ")
        }
        stringBuilder.append(") {\n")
        method.parameters.forEachIndexed { index, parameter ->
            stringBuilder.append("        ")
            stringBuilder.append(method.name)
            stringBuilder.append("Arg")
            stringBuilder.append(index)
            stringBuilder.append("CalledList")
            stringBuilder.append(".add(arg")
            stringBuilder.append(index)
            stringBuilder.append(")\n")
        }
        stringBuilder.append("    }\n")
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