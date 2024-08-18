package com.smtech.onbid.service

import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.entity.OnBidDay
import java.util.*

interface OnBidDaysService {
    fun statusUpdate(onBidDay: OnBidDayDTO): OnBidDay?
//    fun statusUpdate(onBidDay: OnBidDayDTO): Optional<OnBidDay>?

    fun findBidDaysQuery(bididx: Int?): List<OnBidDayDTO>

}