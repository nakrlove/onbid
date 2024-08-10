package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dto.AttachCodeDTO
import com.smtech.onbid.data.repository.AttachCodeRepository
import com.smtech.onbid.entity.AttachCodes
import com.smtech.onbid.handler.CodeDataHandler
import com.smtech.onbid.service.CodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class CodeServiceImpl @Autowired constructor( val codeDataHandler: CodeDataHandler, @Autowired val attachCodeRepository: AttachCodeRepository): CodeService {
//    override fun generateNextCode(): String {
//        return codeDataHandler.generateNextCode()
//    }


    override fun saveCode(attach: AttachCodeDTO): AttachCodes {

        val data = AttachCodes(codename = attach.codename, subcode = attach.subcode)
        codeDataHandler.saveCodeEntity(data)
        return data
    }

    override fun updateCode(attach: AttachCodeDTO): AttachCodes? {
        // 1. ID로 엔티티 조회
        val data = AttachCodes(idx = attach.idx, codename = attach.codename )
        return codeDataHandler.updateCodeEntity(data)
    }

    override fun deleteCode(attach: AttachCodeDTO): AttachCodes {
        val data = AttachCodes(idx = attach.idx, codename = attach.codename )
        codeDataHandler.deleteCodeEntity(data)
        return data
    }

    override fun findCode(attach: AttachCodeDTO): AttachCodes {
        val data = AttachCodes(codename = attach.codename )
        codeDataHandler.findCodeEntity(data)
        return data
    }

    /**
     * 등록된 마스트키 조회
     */
    override fun findMastCode(subcode: String): List<AttachCodes> {
        return attachCodeRepository.findBySubcode(subcode)
    }

    override fun findListsEntity(attach: AttachCodeDTO, page: PageRequest): Page<AttachCodes> {
        val data = AttachCodes(codename = attach.codename )
        println("(@) ====== findListsEntity ====== (@)")
        return codeDataHandler.findListsEntity(data,page)
    }
}