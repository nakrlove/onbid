package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dao.OnBidDayDAO
import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.entity.OnBidDay
import com.smtech.onbid.service.OnBidDaysService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class OnBidDaysServiceImpl(@Autowired val onBidDayDAO: OnBidDayDAO): OnBidDaysService {

    @Transactional
    override fun statusUpdate(onBidDay: OnBidDayDTO): OnBidDay?{
        val param = OnBidDay( daysidx = onBidDay.daysidx!!
            ,sdate = LocalDateTime.now().toString()
            ,edate = LocalDateTime.now().toString()
            ,evalue = null
            ,deposit = null
            ,onbid_status = onBidDay.onbid_status
            ,null)
        return onBidDayDAO.statusUpdate(param)
    }

    override fun findBidDaysQuery(bididx: Int?): List<OnBidDayDTO> {
        return onBidDayDAO.findBidDaysQuery(bididx!!)
    }
}