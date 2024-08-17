package com.smtech.onbid.handler.impl

import com.smtech.onbid.data.dao.CodeDAO
import com.smtech.onbid.data.entity.Codes
import com.smtech.onbid.handler.CodeDataHandler
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class CodeDataHandlerImpl(@Autowired val attachDao: CodeDAO): CodeDataHandler {
//    override fun generateNextCode(): String {
////        TODO("Not yet implemented")
//        return
//    }

    override fun saveCodeEntity(attach: Codes): Optional<Codes> {
        return  attachDao.saveCode(attach)
    }

    override fun updateCodeEntity(attach: Codes): Codes? {
        return  attachDao.updateCode(attach)
    }


    override fun deleteCodeEntity(attach: Codes) {
        attachDao.deleteCode(attach)
    }

    override fun findCodeEntity(attach: Codes): Optional<Codes> {
        return  attachDao.findCode(attach)
    }

    override fun findListsEntity(attach: Codes, page: PageRequest): Page<Codes> {
        println("(||) === findListsEntity === (||)")
        return attachDao.findListsEntity(attach,page)
    }
}