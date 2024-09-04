package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * 메모(유의사항/메모)
 */
@Entity
@Table(name = "memos_tb")
data class Memos(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    val idx: Int? = null,

    @Column(name = "MEMO_CONTENTS" , columnDefinition = "TEXT")
    var memo_contents: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "REGDATE", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    var regdate: LocalDateTime? = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIDIDX")
    @JsonBackReference
//    @JsonIgnore
    var onMemo: OnBid? = null
){
    override fun toString(): String {
        return "Memos(idx=$idx, memo_contents=$memo_contents, regdate=$regdate)"
    }
}