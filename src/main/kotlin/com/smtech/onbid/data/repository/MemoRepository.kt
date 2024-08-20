package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.Memos
import com.smtech.onbid.data.entity.OnBid
import org.springframework.data.jpa.repository.JpaRepository

interface MemoRepository: JpaRepository<Memos, Int> {
    // onMemo 필드(onBid 엔티티)로 목록 조회
    fun findByOnMemo(onMemo: OnBid): List<Memos>
}