package com.smtech.onbid.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
@Entity
@Table(name = "onbid")
data class OnBid(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BIDIDX")
    val bididx: Int? = null,

    @Column(name = "ADDR1", columnDefinition = "TEXT")
    var addr1: String? = null,
    @Column(name = "ADDR2", columnDefinition = "TEXT")
    var addr2: String? = null,

    @Column(name = "BANKRUPTCYNAME", columnDefinition = "TEXT")
    var bankruptcyName: String? = null,

    @Column(name = "BANKRUPTCYPHONE", columnDefinition = "TEXT")
    var bankruptcyphone: String? = null,

    @Column(name = "REGDATE", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    var regdate: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "ITEMS", columnDefinition = "TEXT")
    var items: String? = null,

    @Column(name = "LDDAREA", columnDefinition = "DECIMAL")
    var lddarea: BigDecimal? = null, //토지면적

    @Column(name = "BUILDINGAREA", columnDefinition = "DECIMAL")
    var buildingarea: BigDecimal? = null, //건물면적

    @OneToMany(mappedBy = "onBid", cascade = [CascadeType.ALL], orphanRemoval = true)
    var onBidDays: MutableList<OnBidDays> = mutableListOf()
) {
    // 생성자 추가
//    constructor( contents: String?,contphone: String?, regdate: LocalDateTime?,items: String?)
//            : this(contents, contphone,regdate,items)


    // 기본 생성자
    constructor() : this(null, null, null, null, null)

    // 모든 필드를 초기화하는 보조 생성자
    constructor(addr1: String?, addr2: String? ,bankruptcyName: String?, bankruptcyphone: String?, regdate: LocalDateTime?, items: String?) : this(null,addr1,addr2, bankruptcyName, bankruptcyphone, regdate, items)

    // Helper method to manage the bid days relationship
    fun addOnBidDay(onBidDay: OnBidDays) {
        onBidDays.add(onBidDay)
        onBidDay.onBid = this
    }

    fun removeOnBidDay(onBidDay: OnBidDays) {
        onBidDays.remove(onBidDay)
        onBidDay.onBid = null
    }
}