package com.fire.myreivces.http

import com.fire.myreivces.http.BaseBean.Code.success
import com.fire.myreivces.http.BaseBean.Code.unknown

/**
 * 描述	      ${basebean,用于接收数据的基本bean}
 * 更新者     $Author$
 * 更新时间   $Date$
 */
class BaseBean<T : Any> {

    var code: Int = unknown
    var message: String = ""
    lateinit var data: T
    var userId: Int = 0
    var token: String = ""

    fun isSuccess(): Boolean {
        return success == code
    }

    object Code {
        const val unknown = -1
        const val success = 0

    }
}
