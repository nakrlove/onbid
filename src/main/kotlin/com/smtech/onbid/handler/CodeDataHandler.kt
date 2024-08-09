package com.smtech.onbid.handler

import com.smtech.onbid.data.dto.AttachCodeDTO
import com.smtech.onbid.entity.AttachCodes
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.*

interface CodeDataHandler {
//    fun generateNextCode(): String
    fun saveCodeEntity(attach: AttachCodes): Optional<AttachCodes>
    fun updateCodeEntity(attach: AttachCodes): AttachCodes?
    fun deleteCodeEntity(attach: AttachCodes)
    fun findCodeEntity(attach: AttachCodes): Optional<AttachCodes>
    fun findListsEntity(attach: AttachCodes, page: PageRequest): Page<AttachCodes>
}