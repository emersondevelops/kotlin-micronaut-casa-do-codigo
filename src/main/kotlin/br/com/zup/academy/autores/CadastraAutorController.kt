package br.com.zup.academy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository, val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastra(@Valid @Body request: NovoAutorRequest): HttpResponse<Any> {

        val enderecoResponse: HttpResponse<EnderecoResponse>

        return try {
            enderecoResponse = enderecoClient.consulta(request.cep)
            val autor = request.paraAutor(enderecoResponse.body()!!)
            autorRepository.save(autor)
            val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
            HttpResponse.created(uri)
        } catch (e: HttpClientResponseException) {
            HttpResponse.badRequest("CEP inválido ou serviço indisponível")
        }
    }
}