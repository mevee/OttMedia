package com.vee.musicapp.data.models

import java.util.UUID

class Movie(
    val id: String = "${UUID.randomUUID()}",
     val name:String, val subTitle:String, val url :String?=null)
