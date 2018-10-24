package footballschedule.trw.id.schedulefootballmatch.ui.activity.detail

import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import footballschedule.trw.id.schedulefootballmatch.network.model.Team

interface DetailView{
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: List<Event>)
    fun getHomeBadge(data: List<Team>)
    fun getAwayBadge(data: List<Team>)
}
