package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.smtech.onbid.data.dto.OnBidMapDTO
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "onbid_tb")
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
                ColumnResult(name = "ld_area_pyeong"),
                ColumnResult(name = "build_area"),
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
                ColumnResult(name = "sdate"),
                ColumnResult(name = "edate"),
                ColumnResult(name = "evalue"),
                ColumnResult(name = "deposit"),
                ColumnResult(name = "onbid_status"),
                ColumnResult(name = "status"),
                ColumnResult(name = "land_classification_name"),
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
                    ROUND(b.ld_area * 0.3025, 2) AS ld_area_pyeong,
                    b.build_area,
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
                    DATE_FORMAT(d.sdate, '%Y-%m-%d') AS sdate,
                    DATE_FORMAT(d.edate, '%Y-%m-%d') AS edate,
                    FORMAT(d.evalue,0) AS evalue,
                    FORMAT(d.deposit,0) AS deposit,
                    d.onbid_status,
                    ABS(DATEDIFF(CURDATE(), d.sdate)) AS sdate_diff,
                    ABS(DATEDIFF(CURDATE(), d.edate)) AS edate_diff,
                    ROW_NUMBER() OVER (
                        PARTITION BY b.bididx
                                ORDER BY
                                CASE
                                WHEN d.onbid_status IN ('039', '040') THEN 0
                                ELSE LEAST(sdate_diff, edate_diff)
                                END
                ) AS rn,
                c.name as status
                FROM onbid_tb b
                INNER JOIN onbiddays_tb d ON b.bididx = d.bididx
                LEFT OUTER JOIN code_tb c ON c.code = d.onbid_status
        ),
        filtered_status AS (
                SELECT *
                  FROM closest_dates
                 WHERE rn = 1
            AND (onbid_status IN ('039', '040') OR sdate_diff IS NOT NULL)
        ),
        final_selection AS (
            SELECT
                bididx,
                addr1,
                addr2,
                it_type,
                ld_area,
                ld_area_pyeong,
                build_area,
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
                sdate,
                edate,
                evalue,
                deposit,
                onbid_status,
                status
            FROM filtered_status
            WHERE rn = 1
        )
         SELECT
            f.bididx,
            f.addr1,
            f.addr2,
            f.it_type,
            f.ld_area,
            f.ld_area_pyeong,
            f.build_area,
            f.build_area_pyeong,
            f.rd_addr,
            f.streeaddr2,
            f.bruptcy_admin_name,
            f.bruptcy_admin_phone,
            f.renter,
            f.estatetype,
            f.disposal_type,
            f.note,
            f.land_classification,
            f.progress_status,
            f.sdate,
            f.edate,
            f.evalue,
            f.deposit,
            f.onbid_status,
            f.status,
            c.name AS land_classification_name
         FROM final_selection f
         LEFT JOIN code_tb c ON c.code COLLATE utf8mb4_unicode_ci = f.land_classification COLLATE utf8mb4_unicode_ci
        WHERE 1 = 1
          AND (:searchTerm IS NULL OR f.addr1 LIKE CONCAT('%', :searchTerm, '%') OR f.addr2 LIKE CONCAT('%', :searchTerm, '%'))
        ORDER BY f.bididx DESC
        LIMIT :limit , :offset
    """,
    resultSetMapping = "OnBidWithDetailsMapping"
)
@NamedNativeQuery(
    name = "countOnBidWithDetails",/* 전체카운터 */
    query = """
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
        WHERE d.rn = 1
          AND (:searchTerm IS NULL OR b.addr1 LIKE CONCAT('%', :searchTerm, '%') OR b.addr2 LIKE CONCAT('%', :searchTerm, '%'))
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
                    ROUND(b.ld_area * 0.3025, 2) AS ld_area_pyeong,
                    b.build_area,
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
                    b.regdate
            FROM onbid_tb b
          )
          select 
                d.bididx,
                d.addr1,
                d.addr2,
                d.it_type,
                d.ld_area,
                d.ld_area_pyeong,
                d.build_area,
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
                '' as sdate,
                '' as edate,
                '' as evalue,
                '' as deposit,
                '' as land_classification_name,
                d.onbid_status,
                e.name as status
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
    var regdate: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "IT_TYPE", columnDefinition = "VARCHAR")
    var items: String? = null,    /* 물건구분 */

    @Column(name = "LD_AREA", columnDefinition = "VARCHAR")
    var ld_area: String? = null,   /* 토지면적 */

    @Column(name = "BUILD_AREA", columnDefinition = "VARCHAR")
    var build_area: String? = null, /* 건물면적 */

    @Column(name = "RD_ADDR", columnDefinition = "TEXT")
    var rd_addr: String? = null, /* 도로명주소1 */

    @Column(name = "STREEADDR2", columnDefinition = "TEXT")
    var streeaddr2: String? = null, /* 도로명주소2 */

    @Column(name = "BRUPTCY_ADMIN_NAME", columnDefinition = "VARCHAR")
    var bruptcy_admin_name: String? = null,  /* 파산관제인전화번호 */

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


    @OneToMany(mappedBy = "onMemo", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    var onMemo: MutableList<Memos> = mutableListOf(),

    @OneToMany(mappedBy = "onBid", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    var onBidDays: MutableList<OnBidDays> = mutableListOf(),

    @OneToMany(mappedBy = "onBidFiles", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    var onBidFiles: MutableList<OnBidFile>? = mutableListOf()
) {
    fun addOnBidFiles(onBidFile: OnBidFile) {
        onBidFiles?.add(onBidFile)
        onBidFile.onBidFiles = this
    }

    fun removeOnBidFile(onBidFile: OnBidFile) {
        onBidFiles?.remove(onBidFile)
        onBidFile.onBidFiles = null
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
        onMemo.add(memo)
        memo.onMemo = this
    }

    fun removeMemo(memo: Memos) {
        onMemo.remove(memo)
        memo.onMemo = null
    }
}
