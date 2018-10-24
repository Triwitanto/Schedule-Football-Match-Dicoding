package footballschedule.trw.id.schedulefootballmatch.ui.activity.detailplayer

import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.BaseDetailPlayer
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayerPresenter(private val view: DetailPlayerView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailPlayer(idPlayer: String) {
        view.showLoading()

        async(contextPool.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getDetailPlayer(idPlayer)),
                        BaseDetailPlayer::class.java
                )
            }
            view.showDetailPalyer(data.await().player!!)
            view.hideLoading()
//            Log.i("####", eventNext)
        }
    }
}