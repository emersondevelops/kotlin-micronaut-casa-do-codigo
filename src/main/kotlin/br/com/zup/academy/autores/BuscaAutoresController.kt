package br.com.zup.academy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated

@Validated
@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun busca(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            val autores = autorRepository.findAll();
            val resposta = autores.map { autor -> DetalhesDoAutorResponse(autor) }
            return HttpResponse.ok(resposta)
        }

        val possivelAutor = autorRepository.buscaPorEmail(email)
        if (possivelAutor.isEmpty) return HttpResponse.notFound()

        val autor = possivelAutor.get()
        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }
}
