package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "onbiddays_tb")
data class OnBidDay(@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                     @Column(name = "DAYSIDX")
                     val daysidx: Int? = null,

                    @Column(name = "SDATE")
                     var sdate: String = LocalDateTime.now().toString(),

                    @Column(name = "EDATE")
                     var edate: String = LocalDateTime.now().toString(),


                    @Column(name = "EVALUE")
                     var evalue: String?,

                    @Column(name = "DEPOSIT")
                     var deposit: String?,

                    @Column(name = "REGDATE")
                     var regdate: LocalDateTime? = null,
){
    constructor() : this(daysidx = null, sdate = "", edate = "", null, null,null)
}
