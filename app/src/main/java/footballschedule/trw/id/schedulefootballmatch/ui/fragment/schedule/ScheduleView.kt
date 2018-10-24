package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule

import footballschedule.trw.id.schedulefootballmatch.network.model.Event

interface ScheduleView{
    fun showLoading()
    fun hideLoading()
    fun showSearchMatch(data: List<Event>)
}
