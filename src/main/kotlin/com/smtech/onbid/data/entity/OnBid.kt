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
/*
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
            d.sdate,
            d.edate,
            d.evalue,
            d.deposit,
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
        ) AS rn
        FROM onbid_tb b
        INNER JOIN onbiddays_tb d ON b.bididx = d.bididx
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
        onbid_status
    FROM filtered_status
    WHERE rn = 1
)
SELECT
    f.*,
    c.name AS land_classification_name
FROM final_selection f
LEFT JOIN code_tb c ON c.code COLLATE utf8mb4_unicode_ci = f.land_classification COLLATE utf8mb4_unicode_ci
*/

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
                ColumnResult(name = "land_classification_name"),
                ColumnResult(name = "onbid_status"),
                ColumnResult(name = "name")
            ]
        )
    ]
)
@NamedNativeQuery(
    name = "findOnBidWithDetails",
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
                d.sdate,
                d.edate,
                b.onbid_status,
                FORMAT(d.evalue, 0) AS evalue,
                FORMAT(d.deposit, 0) AS deposit,
                ABS(DATEDIFF(CURDATE(), d.sdate)) AS sdate_diff,
                ABS(DATEDIFF(CURDATE(), d.edate)) AS edate_diff,
                ROW_NUMBER() OVER (
                    PARTITION BY b.bididx
                    ORDER BY 
                        LEAST(ABS(DATEDIFF(CURDATE(), d.sdate)), ABS(DATEDIFF(CURDATE(), d.edate)))
                ) AS rn
            FROM 
                onbid_tb b
            INNER JOIN 
                onbiddays_tb d ON b.bididx = d.bididx
        )
        SELECT 
            c.bididx,
            c.addr1,
            c.addr2,
            c.it_type,
            c.ld_area,
            c.ld_area_pyeong,
            c.build_area,
            c.build_area_pyeong,
            c.rd_addr,
            c.streeaddr2,
            c.bruptcy_admin_name,
            c.bruptcy_admin_phone,
            c.renter,
            c.estatetype,
            c.disposal_type,
            c.note,
            c.land_classification,
            c.progress_status,
            DATE_FORMAT(c.sdate, '%Y-%m-%d') AS sdate,
            DATE_FORMAT(c.edate, '%Y-%m-%d') AS edate,
            c.evalue,
            c.deposit,
            e.name AS land_classification_name,
            c.onbid_status,
            '' as name
        FROM 
            closest_dates c
         left outer join 
            code_tb e ON e.code COLLATE utf8mb4_unicode_ci = c.land_classification COLLATE utf8mb4_unicode_ci
        WHERE c.rn = 1
          AND (:searchTerm IS NULL OR c.addr1 LIKE CONCAT('%', :searchTerm, '%') OR c.addr2 LIKE CONCAT('%', :searchTerm, '%'))
        ORDER BY c.bididx DESC
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
                e.name
            from onbid_detail d
            left outer join 
                    code_tb e ON e.code COLLATE utf8mb4_unicode_ci = d.onbid_status COLLATE utf8mb4_unicode_ci
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
    var items: String? = null,

    @Column(name = "LD_AREA", columnDefinition = "VARCHAR")
    var ld_area: String? = null,/* 토지면적 */

    @Column(name = "BUILD_AREA", columnDefinition = "VARCHAR")
    var build_area: String? = null, /* 건물면적 */

    @Column(name = "RD_ADDR", columnDefinition = "TEXT")
    var rd_addr: String? = null, /* 도로명주소1 */

    @Column(name = "STREEADDR2", columnDefinition = "TEXT")
    var streeaddr2: String? = null,

    @Column(name = "BRUPTCY_ADMIN_NAME", columnDefinition = "VARCHAR")
    var bruptcy_admin_name: String? = null,

    @Column(name = "BRUPTCY_ADMIN_PHONE", columnDefinition = "VARCHAR")
    var bruptcy_admin_phone: String? = null,

    @Column(name = "RENTER", columnDefinition = "TEXT")
    var renter: String? = null,

    @Column(name = "ESTATETYPE", columnDefinition = "TEXT")
    var estateType: String? = null,

    @Column(name = "DISPOSAL_TYPE", columnDefinition = "VARCHAR")
    var disposal_type: String? = null,

    @Column(name = "NOTE", columnDefinition = "TEXT")
    var note: String? = null,

    @Column(name = "LAND_CLASSIFICATION", columnDefinition = "VARCHAR")
    var land_classification: String? = null,

    @Column(name = "PROGRESS_STATUS", columnDefinition = "VARCHAR")
    var progress_status: String? = "000",

    @Column(name = "ONBID_STATUS", columnDefinition = "VARCHAR")
    var onbid_status: String? = "038", /* 입찰진행상태(기본값 입찰중) */


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
