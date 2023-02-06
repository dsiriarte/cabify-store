package com.davidsantiagoiriarte.domain.usecases

interface UseCase<P, R> {
    suspend fun execute(p: P): R
}
