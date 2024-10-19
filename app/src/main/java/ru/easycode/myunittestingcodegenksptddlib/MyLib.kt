package ru.easycode.myunittestingcodegenksptddlib

class MyLib {

    fun generate(clasz: Class<*>) {
        if (!clasz.isInterface)
            throw IllegalStateException("${clasz.simpleName}::class.java is not an interface!")
    }
}