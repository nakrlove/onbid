package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.smtech.onbid.data.dto.OnBidFileDTO
import com.smtech.onbid.data.dto.OnBidMapDTO
import jakarta.persistence.*

@SqlResultSetMapping(
    name = "OnBidFileCateGory",
    classes = [
        ConstructorResult(
            targetClass = OnBidFileDTO::class,
            columns = [
                ColumnResult(name = "idx"),
                ColumnResult(name = "bididx"),
                ColumnResult(name = "code"),
                ColumnResult(name = "filename"),
                ColumnResult(name = "filetype"),
                ColumnResult(name = "filesize"),
                ColumnResult(name = "filepath"),

                ColumnResult(name = "file"),
                ColumnResult(name = "codename"),
            ]
        )
    ]
)
@NamedNativeQuery(
    name = "getFileCategory", /* 목록조회 */
    query = """
      WITH file_info AS (
                SELECT
                     b.idx
                    ,b.bididx
                    ,b.code
                    ,b.filename
                    ,b.filetype                   
                    ,b.filesize
                    ,b.filepath
                    ,b.file
                FROM onbidfiles_tb b
        )
         SELECT
                b.idx
               ,b.bididx
               ,b.code
               ,b.filename 
               ,b.filetype 
               ,b.filesize 
               ,b.filepath 
               ,b.file
               ,c.name as codename
         FROM file_info b
         LEFT JOIN code_tb c ON c.code COLLATE utf8mb4_unicode_ci = b.code COLLATE utf8mb4_unicode_ci
        WHERE b.bididx = :bididx
        ORDER BY b.code DESC
    """,
    resultSetMapping = "OnBidFileCateGory"
)
@Entity
@Table(name = "onbidfiles_tb")
data class OnBidFile (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    val idx: Int? = null,

    @Column(name = "CODE")
    val code: String?,

    @Column(name = "FILENAME")
    val fileName: String?,

    @Column(name = "FILETYPE")
    val fileType: String?,

    @Column(name = "FILESIZE")
    val fileSize: Long?,


    @Lob
    @Column(name = "FILE")
    val file: ByteArray?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIDIDX")
//    @JsonBackReference
    @JsonIgnore
    var onBidFiles: OnBid? = null
){
    constructor() : this(null, code = "", fileName = "", null, null,null)
}