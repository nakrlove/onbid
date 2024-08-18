package com.smtech.onbid.data.repository.inf.impl

import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.repository.inf.OnBidDaysRepositoryCustom
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class OnBidDaysRepositoryCustomImpl: OnBidDaysRepositoryCustom {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findBidDaysQuery(bididx: Int?): List<OnBidDayDTO> {
        val query = entityManager.createNamedQuery("findBidDaysQuery", OnBidDayDTO::class.java)
        query.setParameter("bididx", bididx)
        return query.resultList
    }

}