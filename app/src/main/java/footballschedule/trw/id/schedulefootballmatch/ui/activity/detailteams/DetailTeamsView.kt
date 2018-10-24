package footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams

import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import footballschedule.trw.id.schedulefootballmatch.network.model.Team

interface DetailTeamsView{

    fun showLoading()
    fun hideLoading()
    fun getDetailTeam(data: List<Team>)
}
