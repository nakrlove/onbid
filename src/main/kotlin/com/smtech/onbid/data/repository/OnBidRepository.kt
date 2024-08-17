package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBid
import com.smtech.onbid.data.repository.inf.OnBidRepositoryCustom
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository


interface OnBidRepository : CrudRepository<OnBid, Int>, OnBidRepositoryCustom {
    /** 코드명 검색 */
    fun findByAddr1Like(addr1: String, pageable: Pageable): Page<OnBid>

//    @Query("""
//        WITH onbiddate1 AS (
//             select
//                    bididx
//                   ,date_format(sdate,'%Y-%m-%d') as sdate
//                   ,date_format(edate,'%Y-%m-%d') as edate
//                   ,format(evalue,0) evalue
//                   ,format(deposit,0) deposit
//                   ,ABS(DATEDIFF(CURDATE(),sdate)) AS sdate_diff
//                   ,ABS(DATEDIFF(CURDATE(), edate)) AS edate_diff
//                   ,ROW_NUMBER() OVER (
//                        PARTITION BY bididx
//                        ORDER BY
//                            LEAST(ABS(DATEDIFF(CURDATE(), sdate)), ABS(DATEDIFF(CURDATE(), edate)))
//                    ) AS rn
//               from onbiddays_tb
//            )
//            select b.bididx
//                  ,b.addr1
//                  ,b.addr2
//                  ,b.it_type
//                  ,b.ld_area
//                  ,b.build_area
//                  ,b.rd_addr
//                  ,b.streeaddr2
//                  ,b.bruptcy_admin_name
//                  ,b.bruptcy_admin_phone
//                  ,b.renter
//                  ,b.estatetype
//                  ,b.disposal_type
//                  ,b.note
//                  ,b.land_classification
//                  ,b.progress_status
//                  ,d.sdate
//                  ,d.edate
//                  ,d.evalue
//                  ,d.deposit
//                  from onbid_tb b
//            inner join onbiddate1 d on d.bididx = b.bididx
//            where d.rn = 1
//    """)
//    fun findAllCustmSQL(pageable: Pageable): Page<OnBid>

}