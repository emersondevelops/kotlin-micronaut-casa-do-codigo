package br.com.zup.academy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class DeletaAutorController(val autorRepository: AutorRepository) {

    @Delete("/{id}")
    fun deletar(@PathVariable id: Long): HttpResponse<Any> {

        val possivelAutor = autorRepository.findById(id)
        if (possivelAutor.isEmpty) return HttpResponse.notFound()

        autorRepository.deleteById(id)
        return HttpResponse.ok()
    }
}
