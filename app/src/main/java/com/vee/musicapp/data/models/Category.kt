package com.vee.musicapp.data.models

class Category(val id:String="${System.currentTimeMillis()}", val name:String, val type:String, val movies:List<Movie>)
