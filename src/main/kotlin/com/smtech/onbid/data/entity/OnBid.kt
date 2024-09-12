package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.smtech.onbid.data.dto.OnBidMapDTO
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "onbid_tb")
@EntityListeners(OnBidListener::class)
@SqlResultSetMapping(
    name = "OnBidWithDetailsMapping",
    classes = [
        ConstructorResult(
            targetClass = OnBidMapDTO::class,
            columns = [

                ColumnResult(name = "bididx"),
                ColumnResult(name = "addr1"),
                ColumnResult(name = "addr2"),
                ColumnResult(name = "it_type"),
                ColumnResult(name = "ld_area"),
                ColumnResult(name = "ld_area_memo"),
                ColumnResult(name = "ld_area_pyeong"),
                ColumnResult(name = "build_area"),
                ColumnResult(name = "build_area_memo"),
                ColumnResult(name = "build_area_pyeong"),
                ColumnResult(name = "rd_addr"),
                ColumnResult(name = "streeaddr2"),
                ColumnResult(name = "bruptcy_admin_name"),
                ColumnResult(name = "bruptcy_admin_phone"),
                ColumnResult(name = "renter"),
                ColumnResult(name = "estatetype"),
                ColumnResult(name = "disposal_type"),
                ColumnResult(name = "note"),
                ColumnResult(name = "land_classification"),
                ColumnResult(name = "progress_status"),
//                ColumnResult(name = "sdate"),
                ColumnResult(name = "edate"),
                ColumnResult(name = "evalue"),
                ColumnResult(name = "deposit"),
                ColumnResult(name = "onbid_status"),
                ColumnResult(name = "status"),
                ColumnResult(name = "land_classification_name"),
                ColumnResult(name = "national_land_planning_use_laws"),
                ColumnResult(name = "other_laws"),
                ColumnResult(name = "enforcement_decree"),
                ColumnResult(name = "idx"),
                ColumnResult(name = "debtor"),
                ColumnResult(name = "pnu"),
            ]
        )
    ]
)
@NamedNativeQuery(
    name = "findOnBidLists", /* 목록조회 */
    query = """
      WITH closest_dates AS (
                SELECT
                        b.bididx,
                        b.addr1,
                        b.addr2,
                        b.it_type,
                        b.ld_area,
                        b.ld_area_memo,
                        ROUND(b.ld_area * 0.3025, 2) AS ld_area_pyeong,
                        b.build_area,
                        b.build_area_memo,
                        ROUND(b.build_area * 0.3025, 2) AS build_area_pyeong,
                        b.rd_addr,
                        b.streeaddr2,
                        b.bruptcy_admin_name,
                        b.bruptcy_admin_phone,
                        b.renter,
                        b.estatetype,
                        b.disposal_type,
                        b.note,
                        b.land_classification,
                        b.progress_status,
                       -- DATE_FORMAT(d.sdate, '%Y-%m-%d') AS sdate,
                        DATE_FORMAT(d.edate, '%Y-%m-%d') AS edate,
                        FORMAT(d.evalue,0) AS evalue,
                        FORMAT(d.deposit,0) AS deposit,
                        d.onbid_status,
                        ABS(DATEDIFF(CURDATE(), d.edate)) AS edate_diff,
                        ROW_NUMBER() OVER (
                            PARTITION BY b.bididx
                            ORDER BY 
                                CASE 
                                    -- 입찰중(038)을 우선순위로, 그다음 유찰(041), 취소(040), 낙찰(039) 순으로 처리
                                    WHEN d.onbid_status  IN ('038','001') THEN 1
                                    WHEN d.onbid_status = '041' THEN 2
                                    WHEN d.onbid_status = '040' THEN 3
                                    WHEN d.onbid_status = '039' THEN 4
                                    ELSE 5
                                END,
                                 d.edate ASC -- 같은 상태라면 가까운 edate를 우선
                        ) AS rn,
                        CASE
                            WHEN d.edate < CURDATE() AND d.onbid_status = '039' THEN '낙찰'
                            WHEN d.edate < CURDATE() AND d.onbid_status = '040' THEN '취소'
                            WHEN d.edate < CURDATE() AND d.onbid_status = '041' THEN '유찰'
                            ELSE '입찰중'
                        END AS status,
                     -- c.name as status ,
                        b.national_land_planning_use_laws ,
                        b.other_laws ,
                        b.enforcement_decree ,
                        b.idx ,
                        b.debtor,
                        b.pnu
                FROM onbid_tb b
               INNER JOIN onbiddays_tb d ON b.bididx = d.bididx
                LEFT OUTER JOIN code_tb c ON c.code = d.onbid_status

        ),
        filtered_status AS (
                SELECT *
                  FROM closest_dates
                 WHERE rn = 1
            AND (onbid_status IN ('039', '040') OR edate_diff IS NOT NULL)
        ),
        final_selection AS (
            SELECT
                bididx,
                addr1,
                addr2,
                it_type,
                ld_area,
                ld_area_memo,
                ld_area_pyeong,
                build_area,
                build_area_memo,
                build_area_pyeong,
                rd_addr,
                streeaddr2,
                bruptcy_admin_name,
                bruptcy_admin_phone,
                renter,
                estatetype,
                disposal_type,
                note,
                land_classification,
                progress_status,
                edate   ,
                evalue  ,
                deposit ,
                onbid_status,
                status  ,
                national_land_planning_use_laws ,
                other_laws ,
                enforcement_decree ,
                idx ,
                debtor,
                pnu
            FROM filtered_status
            WHERE rn = 1
        )
         SELECT
            f.bididx,
            f.addr1,
            f.addr2,
            f.it_type,
            f.ld_area,
            f.ld_area_memo,
            f.ld_area_pyeong,
            f.build_area,
            f.build_area_memo,
            f.build_area_pyeong,
            f.rd_addr,
            f.streeaddr2,
            f.bruptcy_admin_name,
            f.bruptcy_admin_phone,
            f.renter,
            d.name as estatetype,
            f.disposal_type,
            f.note,
            f.land_classification,
            f.progress_status,
            f.edate,
            f.evalue,
            f.deposit,
            f.onbid_status,
          --  g.name as status,
            f.status,
            c.name AS land_classification_name ,
            f.national_land_planning_use_laws ,
            f.other_laws ,
            f.enforcement_decree ,
            f.idx ,
            f.debtor,
            f.pnu
         FROM final_selection f
         LEFT JOIN code_tb c ON c.code COLLATE utf8mb4_unicode_ci = f.land_classification COLLATE utf8mb4_unicode_ci
         LEFT JOIN code_tb d ON d.code COLLATE utf8mb4_unicode_ci = f.estatetype COLLATE utf8mb4_unicode_ci
         LEFT JOIN code_tb g ON g.code COLLATE utf8mb4_unicode_ci = f.status COLLATE utf8mb4_unicode_ci
        WHERE (:searchTerm = 0 OR f.idx = :searchTerm )
        ORDER BY f.bididx DESC
        LIMIT :limit , :offset
    """,
    resultSetMapping = "OnBidWithDetailsMapping"
)


/* 문제가 되어 기록해둠
     WITH onbiddate AS (
            SELECT
                bididx,
                ROW_NUMBER() OVER (
                    PARTITION BY bididx
                    ORDER BY
                        LEAST(ABS(DATEDIFF(CURDATE(), sdate)), ABS(DATEDIFF(CURDATE(), edate)))
                ) AS rn
            FROM onbiddays_tb
        )
        SELECT COUNT(*)
        FROM onbid_tb b
        INNER JOIN onbiddate d ON d.bididx = b.bididx
        WHERE (:searchTerm = 0 OR b.idx = :searchTerm )
 */
@NamedNativeQuery(
    name = "countOnBidWithDetails",/* 전체카운터 */
    query = """
        SELECT COUNT(*)
          FROM onbid_tb b
         WHERE (:searchTerm = 0 OR b.idx = :searchTerm )
    """
)
@NamedNativeQuery(
    name = "onBidDetails",/* 상세보기 */
    query = """
      WITH onbid_detail AS (
             SELECT 
                    b.bididx,
                    b.addr1,
                    b.addr2,
                    b.it_type,
                    b.ld_area,
                    b.ld_area_memo,
                    ROUND(b.ld_area * 0.3025, 2) AS ld_area_pyeong,
                    b.build_area,
                    b.build_area_memo,
                    ROUND(b.build_area * 0.3025, 2) AS build_area_pyeong,
                    b.rd_addr,
                    b.streeaddr2,
                    b.bruptcy_admin_name,
                    b.bruptcy_admin_phone,
                    b.renter,
                    b.estatetype,
                    b.disposal_type,
                    b.note,
                    b.land_classification,
                    b.progress_status,
                    b.onbid_status,
                    DATE_FORMAT(b.regdate, '%Y-%m-%d %H:%i:%s') regdate     ,
                    b.national_land_planning_use_laws ,
                    b.other_laws ,
                    b.enforcement_decree ,
                    b.idx ,
                    b.debtor ,
                    b.pnu
            FROM onbid_tb b
          )
          select 
                d.bididx,
                d.addr1,
                d.addr2,
                d.it_type,
                d.ld_area,
                d.ld_area_memo,
                d.ld_area_pyeong,
                d.build_area,
                d.build_area_memo,
                d.build_area_pyeong,
                d.rd_addr,
                d.streeaddr2,
                d.bruptcy_admin_name,
                d.bruptcy_admin_phone,
                d.renter,
                d.estatetype,
                d.disposal_type,
                d.note,
                d.land_classification,
                d.progress_status,
               -- '' as sdate,
                '' as edate,
                '' as evalue,
                '' as deposit,
                '' as land_classification_name,
                d.onbid_status,
                e.name as status ,
                d.national_land_planning_use_laws ,
                d.other_laws,
                d.enforcement_decree ,
                d.idx ,
                d.debtor,
                d.pnu
            from onbid_detail d
            left outer join code_tb e ON e.code COLLATE utf8mb4_unicode_ci = d.onbid_status COLLATE utf8mb4_unicode_ci
            where d.bididx = :bididx
    """,
    resultSetMapping = "OnBidWithDetailsMapping"
)
data class OnBid(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BIDIDX")
    val bididx: Int? = null,

    @Column(name = "ADDR1", columnDefinition = "TEXT")
    var addr1: String? = null,

    @Column(name = "ADDR2", columnDefinition = "TEXT")
    var addr2: String? = null,

    @Column(name = "REGDATE", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 원하는 형식으로 변경
    var regdate: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "IT_TYPE", columnDefinition = "VARCHAR")
    var items: String? = null,                 /* 물건구분 */

    @Column(name = "LD_AREA", columnDefinition = "VARCHAR")
    var ld_area: String? = null,              /* 토지면적 */
    @Column(name = "LD_AREA_MEMO", columnDefinition = "VARCHAR")
    var ld_area_memo: String? = null,         /* 토지면적 메모*/

    @Column(name = "BUILD_AREA", columnDefinition = "VARCHAR")
    var build_area: String? = null,           /* 건물면적 */
    @Column(name = "BUILD_AREA_MEMO", columnDefinition = "VARCHAR")
    var build_area_memo: String? = null,      /* 건물면적 메모*/

    @Column(name = "RD_ADDR", columnDefinition = "TEXT")
    var rd_addr: String? = null,              /* 도로명주소1 */

    @Column(name = "STREEADDR2", columnDefinition = "TEXT")
    var streeaddr2: String? = null,           /* 도로명주소2 */

    @Column(name = "BRUPTCY_ADMIN_NAME", columnDefinition = "VARCHAR")
    var bruptcy_admin_name: String? = null,   /* 파산관제인전화번호 */

    @Column(name = "BRUPTCY_ADMIN_PHONE", columnDefinition = "VARCHAR")
    var bruptcy_admin_phone: String? = null,

    @Column(name = "RENTER", columnDefinition = "VARCHAR")
    var renter: String? = null,               /* 임차여부 */

    @Column(name = "ESTATETYPE", columnDefinition = "VARCHAR")
    var estateType: String? = null,           /* 부동산종류 */

    @Column(name = "DISPOSAL_TYPE", columnDefinition = "VARCHAR")
    var disposal_type: String? = null,        /* 처분방식 */

    @Column(name = "NOTE", columnDefinition = "TEXT")
    var note: String? = null,                 /* 유의사항 */

    @Column(name = "LAND_CLASSIFICATION", columnDefinition = "VARCHAR")
    var land_classification: String? = null,  /* 지목 */

    @Column(name = "PROGRESS_STATUS", columnDefinition = "VARCHAR")
    var progress_status: String? = "000",     /* 진행상태 */

    @Column(name = "ONBID_STATUS", columnDefinition = "VARCHAR")
    var onbid_status: String? = "038",        /* 입찰진행상태(기본값 입찰중) */

    @Column(name = "DEBTOR", columnDefinition = "VARCHAR")
    var debtor: String? = null,               /* 채무자명 */

    @Column(name = "NATIONAL_LAND_PLANNING_USE_LAWS", columnDefinition = "VARCHAR")
    var national_land_planning_use_laws: String? = null,    /* 「국토의 계획 및 이용에 관한 법률」에 따른 지역ㆍ지구등 */

    @Column(name = "OTHER_LAWS", columnDefinition = "VARCHAR")
    var other_laws: String? = null,                         /* 다른 법령 등에 따른 지역ㆍ지구등 */

    @Column(name = "ENFORCEMENT_DECREE", columnDefinition = "VARCHAR")
    var enforcement_decree: String? = null,                 /* 시행령 */

    @Column(name = "IDX", columnDefinition = "INT")
    var idx: Int? = null,                                   /* 관심종목 */
    @Column(name = "PNU", columnDefinition = "VARCHAR")
    var pnu: String? = null,                                /* 필지번호 */

    @OneToMany(mappedBy = "onMemo", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
//    @JsonIgnore
    @JsonManagedReference
    var onMemo: MutableList<Memos>? = mutableListOf(),

    @OneToMany(mappedBy = "onBid", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    var onBidDays: MutableList<OnBidDays> = mutableListOf(),

    @OneToMany(mappedBy = "onBidFiles", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
//    @JsonIgnore
    @JsonManagedReference
    var onBidFiles: MutableList<OnBidFile>? = mutableListOf()
) {

    override fun toString(): String {
        return "OnBid(bididx=$bididx, addr1=$addr1, addr2=$addr2, regdate=$regdate, items=$items, ld_area=$ld_area, build_area=$build_area, rd_addr=$rd_addr, streeaddr2=$streeaddr2, bruptcy_admin_name=$bruptcy_admin_name, bruptcy_admin_phone=$bruptcy_admin_phone, renter=$renter, estateType=$estateType, disposal_type=$disposal_type, note=$note, land_classification=$land_classification, progress_status=$progress_status, onbid_status=$onbid_status, debtor=$debtor, national_land_planning_use_laws=$national_land_planning_use_laws, other_laws=$other_laws, enforcement_decree=$enforcement_decree, idx=$idx)"
    }
    fun addOnBidFiles(onBidFile: OnBidFile) {
        onBidFiles?.add(onBidFile)
        onBidFile?.onBidFiles = this
    }

    fun removeOnBidFile(onBidFile: OnBidFile) {
        onBidFiles?.remove(onBidFile)
        onBidFile?.onBidFiles = null
    }

    fun addOnBidDay(onBidDay: OnBidDays) {
        onBidDays.add(onBidDay)
        onBidDay.onBid = this
    }

    fun removeOnBidDay(onBidDay: OnBidDays) {
        onBidDays.remove(onBidDay)
        onBidDay.onBid = null
    }

    fun addMemo(memo: Memos) {
        onMemo?.add(memo)
        memo?.onMemo = this
    }

    fun removeMemo(memo: Memos) {
        onMemo?.remove(memo)
        memo?.onMemo = null
    }
}

class OnBidListener {
    @PreUpdate
    fun preUpdate(onBid: OnBid) {
        println("OnBid가 업데이트 되었습니다: $onBid")
    }

    @PostUpdate
    fun postUpdate(onBid: OnBid) {
        println("OnBid 업데이트 후: $onBid")
    }
}