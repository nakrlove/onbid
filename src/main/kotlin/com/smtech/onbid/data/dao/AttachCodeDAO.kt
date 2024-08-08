package com.smtech.onbid.data.dao

import com.smtech.onbid.entity.AttachCodes
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.*


interface AttachCodeDAO {

    fun generateNextCode(): String
    fun saveCode(attach: AttachCodes): Optional<AttachCodes>
    fun findCode(attach: AttachCodes): Optional<AttachCodes>
    fun findListsEntity(attach: AttachCodes,page: PageRequest): Page<AttachCodes>
}