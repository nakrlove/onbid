package com.smtech.onbid.data.dao

import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.entity.OnBidDay
import java.util.*

interface OnBidDayDAO {
    fun findBidDaysQuery(bididx: Int):List<OnBidDayDTO>
    fun statusUpdate(onBidday: OnBidDay): OnBidDay?
//    fun statusUpdate(onBidday: OnBidDay): Optional<OnBidDay>?
}