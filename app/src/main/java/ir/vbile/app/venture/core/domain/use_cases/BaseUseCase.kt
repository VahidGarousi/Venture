package ir.vbile.app.venture.core.domain.use_cases

interface BaseUseCase<T> {
    suspend operator fun invoke(): T
}