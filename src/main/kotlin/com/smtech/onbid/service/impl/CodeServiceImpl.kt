package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dto.AttachCodeDTO
import com.smtech.onbid.entity.AttachCodes
import com.smtech.onbid.handler.CodeDataHandler
import com.smtech.onbid.service.CodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CodeServiceImpl @Autowired constructor( val codeDataHandler: CodeDataHandler): CodeService {
    override fun generateNextCode(): String {
        return codeDataHandler.generateNextCode()
    }


    override fun saveCode(attach: AttachCodeDTO): AttachCodes {
        val data = AttachCodes(codename = attach.codename )
        codeDataHandler.saveCodeEntity(data)
        return data
    }

    override fun findCode(attach: AttachCodeDTO): AttachCodes {
        val data = AttachCodes(codename = attach.codename )
        codeDataHandler.findCodeEntity(data)
        return data
    }

    override fun findListsEntity(attach: AttachCodeDTO, page: PageRequest): Page<AttachCodes> {
        val data = AttachCodes(codename = attach.codename )
        return codeDataHandler.findListsEntity(data,page)
    }
}