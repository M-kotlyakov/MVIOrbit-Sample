package com.example.implementfailedscreen.domain

import com.example.implementfailedscreen.data.model.Item

interface  Repository {

    fun getData(): Item
}