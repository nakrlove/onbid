package com.smtech.onbid.entity.wapper

import com.smtech.onbid.entity.Codes
import org.springframework.data.domain.Page

data class CodeWrapper(val count: Long, val codes: Page<Codes>) {
}