package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

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