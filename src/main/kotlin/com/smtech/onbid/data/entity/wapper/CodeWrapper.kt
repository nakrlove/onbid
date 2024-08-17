package com.smtech.onbid.data.entity.wapper

import com.smtech.onbid.data.entity.Codes
import org.springframework.data.domain.Page

data class CodeWrapper(val count: Long, val codes: Page<Codes>) {
}