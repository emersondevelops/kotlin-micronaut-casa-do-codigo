package br.com.zup.academy.autores

import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse, val numero: String, val cep: String) {

    val rua = enderecoResponse.address
    val cidade = enderecoResponse.city
    val estado = enderecoResponse.state

}
