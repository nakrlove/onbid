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


    /** 수정 */
    override fun statusUpdate(onBidday: OnBidDay): OnBidDay? {

        var optionalEntity = onBidDayRepository.findById(onBidday.daysidx!!)

        if(onBidday.onbid_status == "039") { //낙찰
            if(optionalEntity.isPresent) {
                val attachCode = optionalEntity.get()
                //현재입찰일자 이전은 모두 유찰상태 변경
                onBidDayRepository.successStatusByBididx("041", attachCode.bididx!!,attachCode.daysidx!!)
                //현재입찰일자 이후는 모두 종료상태(001) 변경
                onBidDayRepository.finishStatusByBididx("-01", attachCode.bididx!!,attachCode.daysidx!!)
            }
        }

        if(onBidday.onbid_status == "040") { //취소
            if(optionalEntity.isPresent) {
                val attachCode = optionalEntity.get()
                onBidDayRepository.updateStatusByBididx(onBidday.onbid_status!!, attachCode.bididx!!)
                return attachCode
            }
        }


        if(onBidday.onbid_status == "041") { //유찰
            if(optionalEntity.isPresent) {
                val attachCode = optionalEntity.get()
                //현재입찰일자 이전은 모두 유찰상태 변경
                onBidDayRepository.successStatusByBididx("041", attachCode.bididx!!,attachCode.daysidx!!)
            }
        }

        return if(optionalEntity.isPresent) {
            // 2. 엔티티 존재 시, 수정 작업 수행
            val attachCode = optionalEntity.get()
            attachCode.onbid_status = onBidday.onbid_status
            // 3. save 메서드로 엔티티 저장 (업데이트)
            onBidDayRepository.save(attachCode)
        } else {
            null
        }
    }
}