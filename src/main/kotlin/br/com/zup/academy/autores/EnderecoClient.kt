package br.com.zup.academy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:8081/cep/busca")
interface EnderecoClient {

    @Get("http://viacep.com.br/ws/{cep}/json/")
    fun consulta(@PathVariable cep: String): HttpResponse<EnderecoResponse>

}
