package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.desteam

import footballschedule.trw.id.schedulefootballmatch.network.model.Team

interface DesTeamView{

    fun showLoading()
    fun hideLoading()
    fun getDescTeam(data: List<Team>)
}
