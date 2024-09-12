package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dto.CodeDTO
import com.smtech.onbid.data.repository.CodeRepository
import com.smtech.onbid.data.entity.Codes
import com.smtech.onbid.handler.CodeDataHandler
import com.smtech.onbid.service.CodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CodeServiceImpl @Autowired constructor( val codeDataHandler: CodeDataHandler, @Autowired val attachCodeRepository: CodeRepository): CodeService {
//    override fun generateNextCode(): String {
//        return codeDataHandler.generateNextCode()
//    }


    override fun saveCode(attach: CodeDTO): Codes {

        val data = Codes(name = attach.codename, scode = attach.subcode)
        codeDataHandler.saveCodeEntity(data)
        return data
    }

    override fun updateCode(attach: CodeDTO): Codes? {
        // 1. ID로 엔티티 조회
        val data = Codes(idx = attach.idx, name = attach.codename , scode = attach.subcode, code = attach.code)
        return codeDataHandler.updateCodeEntity(data)
    }

    override fun deleteCode(attach: CodeDTO): Codes {
        val data = Codes(idx = attach.idx, name = attach.codename )
        codeDataHandler.deleteCodeEntity(data)
        return data
    }

    override fun findCode(attach: CodeDTO): Codes {
        val data = Codes(name = attach.codename )
        codeDataHandler.findCodeEntity(data)
        return data
    }

    override fun findCodeQuery(): List<Codes> {
       return  attachCodeRepository.findCodeQuery()
    }

    override fun findCodeQuery(codes: List<String>?): List<Codes> {
        return  attachCodeRepository.findCodeQuery(codes)
    }

    /**
     * 등록된 마스트키 조회
     */
    override fun findMastCode(subcode: String): List<Codes> {
        return attachCodeRepository.findByScode(subcode)
    }

//    override fun findListsEntity(attach: CodeDTO, page: PageRequest): CodeWrapper {
    override fun findListsEntity(attach: CodeDTO, page: PageRequest): Page<Codes> {
        val data = Codes(name = attach.codename )
//        val count = attachCodeRepository.count()
        println("(@) ====== findListsEntity ====== (@)")
        val resultList =  codeDataHandler.findListsEntity(data,page)
        return resultList
//        return CodeWrapper(count,resultList)
    }
}