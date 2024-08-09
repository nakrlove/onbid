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
        attach.code = generateNextCode() //code값 max값

        //null일경우 000r값 추가
        if (attach.subcode.isNullOrEmpty()) {
            attach.subcode = "000"
        }
        val saveResult = attachCodeRepository.save(attach)
        return  attachCodeRepository.findById(saveResult.idx!!)
    }

    /**
     * 수정
     */
    override fun updateCode(attach: AttachCodes): AttachCodes? {
//        val data = AttachCodes(idx = attach.idx, codename = attach.codename )
        val optionalEntity = attachCodeRepository.findById(attach.idx!!)
        return if(optionalEntity.isPresent) {
            // 2. 엔티티 존재 시, 수정 작업 수행
            val attachCode = optionalEntity.get()
            attachCode.codename = attach.codename
            // 3. save 메서드로 엔티티 저장 (업데이트)
            attachCodeRepository.save(attachCode)
        } else {
            null
        }

    }


    override fun deleteCode(attach: AttachCodes) {
        attach.idx?.let { attachCodeRepository.deleteById(it) }
    }

    override fun findCode(attach: AttachCodes): Optional<AttachCodes> {
        LOGGER.value.debug(" =========== findCode ============")
        return   attachCodeRepository.findById(attach.idx!!);
    }

    /**
     * 목록조회
     */
    override fun findListsEntity(attach: AttachCodes, page: PageRequest): Page<AttachCodes> {

        LOGGER.value.debug(" =========== findListsEntity ============")
        return if (attach.codename.isNullOrBlank()) {
            LOGGER.value.debug(" =========== (1) findListsEntity ============")
            attachCodeRepository.findAll(page)
        } else {
            LOGGER.value.debug(" =========== (2) findListsEntity ============")
            attachCodeRepository.findByCodenameContaining(attach.codename!!, page)
        }

    }


}