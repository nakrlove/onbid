package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "onbiddays_tb")
data class OnBidDays(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DAYSIDX")
    val daysidx: Int? = null,

//    @Column(name = "SDATE")
//    var sdate: String = LocalDateTime.now().toString(),

    @Column(name = "EDATE")
    var edate: String = LocalDateTime.now().toString(),


    @Column(name = "EVALUE")
    var evalue: String?,

    @Column(name = "DEPOSIT")
    var deposit: String?,

    @Column(name = "ONBID_STATUS")
    var onbid_status: String?,

    @Column(name = "REGDATE")
    var regdate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIDIDX")
//    @JsonBackReference
    @JsonIgnore
    var onBid: OnBid? = null
){
    constructor() : this(daysidx = null,  edate = LocalDateTime.now().toString(), null, null,null,null)
}