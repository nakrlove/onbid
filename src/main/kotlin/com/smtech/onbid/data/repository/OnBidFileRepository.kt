package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBidFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OnBidFileRepository : JpaRepository<OnBidFile, Int> {
    // 기본적인 CRUD 메소드 (save, findById, findAll, delete 등)은 JpaRepository에서 제공됨
}