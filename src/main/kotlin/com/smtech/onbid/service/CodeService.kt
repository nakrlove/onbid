package com.smtech.onbid.service

import com.smtech.onbid.data.dto.CodeDTO
import com.smtech.onbid.entity.Codes
import com.smtech.onbid.entity.wapper.CodeWrapper
import org.springframework.data.domain.PageRequest

interface CodeService {
//    fun generateNextCode(): String
    fun saveCode(attach: CodeDTO): Codes
    fun updateCode(attach: CodeDTO): Codes?
    fun deleteCode(attach: CodeDTO): Codes
    fun findCode(attach: CodeDTO): Codes
    fun findCodeQuery(): List<Codes>
    fun findMastCode(subcode: String): List<Codes>
    fun findListsEntity(attach: CodeDTO, page: PageRequest): CodeWrapper
}