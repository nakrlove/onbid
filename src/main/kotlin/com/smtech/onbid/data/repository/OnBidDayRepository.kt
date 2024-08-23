package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBidDay
import com.smtech.onbid.data.repository.inf.OnBidDaysRepositoryCustom
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface OnBidDayRepository:  CrudRepository<OnBidDay, Int>, OnBidDaysRepositoryCustom {

    /** 취소 */
    @Modifying
    @Transactional
    @Query("UPDATE onbiddays_tb o SET o.onbid_status = :status WHERE o.bididx = :bididx",nativeQuery = true)
    fun updateStatusByBididx(@Param("status") status: String, @Param("bididx") bididx: Int): Int


    /**
     * 낙찰시 현제일자이전은 유찰처리
     */
    @Modifying
    @Transactional
    @Query("UPDATE onbiddays_tb o SET o.onbid_status = :status WHERE o.bididx = :bididx AND o.daysidx < :daysidx order by o.daysidx asc",nativeQuery = true)
    fun successStatusByBididx(@Param("status") status: String, @Param("bididx") bididx: Int,@Param("daysidx") daysidx: Int): Int

    /**
     * 낙찰시 현제일자이전은 유찰처리
     */
    @Modifying
    @Transactional
    @Query("UPDATE onbiddays_tb o SET o.onbid_status = :status WHERE o.bididx = :bididx AND o.daysidx > :daysidx order by o.daysidx asc",nativeQuery = true)
    fun finishStatusByBididx(@Param("status") status: String, @Param("bididx") bididx: Int,@Param("daysidx") daysidx: Int): Int
}
