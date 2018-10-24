package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams

import footballschedule.trw.id.schedulefootballmatch.network.model.Team

interface TeamFragmentView{
    fun showLoading()
    fun hideLoading()
    fun showListTeams(data: List<Team>)
    fun showSearchTeams(data: List<Team>)
}
