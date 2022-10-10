package com.kenchen.capstonenewsapp
//
//import android.content.Context
//import android.util.Log
//import androidx.core.content.edit
//import androidx.preference.PreferenceManager
//import com.kenchen.capstonenewsapp.model.Article
//import com.kenchen.capstonenewsapp.model.Source
//
//interface NewsService {
//}
//
//class InMemoryNewsServiceImp(private val context: Context) : NewsService {
//    fun getDummyNews() = newsList
//
//    fun saveNews() {
//        val sharePrefs = PreferenceManager.getDefaultSharedPreferences(context)
//        sharePrefs.edit {
//            newsList.forEachIndexed(){
//                index, article -> putString(index.toString(), article.toString())
//            }
//        }
//    }
//
//    fun show() {
//        Log.i("test",newsList.joinToString())
//    }
//
//    // dummy news data
//    private val newsList = arrayListOf<Article?>(
//        Article(
//            source = Source("bbc-news", "BBC News"),
//            author = "BBC News",
//            title = "Pakistan floods: Thousands told to evacuate as rivers rise",
//            description = "Monsoon-induced floods continue to cause destruction in what is said to be one of the country's worst disasters.",
//            url = "http://www.bbc.co.uk/news/world-asia-62699886",
//            urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/1058C/production/_126465966_mediaitem126465965.jpg",
//            publishedAt = "2022-08-27T13:07:17.6026971Z",
//            content = "Media caption, Watch: Millions affected by heavy flooding in Pakistan\r\nThousands of people who live in areas under threat of flooding in some parts of Pakistan have been told to evacuate. \r\nAid agenc… [+2491 chars]",
//        ),
//        Article(
//            source = Source(name = "Saarbruecker-zeitung.de"),
//            author = "Eliran Kendi",
//            title = "Tankrabatt: In Frankreich können deutsche Autofahrer bis zu 30 Euro sparen - Saarbrücker Zeitung",
//            description = "Zum 1. September läuft der Tankrabatt in Deutschland aus. Gleichzeitig macht Frankreich Benzin und Diesel deutlich günstiger. So viel können Autofahrer beim Tanken jenseits der Grenze sparen.",
//            url = "https://www.saarbruecker-zeitung.de/saarland/blickzumnachbarn/frankreich/tankrabatt-in-frankreich-bis-zu-30-euro-koennen-deutsche-autofahrer-sparen_aid-75402217",
//            urlToImage = "https://www.saarbruecker-zeitung.de/imgs/03/1/4/0/5/7/7/1/6/9/tok_2e2b5b98de15f1f6139c7917b4b79312/w1200_h630_x617_y579_sn-Preise31081208D.jpg_0581_GRC42EBG2.1-6bc2b130c2604e4b.jpg",
//            publishedAt = "2022-08-27T21:05:01Z",
//            content = "27. August 2022 um 10:58 Uhr\\r\\nSpritpreise ab September\\r\\n :\\r\\n Tankrabatt in Frankreich ADAC-Experte verrät: So viel können Tanktouristen sparen\\r\\nSaarbrückenZum 1. September läuft der Tankrabatt in Deu… [+3917 chars]",
//        ),
//        Article(
//            source = Source("the-washington-post", "The Washington Post"),
//            author = "Annie Linskey, Michael Scherer",
//            title = "Democrats improve outlook on House amid better political environment - The Washington Post",
//            description = "While Democrats acknowledge they still face major hurdles, there has been an unmistakable mood shift, according to interviews with candidates, strategists and officials.",
//            url = "https://www.washingtonpost.com/politics/2022/08/27/democrats-republicans-house-midterms/",
//            urlToImage = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/5WVEWGQCF4I63C7LFNHEQGYVAA.jpg&w=1440",
//            publishedAt = "2022-08-28T00:23:30Z",
//            content = "Comment on this story\\r\\nDemocrats are voicing growing confidence about limiting losses in the House and potentially even salvaging their majority in the midterm elections, with candidates and allied g… [+14030 chars]",
//        ),
//        Article(
//            source = Source(name = "Daytona Beach News-Journal"),
//            author = "Ken Willis, Daytona Beach News-Journal Online",
//            title = "Live updates from Coke Zero 400 NASCAR race, results at Daytona - Daytona Beach News-Journal",
//            description = "Daytona International Speedway is ready to host the Coke Zero Sugar 400 NASCAR Cup race Saturday night, August 27, 2022. Follow for live updates.",
//            url = "https://www.news-journalonline.com/story/sports/nascar/2022/08/27/live-updates-coke-zero-400-daytona-nascar-race-results-weather-forecast/7904034001/",
//            urlToImage = "https://www.gannett-cdn.com/presto/2022/08/27/NDNJ/43d4cbd7-3748-4d65-bfdf-4461dda2e413-Coke_400_puddles.jpg?auto=webp&crop=6414,3608,x0,y270&format=pjpg&width=1200",
//            publishedAt = "2022-08-28T00:11:15Z",
//            content = "It's always the last few laps, and the inevitable crashes at Daytona International Speedway, that help determine who gets the checkered flag.\\r\\nBut you have to race all 160 laps of the Coke Zero Sugar… [+4635 chars]",
//        ),
//        Article(
//            source = Source("cnn", "CNN"),
//            author = null,
////            author = "Eyad Kourdi, CNN",
//            title = "At least 23 people dead, 140 injured in violent clashes between rival militias in Libyan capital of Tripoli - CNN",
//            description = "At least 23 people have died and 140 been injured in violent clashes between rival Libyan militias across the country's capital of Tripoli, the Libyan Ministry of Health said Saturday.",
//            url = "https://www.cnn.com/2022/08/27/world/violent-clashes-rival-militias-libya/index.html",
//            urlToImage = "\"https://cdn.cnn.com/cnnnext/dam/assets/220827173345-01-tripoli-08272022-super-tease.jpg",
//            publishedAt = "2022-08-27T23:38:00Z",
//        ),
//        null,
//    )
//}
