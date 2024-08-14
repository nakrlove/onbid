package com.smtech.onbid.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDateTime
@Entity
@Table(name = "onbid_tb")
data class OnBid(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BIDIDX")
    open val bididx: Int? = null,

    @Column(name = "ADDR1", columnDefinition = "TEXT")
    open var addr1: String? = null,
    @Column(name = "ADDR2", columnDefinition = "TEXT")
    open var addr2: String? = null,

    @Column(name = "REGDATE", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    open var regdate: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "IT_TYPE", columnDefinition = "VARCHAR")
    open var items: String? = null,

    @Column(name = "LD_AREA", columnDefinition = "VARCHAR")
    open var ld_area: String? = null, //토지면적

    @Column(name = "BUILD_AREA", columnDefinition = "VARCHAR")
    open var build_area: String? = null, //건물면적

    @Column(name = "RD_ADDR", columnDefinition = "TEXT")
    open var rd_addr: String? = null,  //도로주소명

    @Column(name = "STREEADDR2", columnDefinition = "TEXT")
    open var streeaddr2: String? = null,

    @Column(name = "BRUPTCY_ADMIN_NAME", columnDefinition = "VARCHAR")
    open var bruptcy_admin_name: String? = null,

    @Column(name = "BRUPTCY_ADMIN_PHONE", columnDefinition = "VARCHAR")
    open var bruptcy_admin_phone: String? = null,

    @Column(name = "RENTER", columnDefinition = "TEXT")
    open var renter: String? = null, /* 임차여부 */

    @Column(name = "ESTATETYPE", columnDefinition = "TEXT")
    open var estateType: String? = null, /* 부동산 종류*/

    @Column(name = "DISPOSAL_TYPE", columnDefinition = "VARCHAR")
    open var disposal_type: String? = null, /* 처분방식*/

    @Column(name = "NOTE", columnDefinition = "TEXT")
    open var note: String? = null,  /* 유의사항 */

    @Column(name = "LAND_CLASSIFICATION", columnDefinition = "VARCHAR")
    open var land_classification: String? = null, /* 지목 */

    @Column(name = "PROGRESS_STATUS", columnDefinition = "VARCHAR")
    open var progress_status: String? = "000",  /* 진행상태 */



    @OneToMany(mappedBy = "onMemo",cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
//    @JsonManagedReference
    @JsonIgnore
    var onMemo: MutableList<Memos> = mutableListOf(),

    @OneToMany(mappedBy = "onBid", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
//    @JsonManagedReference
    @JsonIgnore
    var onBidDays: MutableList<OnBidDays> = mutableListOf(),

    @OneToMany(mappedBy = "onBidFiles", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
//    @JsonManagedReference
    @JsonIgnore
    var onBidFiles: MutableList<OnBidFile>? = mutableListOf()
) {
    // 생성자 추가

    // 기본 생성자
//    constructor() : this(null, null, null, null, null,null,null)

    // 모든 필드를 초기화하는 보조 생성자
//    constructor(  addr1: String?
//                  , addr2: String?
//                  , bankruptcyName: String?
//                  , bankruptcyphone: String?
//                  , regdate: LocalDateTime?
//                  , items: String?
//                  , land: BigDecimal?
//                  , build: BigDecimal
//                  , streeaddr1: String?
//                  , streeaddr2:String?) : this(null,addr1,addr2, bankruptcyName, bankruptcyphone, regdate, items,land,build,streeaddr1,streeaddr2)

    /**
     * 파일첨부
     */
    fun addOnBidFiles(onBidFile: OnBidFile) {
        onBidFiles?.add(onBidFile)
        onBidFile.onBidFiles = this
    }

    fun removeOnBidDay(onBidFile: OnBidFile) {
        onBidFiles?.remove(onBidFile)
        onBidFile.onBidFiles = null
    }

    // Helper method to manage the bid days relationship
    fun addOnBidDay(onBidDay: OnBidDays) {
        onBidDays.add(onBidDay)
        onBidDay.onBid = this
    }

    fun removeOnBidDay(onBidDay: OnBidDays) {
        onBidDays.remove(onBidDay)
        onBidDay.onBid = null
    }

    // Helper method to manage the bid memo relationship
    fun addMemo(memo: Memos) {
        onMemo.add(memo)
        memo.onMemo = this
    }

    fun removeMemo(memo: Memos) {
        onMemo.remove(memo)
        memo.onMemo = null
    }
}