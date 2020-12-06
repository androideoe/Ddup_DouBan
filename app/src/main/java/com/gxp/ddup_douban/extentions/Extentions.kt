package com.gxp.ddup_douban.extentions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by ddup on 2020/11/14.
 * 扩展方法
 */

fun ViewModel.launch(
    block: suspend CoroutineScope.() -> Unit,
    onError: (e: Throwable) -> Unit = {},
    onComplete: () -> Unit = {}

) {
    viewModelScope.launch(CoroutineExceptionHandler { _, e -> onError(e) }) {
        try {
            block.invoke(this)
        } finally {
            onComplete()
        }

    }

}