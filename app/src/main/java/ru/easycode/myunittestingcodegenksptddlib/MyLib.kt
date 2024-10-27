package ru.easycode.myunittestingcodegenksptddlib

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

        stringBuilder.append("}")

        return stringBuilder.toString()
    }
}