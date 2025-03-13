package com.vee.musicapp.data.repo

import com.vee.musicapp.data.AppData
import com.vee.musicapp.data.models.Category

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieRepositoryTest {
    var data : List<Category>? = null

    @Before
    fun setUp(){
        println("Start of test")
        data = AppData.getHomePageData()
   }

    @After
    fun tearDown(){
     println("End of test")
        data = null
    }

    @Test
    fun getHomeData_is_isNullOrEmpty_exp_True() {
        assertEquals(false,data.isNullOrEmpty())
    }


    @Test
    fun getHomeData_is_isNullOrEmpty_exp_False() {
        assertEquals(false,data?.isNotEmpty())
    }

    @Test
    fun getHomeData() {
        val response =  "f"
        assertEquals("Error",response)
    }
}