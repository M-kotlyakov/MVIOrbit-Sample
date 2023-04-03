package com.example.implementfailedscreen.data

import com.example.implementfailedscreen.data.model.Item
import com.example.implementfailedscreen.domain.Repository

class RepositoryImpl : Repository {

    override fun getData() = Item(status = "ok", digit = 1)
}