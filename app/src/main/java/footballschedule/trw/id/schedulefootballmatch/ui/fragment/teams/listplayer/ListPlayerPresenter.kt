package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.listplayer

import android.util.Log
import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.BaseListPlayer
import footballschedule.trw.id.schedulefootballmatch.network.model.BaseListTeam
import footballschedule.trw.id.schedulefootballmatch.network.model.MatchEvent
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.nextmatch.NextMatchView
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class ListPlayerPresenter(private val view: ListPlayerView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getListPlayer(namaTeam: String) {
        view.showLoading()

        async(contextPool.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getListPalyer(namaTeam)),
                        BaseListPlayer::class.java
                )
            }
            view.showListPalyer(data.await().player!!)
            view.hideLoading()
//            Log.i("####", eventNext)
        }
    }
}
