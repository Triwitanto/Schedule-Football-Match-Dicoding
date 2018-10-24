package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule

import android.util.Log
import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.BaseEvent
import footballschedule.trw.id.schedulefootballmatch.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class SchedulePresenter(private val view: ScheduleView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson,
                        private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getSearchMatch(namaTeamVs: String) {
        view.showLoading()
        Log.i("######", namaTeamVs)

        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiUrl.getSearchMatch(namaTeamVs)),
                        BaseEvent::class.java
                )
            }

            view.showSearchMatch(data.await().event!!)
            view.hideLoading()
        }
    }
}
