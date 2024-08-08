package com.smtech.onbid.handler.impl

import com.smtech.onbid.data.dao.AttachCodeDAO
import com.smtech.onbid.entity.AttachCodes
import com.smtech.onbid.handler.CodeDataHandler
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class CodeDataHandlerImpl(@Autowired val attachDao: AttachCodeDAO): CodeDataHandler {
    override fun generateNextCode(): String {
        TODO("Not yet implemented")
    }

    override fun saveCodeEntity(attach: AttachCodes): Optional<AttachCodes> {
        return  attachDao.saveCode(attach)
    }

    override fun findCodeEntity(attach: AttachCodes): Optional<AttachCodes> {
        return  attachDao.findCode(attach)
    }

    override fun findListsEntity(attach: AttachCodes, page: PageRequest): Page<AttachCodes> {
        return attachDao.findListsEntity(attach,page)
    }
}