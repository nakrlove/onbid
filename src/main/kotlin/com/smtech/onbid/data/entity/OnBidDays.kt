package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
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
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 원하는 형식으로 변경
    @JsonFormat(pattern = "yyyy-MM-dd") // 원하는 형식으로 변경
    var edate: String? = null,
//    var edate: String = LocalDateTime.now().toString(),


    @Column(name = "EVALUE")
    var evalue: String?,

    @Column(name = "DEPOSIT")
    var deposit: String?,

    @Column(name = "ONBID_STATUS")
    var onbid_status: String?,

    @Column(name = "REGDATE")
    @JsonFormat(pattern = "yyyy-MM-dd") // 원하는 형식으로 변경
    var regdate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIDIDX")
    @JsonBackReference
//    @JsonIgnore
    var onBid: OnBid? = null
){
    constructor() : this(daysidx = null,  edate = null, null, null,null,null)
}