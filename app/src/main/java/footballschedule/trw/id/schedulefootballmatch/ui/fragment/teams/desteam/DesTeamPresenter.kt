package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.desteam

import android.util.Log
import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.BaseTeam
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams.DetailTeamsView
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DesTeamPresenter(private val view: DesTeamView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson,
                       private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {


    fun getDesTeam(nameTeam: String) {
        Log.i("####", nameTeam)

        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getTeamDetail(nameTeam)),
                        BaseTeam::class.java
                )
            }

            view.getDescTeam(data.await().teams)
        }
    }

}
