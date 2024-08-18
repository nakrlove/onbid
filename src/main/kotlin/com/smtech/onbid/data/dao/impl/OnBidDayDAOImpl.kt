package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.OnBidDayDAO
import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.entity.OnBidDay
import com.smtech.onbid.data.repository.OnBidDayRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OnBidDayDAOImpl(@Autowired val onBidDayRepository: OnBidDayRepository) : OnBidDayDAO {
    /* 입찰일자 조회 */
    override fun findBidDaysQuery(bididx: Int):List<OnBidDayDTO> {
        return onBidDayRepository.findBidDaysQuery(bididx)
    }

    override fun statusUpdate(onBidday: OnBidDay): OnBidDay? {

        var optionalEntity = onBidDayRepository.findById(onBidday.daysidx!!)
        return if(optionalEntity.isPresent) {
            // 2. 엔티티 존재 시, 수정 작업 수행
            val attachCode = optionalEntity.get()
            attachCode.onbid_status = onBidday.onbid_status
            // 3. save 메서드로 엔티티 저장 (업데이트)
            onBidDayRepository.save(attachCode)
//            onBidDayRepository.findDaysQuery(onBidday.daysidx!!)

        } else {
            null
        }
    }
}