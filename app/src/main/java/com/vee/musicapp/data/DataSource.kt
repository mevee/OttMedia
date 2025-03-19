package com.vee.musicapp.data

import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie

class DataSource {

    fun getHomePageData(): List<Category> {
        val mArray = mutableListOf<Category>()
//            mArray.add(Category(type = "V", name = "Recently Launched1", movies = getMovieListVertical().toList()))
        mArray.add(
            Category(
                type = "H", name = "Live Tv Guide2", movies = getMovieListHorizontal().toList()
            )
        )
//        mArray.add(
//            Category(
//                type = "V",
//                name = "Recently Launched3",
//                movies = getNextMovieListVertical().toList()
//            )
//        )
        mArray.add(
            Category(
                type = "H", name = "Romantic4", movies = getMovieListHorizontal().toList()
            )
        )
//            mArray.add(Category(type = "V", name = "Thriller5", movies = getMovieListVertical().toList()))
        mArray.add(
            Category(
                type = "H", name = "Sports News6", movies = getMovieListHorizontal().toList()
            )
        )
//            mArray.add(Category(type = "V", name = "Recently Launched7", movies = getMovieListVertical().toList()))
        mArray.add(
            Category(
                type = "H", name = "Live Tv Guide8", movies = getMovieListHorizontal().toList()
            )
        )
//        mArray.add(
//            Category(
//                type = "V", name = "Recently Launched9", movies = getMovieListVertical().toList()
//            )
//        )
        mArray.add(
            Category(
                type = "H", name = "Romantic10", movies = getMovieListHorizontal().toList()
            )
        )
//            mArray.add(Category(type = "V", name = "Thriller11", movies = getMovieListVertical().toList()))
        mArray.add(
            Category(
                type = "H", name = "Sports News12", movies = getMovieListHorizontal().toList()
            )
        )
//            mArray.add(Category(type = "V", name = "Recently Launched13", movies = getMovieListVertical().toList()))
        mArray.add(
            Category(
                type = "H", name = "Live Tv Guide14", movies = getMovieListHorizontal().toList()
            )
        )
//            mArray.add(Category(type = "V", name = "Recently Launched15", movies = getMovieListVertical().toList()))
        mArray.add(
            Category(
                type = "H", name = "Romantic16", movies = getMovieListHorizontal().toList()
            )
        )
//            mArray.add(Category(type = "V", name = "Thriller17", movies = getMovieListVertical().toList()))
//            mArray.add(Category(type = "V", name = "Thriller18", movies = getMovieListVertical().toList()))
        mArray.add(
            Category(
                type = "H", name = "Sports News19", movies = getMovieListHorizontal().toList()
            )
        )
//            mArray.add(Category(type = "V", name = "Thriller20", movies = getMovieListVertical().toList()))
        return mArray
    }

    fun getNextMovieListVertical(): Array<Movie> {
        return arrayOf(
            Movie(
                name = "The Parenting",
                subTitle = "Boyfriends Josh and Rohan plan a weekend getaway to introduce their parents, only to discover that their rental is home to a 400-year-old poltergeist.",
                url = "https://artworks.thetvdb.com/banners/v4/movie/350369/posters/67ae5d7b8348f.jpg"
            ),
            Movie(
                name = "Toy Story",
                subTitle = "By Apple tv for Apple sales",
            ),
            Movie(
                name = "Hotel Transylvania Franchise",
                subTitle = "\n" + "\n" + "The (mis)adventures of monsters residing in Hotel Transylvania, a plaza hotel where monsters can relax and get away from humans due to fear of persecution.\n",
            ),
            Movie(
                name = "The Electric State",
                subTitle = "\n" + "\n" + "An orphaned teen hits the road with a mysterious robot to find her long-lost brother, teaming up with a smuggler and his wisecracking sidekick.\n",
                url = "https://artworks.thetvdb.com/banners/v4/movie/158077/posters/67b8f82b6c705.jpg"
            ),
        )
    }

    fun getMovieListVertical(): Array<Movie> {
        return arrayOf(
            Movie(
                name = "Snow Worlf",
                subTitle = "For details, next steps, and to ask questions about these issues, please visit the App Review page in App Store Connect",
                url = "https://image.tmdb.org/t/p/w1280/q0bCG4NX32iIEsRFZqRtuvzNCyZ.jpg"
            ),
            Movie(
                name = "Grey",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/lurEK87kukWNaHd0zYnsi3yzJrs.jpg"
            ),
            Movie(
                name = "Sniper",
                subTitle = "White lilly",
                url = "https://i.ytimg.com/vi/Lb2wwEx6DVw/maxresdefault.jpg"
            ),
            Movie(
                name = "Snow",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/iqQw58Cbgh5r7XRYUZpJPDPQD0m.jpg"
            ),
            Movie(
                name = "Snow Worlf",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/vGXptEdgZIhPg3cGlc7e8sNPC2e.jpg"
            ),
            Movie(
                name = "No Country for old man",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/wWba3TaojhK7NdycRhoQpsG0FaH.jpg"
            ),
            Movie(
                name = "Snow Worlf",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/2hTPkBiXoMmrdtYgHtB9WU5dg80.jpg"
            ),
            Movie(
                name = "No Country for old man",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/cGm2qnmXx9tFabmzEIkJZjCJdQd.jpg"
            ),
            Movie(
                name = "Snow Worlf",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/vGXptEdgZIhPg3cGlc7e8sNPC2e.jpg"
            ),
            Movie(
                name = "No Country for old man",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/wWba3TaojhK7NdycRhoQpsG0FaH.jpg"
            ),
            Movie(
                name = "Snow Worlf",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/2hTPkBiXoMmrdtYgHtB9WU5dg80.jpg"
            ),
            Movie(
                name = "No Country for old man",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/cGm2qnmXx9tFabmzEIkJZjCJdQd.jpg"
            ),
        )
    }

    fun getMovieListHorizontal(): Array<Movie> {
        return arrayOf(
            Movie(
                name = "Captain America",
                subTitle = "For details, next steps, and to ask questions about these issues, please visit the App Review page in App Store Connect",
                url = "https://m.media-amazon.com/images/I/71sNQzzr3aS.jpg"
            ),
            Movie(
                name = "Grey",
                subTitle = "White lilly",
                url = "https://rukminim2.flixcart.com/image/850/1000/kavefm80/poster/w/x/z/medium-the-marvel-avengers-wall-poster-for-room-with-gloss-original-imafsckr6vt3wjjv.jpeg?q=90&crop=false"
            ),
            Movie(
                name = "Sniper",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/lrCcovGRcuv8Z1v3ae1ZH5Ird05.jpg"
            ),
            Movie(
                name = "Snow",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/iqQw58Cbgh5r7XRYUZpJPDPQD0m.jpg"
            ),
            Movie(
                name = "Snow Worlf",
                subTitle = "White lilly",
            ),
            Movie(
                name = "No Country for old man",
                subTitle = "White lilly",
            ),
            Movie(
                name = "Snow Worlf",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/2hTPkBiXoMmrdtYgHtB9WU5dg80.jpg"
            ),
            Movie(
                name = "No Country for old man",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/cGm2qnmXx9tFabmzEIkJZjCJdQd.jpg"
            ),
            Movie(
                name = "Snow Worlf",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/vGXptEdgZIhPg3cGlc7e8sNPC2e.jpg"
            ),
            Movie(
                name = "No Country for old man",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/wWba3TaojhK7NdycRhoQpsG0FaH.jpg"
            ),
            Movie(
                name = "Snow Worlf",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/2hTPkBiXoMmrdtYgHtB9WU5dg80.jpg"
            ),
            Movie(
                name = "No Country for old man",
                subTitle = "White lilly",
                url = "https://www.themoviedb.org/t/p/w1280/cGm2qnmXx9tFabmzEIkJZjCJdQd.jpg"
            ),
        )
    }

}