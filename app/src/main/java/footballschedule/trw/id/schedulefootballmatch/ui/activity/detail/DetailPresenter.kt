package footballschedule.trw.id.schedulefootballmatch.ui.activity.detail

import android.util.Log
import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.BaseTeam
import footballschedule.trw.id.schedulefootballmatch.network.model.DetailEvent
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg
import kotlinx.coroutines.experimental.async

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val contextPool: CoroutineContextProvider=CoroutineContextProvider()){

    fun getDetailMatch(idEvent: String){
        view.showLoading()
        Log.i("####", idEvent)

        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getDetail(idEvent)),
                        DetailEvent::class.java
                )
            }

            view.showDetailMatch(data.await().events)
        }
    }

    fun getImageHome(idTeam: String){
        Log.i("####", idTeam)

        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getTeam(idTeam)),
                        BaseTeam::class.java
                )
            }

            view.getHomeBadge(data.await().teams)
        }
    }
    fun getImageAway(idTeam: String){
        Log.i("####", idTeam)

        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getTeam(idTeam)),
                        BaseTeam::class.java
                )
            }
            view.getAwayBadge(data.await().teams)
        }
        view.hideLoading()
    }
}
