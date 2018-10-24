package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams

import android.util.Log
import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.BaseTeam
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class TeamFragmentPresenter(private val view: TeamFragmentView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamsList(nameLeague: String) {
        view.showLoading()
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getTeamList(nameLeague)),
                        BaseTeam::class.java
                )
            }
            view.showListTeams(data.await().teams)
            view.hideLoading()
        }
    }

    fun getSearchTeam(namaTeam: String) {
        view.showLoading()
        Log.i("triw",namaTeam)
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getSearchTeam(namaTeam)),
                        BaseTeam::class.java
                )
            }
            view.showSearchTeams(data.await().teams)
            view.hideLoading()
        }

    }

}
