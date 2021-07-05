package br.com.zup.academy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.validation.Validated

@Validated
@Controller("/autores")
class ListaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun lista() : HttpResponse<List<DetalhesDoAutorResponse>> {

        val autores = autorRepository.findAll();
        val resposta = autores.map { autor -> DetalhesDoAutorResponse(autor) }
        return HttpResponse.ok(resposta)
    }
}