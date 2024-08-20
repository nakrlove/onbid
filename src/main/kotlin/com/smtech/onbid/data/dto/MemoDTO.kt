package com.smtech.onbid.data.dto

import java.time.LocalDateTime

data class MemoDTO(
    val idx: Int? = null,
    var memo_contents: String? = null,
    var regdate: LocalDateTime? = LocalDateTime.now(),
    val bididx: Int
)
