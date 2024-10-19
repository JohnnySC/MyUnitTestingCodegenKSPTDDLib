package ru.easycode.myunittestingcodegenksptddlib

class MyLib {

    fun generate(clasz: Class<*>) : String {
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
        stringBuilder.append(" {\n\n")

        return stringBuilder.toString()
    }
}