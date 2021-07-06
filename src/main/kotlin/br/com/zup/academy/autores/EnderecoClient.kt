package br.com.zup.academy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client
interface EnderecoClient {

    @Get("https://ws.apicep.com/cep/{cep}.xml")
    @Consumes(MediaType.APPLICATION_XML)
    fun consulta(@PathVariable cep: String): HttpResponse<EnderecoResponse>

}
