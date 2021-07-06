package br.com.zup.academy.autores

import java.time.LocalDateTime
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String,
    val endereco: Endereco
) {

    @Id
    @GeneratedValue
    var id: Long? = null

    val criadoEm: LocalDateTime = LocalDateTime.now()
}