package com.smtech.onbid.data.dto.wrapper

import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.entity.Memos

class OnBidWrapper(val bidMap: OnBidMapDTO?,val bidDay: List<OnBidDayDTO>?,val memos: List<Memos>?) {
}