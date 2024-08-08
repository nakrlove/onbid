package com.smtech.onbid.service

import com.smtech.onbid.data.dto.AttachCodeDTO
import com.smtech.onbid.entity.AttachCodes
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface CodeService {
    fun generateNextCode(): String
    fun saveCode(attach: AttachCodeDTO): AttachCodes
    fun findCode(attach: AttachCodeDTO): AttachCodes
    fun findListsEntity(attach: AttachCodeDTO, page: PageRequest): Page<AttachCodes>
}