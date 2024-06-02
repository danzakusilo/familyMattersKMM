package com.danya.app.util

// take string, if it's a float, trip decimals if they are not 0
fun String.trimDecimals(): String {
    return if (this.contains(".")) {
        val trimmed = this.trimEnd('0')
        if (trimmed.endsWith('.')) {
            trimmed.dropLast(1)
        } else {
            trimmed
        }
    } else {
        this
    }
}
