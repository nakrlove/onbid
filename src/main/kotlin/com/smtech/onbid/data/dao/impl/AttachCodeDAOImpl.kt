package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.AttachCodeDAO
import com.smtech.onbid.data.dto.AttachCodeDTO
import com.smtech.onbid.data.repository.AttachCodeRepository
import com.smtech.onbid.entity.AttachCodes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AttachCodeDAOImpl(@Autowired val attachCodeRepository: AttachCodeRepository): AttachCodeDAO {

    val LOGGER: Lazy<Logger> = lazy { LoggerFactory.getLogger(this.javaClass) }


    /** 3자리 코드생성 */
    override fun generateNextCode(): String {
        val maxCode = attachCodeRepository.findMaxCode()
        val nextCodeNumber = (maxCode?.toIntOrNull() ?: 0) + 1
        return nextCodeNumber.toString().padStart(3, '0')
    }

    @Transactional
    override fun saveCode(attach: AttachCodes): Optional<AttachCodes> {
        LOGGER.value.debug(" =========== saveCode ============")
        attach.code = generateNextCode()
        val saveResult = attachCodeRepository.save(attach)
        return  attachCodeRepository.findById(saveResult.idx!!)
    }

    override fun findCode(attach: AttachCodes): Optional<AttachCodes> {
        LOGGER.value.debug(" =========== findCode ============")
        return   attachCodeRepository.findById(attach.idx!!);
    }

    override fun findListsEntity(attach: AttachCodes, page: PageRequest): Page<AttachCodes> {
        LOGGER.value.debug(" =========== findListsEntity ============")
        return attachCodeRepository.findByCodenameContaining(attach.codename,page)
    }


}