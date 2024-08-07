package com.smtech.onbid.data.repository

import com.smtech.onbid.entity.AttachCodes


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

interface AttachCodeRepository: JpaRepository<AttachCodes, Long> {
    /** 코드명 검색 */
    fun findByCodenameContaining(codename: String, pageable: Pageable): Page<AttachCodes>

//    @Query("SELECT MAX(c.code) FROM onbidcodes c")
    @Query("SELECT MAX(c.code) FROM AttachCodes c")
    fun findMaxCode(): String?
}