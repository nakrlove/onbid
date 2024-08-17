package com.smtech.onbid.data.dao

import com.smtech.onbid.data.entity.OnBidDay

interface OnBidDayDAO {
    fun findDaysQuery(bididx: Int):List<OnBidDay>
}