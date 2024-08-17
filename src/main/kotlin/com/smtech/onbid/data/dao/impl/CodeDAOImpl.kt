package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.CodeDAO
import com.smtech.onbid.data.repository.CodeRepository
import com.smtech.onbid.data.entity.Codes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CodeDAOImpl(@Autowired val attachCodeRepository: CodeRepository): CodeDAO {

    val LOGGER: Lazy<Logger> = lazy { LoggerFactory.getLogger(this.javaClass) }
    /** 3자리 코드생성 */
    override fun generateNextCode(): String {
        val maxCode = attachCodeRepository.findMaxCode()
        val nextCodeNumber = (maxCode?.toIntOrNull() ?: 0) + 1
        return nextCodeNumber.toString().padStart(3, '0')
    }

    @Transactional
    override fun saveCode(attach: Codes): Optional<Codes> {

        LOGGER.value.debug(" =========== saveCode ============")
        attach.code = generateNextCode() //code값 max값

        //null일경우 000r값 추가
        if (attach.scode.isNullOrEmpty()) {
            attach.scode = "000"
        }
        val saveResult = attachCodeRepository.save(attach)
        return  attachCodeRepository.findById(saveResult.idx!!)
    }

    /**
     * 수정
     */
    override fun updateCode(attach: Codes): Codes? {
        val optionalEntity = attachCodeRepository.findById(attach.idx!!)
        return if(optionalEntity.isPresent) {
            // 2. 엔티티 존재 시, 수정 작업 수행
            val attachCode = optionalEntity.get()
            attachCode.name = attach.name
            attachCode.scode = attach.scode
            // 3. save 메서드로 엔티티 저장 (업데이트)
            attachCodeRepository.save(attachCode)
        } else {
            null
        }

    }


    override fun deleteCode(attach: Codes) {
        attach.idx?.let { attachCodeRepository.deleteById(it) }
    }

    override fun findCodeQuery(): List<Codes> {
        return attachCodeRepository.findCodeQuery()
    }

    override fun findCode(attach: Codes): Optional<Codes> {
        LOGGER.value.debug(" =========== findCode ============")
        return   attachCodeRepository.findById(attach.idx!!);
    }

    /**
     * 목록조회
     */
    override fun findListsEntity(attach: Codes, page: PageRequest): Page<Codes> {

        LOGGER.value.debug(" =========== findListsEntity ============")
        return if (attach.name.isNullOrBlank()) {
            LOGGER.value.debug(" =========== (1) findListsEntity == pageNumber:${page.pageNumber},pageSize:${page.pageSize}")
            attachCodeRepository.findAll(page)
        } else {
            LOGGER.value.debug(" =========== (2) findListsEntity ============")
            attachCodeRepository.findByNameContaining(attach.name!!, page)
        }

    }


}