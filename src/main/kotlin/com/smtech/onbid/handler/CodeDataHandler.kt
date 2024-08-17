package com.smtech.onbid.handler

import com.smtech.onbid.data.entity.Codes
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.*

interface CodeDataHandler {
//    fun generateNextCode(): String
    fun saveCodeEntity(attach: Codes): Optional<Codes>
    fun updateCodeEntity(attach: Codes): Codes?
    fun deleteCodeEntity(attach: Codes)
    fun findCodeEntity(attach: Codes): Optional<Codes>
    fun findListsEntity(attach: Codes, page: PageRequest): Page<Codes>
}