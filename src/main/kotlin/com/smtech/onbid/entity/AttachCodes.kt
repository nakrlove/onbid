package com.smtech.onbid.entity

import com.smtech.onbid.service.CodeService
import com.smtech.onbid.service.impl.CodeServiceImpl
import jakarta.persistence.*
import org.springframework.beans.factory.annotation.Autowired

@Entity
@Table(name = "onbidcodes")
data class AttachCodes(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    val idx: Long? = null,

    @Column(name = "CODE")
    var code: String? = null,

    @Column(name = "CODE_NAME")
    val codename: String ,

    @Column(name = "SUB_CODE")
    val subcode: String? = null,
) {

    @PrePersist
    fun generateCode() {
    }

    private fun generateNextCode(): String {
        // 코드 생성 로직을 서비스 클래스에서 처리합니다.
        // 예를 들어, AttachCodeService에서 처리할 수 있습니다.
        return "000" // 디폴트 코드로 설정합니다.
    }
}
