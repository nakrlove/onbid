package com.smtech.onbid.entity

import jakarta.persistence.*

@Entity
@Table(name = "code_tb")
data class Codes(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    val idx: Long? = null,

    @Column(name = "CODE")
    var code: String? = null,

    @Column(name = "NAME")
    var name: String? = null ,

    @Column(name = "SCODE")
    var scode: String? = null,
) {

    @PrePersist
    fun generateCode() {
    }

    private fun generateNextCode(): String {
        // 코드 생성 로직을 서비스 클래스에서 처리합니다.
        // 예를 들어, AttachCodeService에서 처리할 수 있습니다.
        return "000" // 디폴트 코드로 설정합니다.
    }

    @PostLoad
    fun setDefaultScode() {
        if (scode.isNullOrEmpty()) {
            scode = "000"
        }
    }
}
