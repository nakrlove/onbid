package com.smtech.onbid.data.entity

import com.smtech.onbid.data.dto.OnBidDayDTO
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "onbiddays_tb")
@SqlResultSetMapping(
    name = "OnBidDaysWithDetailsMapping",
    classes = [
        ConstructorResult(
            targetClass = OnBidDayDTO::class,
            columns = [
                ColumnResult(name = "sdate"),
                ColumnResult(name = "edate"),
                ColumnResult(name = "evalue"),
                ColumnResult(name = "deposit"),
                ColumnResult(name = "daysidx"),
                ColumnResult(name = "bididx"),
                ColumnResult(name = "onbid_status"),
                ColumnResult(name = "regdate"),
                ColumnResult(name = "name")
            ]
        )
    ]
)
@NamedNativeQuery(
    name = "findBidDaysQuery",
    query = """
      WITH  onbiddays AS (
         SELECT date_format(sdate,'%Y-%m-%d') as sdate 
               ,date_format(edate,'%Y-%m-%d') as edate
               ,format(evalue,0) as evalue
               ,format(deposit,0) as deposit
               ,daysidx
               ,bididx
               ,onbid_status
               ,COALESCE(date_format(regdate, '%Y-%m-%d %H:%i:%s'), '') AS regdate
        FROM   onbiddays_tb
      )
      SELECT b.sdate 
            ,b.edate
            ,b.evalue
            ,b.deposit
            ,b.daysidx
            ,b.bididx
            ,b.onbid_status
            ,b.regdate
            ,c.name
       FROM onbiddays b 
       LEFT OUTER JOIN code_tb c ON c.code COLLATE utf8mb4_unicode_ci = b.onbid_status COLLATE utf8mb4_unicode_ci
      WHERE b.bididx = :bididx
      ORDER BY b.daysidx asc
    """,
    resultSetMapping = "OnBidDaysWithDetailsMapping"
)
@NamedNativeQuery(
    name = "findDaysQuery",
    query = """
      WITH  onbiddays AS (
         SELECT date_format(sdate,'%Y-%m-%d') as sdate 
               ,date_format(edate,'%Y-%m-%d') as edate
               ,format(evalue,0) as evalue
               ,format(deposit,0) as deposit
               ,daysidx
               ,bididx
               ,onbid_status
               ,COALESCE(date_format(regdate, '%Y-%m-%d %H:%i:%s'), '') AS regdate
        FROM   onbiddays_tb
      )
      SELECT b.sdate 
            ,b.edate
            ,b.evalue
            ,b.deposit
            ,b.daysidx
            ,b.bididx
            ,b.onbid_status
            ,b.regdate
            ,c.name
       FROM onbiddays b 
       LEFT OUTER JOIN code_tb c ON c.code COLLATE utf8mb4_unicode_ci = b.onbid_status COLLATE utf8mb4_unicode_ci
      WHERE b.bididx = :bididx
      ORDER BY b.daysidx asc
    """,
    resultSetMapping = "OnBidDaysWithDetailsMapping"
)
class OnBidDay(@Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    @Column(name = "DAYSIDX")
                    var daysidx: Int? = null,

                    @Column(name = "SDATE")
                    var sdate: String = LocalDateTime.now().toString(),

                    @Column(name = "EDATE")
                    var edate: String = LocalDateTime.now().toString(),

                    @Column(name = "EVALUE")
                    var evalue: String?,

                    @Column(name = "DEPOSIT")
                    var deposit: String?,

                    @Column(name = "ONBID_STATUS")
                    var onbid_status: String?,

//                    @Column(name = "NAME")
//                    var name: String?,

                    @Column(name = "REGDATE")
                     var regdate: String? = null,
//                     var regdate: LocalDateTime? = null,
){
//    constructor() : this(daysidx = null, sdate = LocalDateTime.now().toString(), edate = LocalDateTime.now().toString(), null, null,null,null)
    constructor() : this(null, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, null, null, null)
}