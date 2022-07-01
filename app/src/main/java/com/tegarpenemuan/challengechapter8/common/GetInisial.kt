package com.tegarpenemuan.challengechapter8.common

/**
 * com.tegarpenemuan.challengchapter6.common
 *
 * Created by Tegar Penemuan on 31/05/2022.
 * https://github.com/tegarpenemuanr3
 *
 */

object GetInisial {
    fun String?.getInitial(): String {
        if (this.isNullOrEmpty()) return ""
        val array = this.split(" ")
        if (array.isEmpty()) return this
        var inisial = array[0].substring(0, 1)
        if (array.size > 1) inisial += array[1].substring(0, 1)
        return inisial.uppercase()
    }
}