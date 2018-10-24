package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.previousmatch


import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.MatchEvent
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg


class PreviousMatchPresenter(private val view: PreviousMatchView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPreviousMatch(eventPrevious: String) {
        view.showLoading()

        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getPreviousMatch(eventPrevious)),
                        MatchEvent::class.java
                )
            }
            view.showPriviousMatchList(data.await().events)
            view.hideLoading()
            Log.i("####", eventPrevious)
        }
    }
}