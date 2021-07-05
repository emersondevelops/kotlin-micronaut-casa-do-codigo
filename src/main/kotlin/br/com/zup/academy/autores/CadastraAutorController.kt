package br.com.zup.academy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastra(@Valid @Body request: NovoAutorRequest): HttpResponse<Any> {

        val autor = request.paraAutor()
        autorRepository.save(autor);

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
    }
}