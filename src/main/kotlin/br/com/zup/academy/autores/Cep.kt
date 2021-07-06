package br.com.zup.academy.autores

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(RUNTIME)
@Constraint(validatedBy = [CepValidator::class])
annotation class Cep(val message: String = "CEP com formato inválido. Use o formato 99999-999")

@Singleton
class CepValidator : ConstraintValidator<Cep, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Cep>,
        context: ConstraintValidatorContext
    ): Boolean {
        // Formato: 99999-999 - [0-9]{5}-[0-9]{3}
        return value?.matches("[0-9]{5}-[0-9]{3}".toRegex()) ?: true
    }
}
