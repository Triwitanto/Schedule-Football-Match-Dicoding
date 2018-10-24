package footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams

import android.util.Log
import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.BaseTeam
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailTeamsPresenter(private val view: DetailTeamsView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailTeams(nameTeam: String) {
        view.showLoading()
        Log.i("####", nameTeam)

        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getTeamDetail(nameTeam)),
                        BaseTeam::class.java
                )
            }

            view.getDetailTeam(data.await().teams)
        }
    }
}