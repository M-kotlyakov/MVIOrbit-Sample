package com.example.implementfailedscreen.domain

import com.example.implementfailedscreen.data.RepositoryImpl

class GetListUseCase(private val repository: RepositoryImpl) {

    fun geItem() = repository.getData()
}