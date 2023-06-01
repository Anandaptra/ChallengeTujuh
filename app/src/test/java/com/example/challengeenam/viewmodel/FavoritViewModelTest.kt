package com.example.challengeenam.viewmodel

import com.example.challengeenam.network.ResultApi
import com.example.challengeenam.room.FavDao
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock

class FavoritViewModelTest {
    @Mock
    lateinit var resultApi : FavDao

    @Before
    fun setup() {
        resultApi = mockk()
    }



}