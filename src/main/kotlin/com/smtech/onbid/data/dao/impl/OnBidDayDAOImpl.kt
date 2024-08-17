package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.OnBidDayDAO
import com.smtech.onbid.data.entity.OnBidDay
import com.smtech.onbid.data.entity.OnBidDays
import com.smtech.onbid.data.repository.OnBidDayRepository
import com.smtech.onbid.data.repository.OnBidDaysRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OnBidDayDAOImpl(@Autowired val onBidDayRepository: OnBidDayRepository) : OnBidDayDAO {
    /* 입찰일자 조회 */
    override fun findDaysQuery(bididx: Int):List<OnBidDay> {
        return onBidDayRepository.findDaysQuery(bididx)
    }
}