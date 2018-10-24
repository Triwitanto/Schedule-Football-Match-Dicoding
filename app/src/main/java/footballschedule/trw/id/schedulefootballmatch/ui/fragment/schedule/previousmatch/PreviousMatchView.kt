package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.previousmatch

import footballschedule.trw.id.schedulefootballmatch.network.model.Event

interface PreviousMatchView{
    fun showLoading()
    fun hideLoading()
    fun showPriviousMatchList(data: List<Event>)
}
