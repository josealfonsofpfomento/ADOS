package com.josealfonsomora.ados.domain

import com.josealfonsomora.ados.data.repositories.BusesRepository
import com.josealfonsomora.ados.data.room.toEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAutobusUseCase @Inject constructor(
    private val busesRepository: BusesRepository,
) {
    suspend operator fun invoke(autobus: Autobus) {
        withContext(IO) {
            busesRepository.deleteBus(autobus.toEntity())
        }
    }
}
