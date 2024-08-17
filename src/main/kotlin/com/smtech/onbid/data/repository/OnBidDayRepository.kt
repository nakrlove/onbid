package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBidDay
import com.smtech.onbid.data.entity.OnBidDays
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OnBidDayRepository: JpaRepository<OnBidDay, Int> {

    /**
     * 코드조회 (지목/면적)
     */
    @Query("""
      SELECT date_format(sdate,'%Y-%m-%d') as sdate 
            ,date_format(edate,'%Y-%m-%d') as edate
            ,format(evalue,0) as evalue
            ,format(deposit,0) as deposit
            ,daysidx
            ,bididx
            ,regdate
       FROM onbiddays_tb
      WHERE bididx = :bididx
      ORDER BY daysidx asc
    """, nativeQuery = true)
    fun findDaysQuery(@Param(value = "bididx") bididx: Int): List<OnBidDay>

}
