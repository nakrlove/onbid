package com.smtech.onbid.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "onbidfiles")
data class OnBidFile (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    val idx: Int? = null,

    @Column(name = "FILENAME")
    val fileName: String,

    @Column(name = "FILESIZE")
    val fileSize: Long,

    @Lob
    @Column(name = "FILE")
    val file: ByteArray,

    @ManyToOne
    @JoinColumn(name = "BIDIDX")
    var onBidFiles: OnBid? = null
){
//    constructor(fileName,fileSize,file) : this(null, fileName , fileSize , file, null)

}