package com.smtech.onbid.data.dao

import com.smtech.onbid.entity.Codes
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.*


interface CodeDAO {
    fun generateNextCode(): String
    fun saveCode(attach: Codes): Optional<Codes>
    fun updateCode(attach: Codes): Codes?
    fun deleteCode(attach: Codes)
    fun findCodeQuery(): List<Codes>
    fun findCode(attach: Codes): Optional<Codes>
    fun findListsEntity(attach: Codes, page: PageRequest): Page<Codes>
}