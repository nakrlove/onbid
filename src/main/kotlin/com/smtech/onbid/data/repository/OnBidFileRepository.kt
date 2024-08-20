package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBidDay
import com.smtech.onbid.data.entity.OnBidFile
import com.smtech.onbid.data.repository.inf.OnBidFileRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OnBidFileRepository :  CrudRepository<OnBidFile, Int>, OnBidFileRepositoryCustom {
    // 기본적인 CRUD 메소드 (save, findById, findAll, delete 등)은 JpaRepository에서 제공됨
    override fun findById(id: Int): Optional<OnBidFile>
}