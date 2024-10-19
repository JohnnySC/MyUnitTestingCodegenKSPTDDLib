package ru.easycode.myunittestingcodegenksptddlib

import android.os.Build
import androidx.annotation.RequiresApi

class MyLib {

    @RequiresApi(Build.VERSION_CODES.S)
    fun generate(clasz: Class<*>) : String {
        if (!clasz.isInterface)
            throw IllegalStateException("${clasz.simpleName}::class.java is not an interface!")

        val stringBuilder = StringBuilder()

        stringBuilder.append(clasz.`package`)
        stringBuilder.append("\n\n")
        stringBuilder.append("import junit.framework.TestCase.assertEquals")
        stringBuilder.append("\n\n")

        return stringBuilder.toString()
    }
}