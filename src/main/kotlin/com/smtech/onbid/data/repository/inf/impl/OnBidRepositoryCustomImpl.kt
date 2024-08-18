package com.smtech.onbid.data.repository.inf.impl

import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.repository.inf.OnBidRepositoryCustom
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class OnBidRepositoryCustomImpl : OnBidRepositoryCustom {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findOnBidLists(searchTerm: String?, limit: Int, offset: Int): List<OnBidMapDTO> {
        val query = entityManager.createNamedQuery("findOnBidLists", OnBidMapDTO::class.java)
        query.setParameter("searchTerm", searchTerm)
        query.setParameter("limit", limit)
        query.setParameter("offset", offset)
        return query.resultList
    }

    override fun countOnBidWithDetails(searchTerm: String?): Long {
        val query = entityManager.createNamedQuery("countOnBidWithDetails")
        query.setParameter("searchTerm", searchTerm)
        return (query.singleResult as Number).toLong()
    }

    override fun onBidDetails(bididx: Int): OnBidMapDTO {
        val query = entityManager.createNamedQuery("onBidDetails", OnBidMapDTO::class.java)
        query.setParameter("bididx", bididx)
        return query.singleResult
    }

//    @Suppress("UNCHECKED_CAST")
    override fun findDaysQuery(bididx: Int): List<OnBidDayDTO> {
        val query = entityManager.createNamedQuery("findDaysQuery", OnBidDayDTO::class.java)
        query.setParameter("bididx", bididx)
        return query.resultList
    }
}