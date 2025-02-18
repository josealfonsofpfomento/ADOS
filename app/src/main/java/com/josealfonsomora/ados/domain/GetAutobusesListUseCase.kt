package com.josealfonsomora.ados.domain

import com.josealfonsomora.ados.data.repositories.BusesRepository
import com.josealfonsomora.ados.data.room.toDomain
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAutobusesListUseCase @Inject constructor(
    private val busesRepository: BusesRepository,
) {
    suspend operator fun invoke(): Flow<List<Autobus>> = withContext(IO) {
        busesRepository.getBuses().map { list -> list.map { it.toDomain() } }
    }

}