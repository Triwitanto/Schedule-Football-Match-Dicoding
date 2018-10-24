package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.nextmatch

import android.util.Log
import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.MatchEvent
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val view: NextMatchView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getNextMatch(eventNext: String) {
        view.showLoading()

        async(contextPool.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getNextMatch(eventNext)),
                        MatchEvent::class.java
                )
            }
            view.showNextMatchList(data.await().events)
            view.hideLoading()
            Log.i("####", eventNext)
        }
    }
}