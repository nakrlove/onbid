package com.smtech.onbid.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "ONBIDDAYS")
data class OnBidDays(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DAYSIDX")
    val daysidx: Int? = null,

    @Column(name = "STARTDAYS")
    var startDays: LocalDateTime = LocalDateTime.now(),

    @Column(name = "ENDDAYS")
    var endDays: LocalDateTime = LocalDateTime.now(),

    @Column(name = "REGDATE")
    var regdate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIDIDX")
    var onBid: OnBid? = null
) {
//    constructor() : this(null)
    constructor() : this(null, startDays = LocalDateTime.now(), endDays = LocalDateTime.now(), null, null)
}