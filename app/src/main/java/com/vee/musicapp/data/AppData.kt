package com.vee.musicapp.data

import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie

class AppData {
    companion object{
        fun getHomePageData():List<Category>{
            val mArray = mutableListOf<Category>()
//            mArray.add(Category(type = "V", name = "Recently Launched1", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Live Tv Guide2", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Recently Launched3", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Romantic4", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Thriller5", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Sports News6", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Recently Launched7", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Live Tv Guide8", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Recently Launched9", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Romantic10", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Thriller11", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Sports News12", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Recently Launched13", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Live Tv Guide14", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Recently Launched15", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Romantic16", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Thriller17", movies = getMovieListVertical().toList()))
//            mArray.add(Category(type = "V", name = "Thriller18", movies = getMovieListVertical().toList()))
            mArray.add(Category(type = "H", name = "Sports News19", movies = getMovieListHorizontal().toList()))
//            mArray.add(Category(type = "V", name = "Thriller20", movies = getMovieListVertical().toList()))
            return mArray
        }

         fun getMovieListVertical(): Array<Movie> {
            return arrayOf(
                Movie(
                    name = "Snow Worlf",
                    title = "For details, next steps, and to ask questions about these issues, please visit the App Review page in App Store Connect",
                    url = "https://image.tmdb.org/t/p/w1280/q0bCG4NX32iIEsRFZqRtuvzNCyZ.jpg"
                ),
                Movie(
                    name = "Grey",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/lurEK87kukWNaHd0zYnsi3yzJrs.jpg"
                ),
                Movie(
                    name = "Sniper",
                    title = "White lilly",
                    url = "https://i.ytimg.com/vi/Lb2wwEx6DVw/maxresdefault.jpg"
                ),
                Movie(
                    name = "Snow",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/iqQw58Cbgh5r7XRYUZpJPDPQD0m.jpg"
                ),
                Movie(
                    name = "Snow Worlf",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/vGXptEdgZIhPg3cGlc7e8sNPC2e.jpg"
                ),
                Movie(
                    name = "No Country for old man",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/wWba3TaojhK7NdycRhoQpsG0FaH.jpg"
                ), Movie(
                    name = "Snow Worlf",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/2hTPkBiXoMmrdtYgHtB9WU5dg80.jpg"
                ),
                Movie(
                    name = "No Country for old man",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/cGm2qnmXx9tFabmzEIkJZjCJdQd.jpg"
                ),
                Movie(
                    name = "Snow Worlf",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/vGXptEdgZIhPg3cGlc7e8sNPC2e.jpg"
                ),
                Movie(
                    name = "No Country for old man",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/wWba3TaojhK7NdycRhoQpsG0FaH.jpg"
                ), Movie(
                    name = "Snow Worlf",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/2hTPkBiXoMmrdtYgHtB9WU5dg80.jpg"
                ),
                Movie(
                    name = "No Country for old man",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/cGm2qnmXx9tFabmzEIkJZjCJdQd.jpg"
                ),
            )
        }

        fun getMovieListHorizontal(): Array<Movie> {
            return arrayOf(
                Movie(
                    name = "Captain America",
                    title = "For details, next steps, and to ask questions about these issues, please visit the App Review page in App Store Connect",
                    url = "https://m.media-amazon.com/images/I/71sNQzzr3aS.jpg"
                ),
                Movie(
                    name = "Grey",
                    title = "White lilly",
                    url = "https://rukminim2.flixcart.com/image/850/1000/kavefm80/poster/w/x/z/medium-the-marvel-avengers-wall-poster-for-room-with-gloss-original-imafsckr6vt3wjjv.jpeg?q=90&crop=false"
                ),
                Movie(
                    name = "Sniper",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/lrCcovGRcuv8Z1v3ae1ZH5Ird05.jpg"
                ),
                Movie(
                    name = "Snow",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/iqQw58Cbgh5r7XRYUZpJPDPQD0m.jpg"
                ),
                Movie(
                    name = "Snow Worlf",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/vGXptEdgZIhPg3cGlc7e8sNPC2e.jpg"
                ),
                Movie(
                    name = "No Country for old man",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/wWba3TaojhK7NdycRhoQpsG0FaH.jpg"
                ), Movie(
                    name = "Snow Worlf",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/2hTPkBiXoMmrdtYgHtB9WU5dg80.jpg"
                ),
                Movie(
                    name = "No Country for old man",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/cGm2qnmXx9tFabmzEIkJZjCJdQd.jpg"
                ), Movie(
                    name = "Snow Worlf",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/vGXptEdgZIhPg3cGlc7e8sNPC2e.jpg"
                ),
                Movie(
                    name = "No Country for old man",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/wWba3TaojhK7NdycRhoQpsG0FaH.jpg"
                ), Movie(
                    name = "Snow Worlf",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/2hTPkBiXoMmrdtYgHtB9WU5dg80.jpg"
                ),
                Movie(
                    name = "No Country for old man",
                    title = "White lilly",
                    url = "https://www.themoviedb.org/t/p/w1280/cGm2qnmXx9tFabmzEIkJZjCJdQd.jpg"
                ),
            )
        }
    }


}