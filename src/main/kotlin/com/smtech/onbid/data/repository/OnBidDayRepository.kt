package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBidDay
import com.smtech.onbid.data.repository.inf.OnBidDaysRepositoryCustom
import org.springframework.data.repository.CrudRepository

interface OnBidDayRepository:  CrudRepository<OnBidDay, Int>, OnBidDaysRepositoryCustom {

}
