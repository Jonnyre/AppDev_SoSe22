package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameTypeRepository
import com.hfu.bierolympiade.domain.model.GameType
import com.hfu.bierolympiade.domain.model.GameTypeId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddGameTypeUseCase @Inject constructor(
    private val gameTypeRepository: GameTypeRepository
){
    suspend operator fun invoke(name: String, icon: String, rules: String): Boolean = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newGameType = GameType.create(
            GameTypeId(uniqueID),
            name = name,
            icon = icon,
            rules = rules
        )
        if(newGameType != null)  {
            gameTypeRepository.addGameType(newGameType)
            return@withContext true
        }
        return@withContext false
    }
}