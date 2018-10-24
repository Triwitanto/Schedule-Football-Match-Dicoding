package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.nextmatch

import footballschedule.trw.id.schedulefootballmatch.network.model.Event

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<Event>)
}
