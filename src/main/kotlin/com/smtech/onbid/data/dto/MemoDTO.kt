package com.smtech.onbid.data.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class MemoDTO(
    val idx: Int? = null,
    var memo_contents: String? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    var regdate: LocalDateTime? = null,
    var regdate: LocalDateTime? = LocalDateTime.now(),
//    var regdate: String? = null,
    val bididx: Int
)
