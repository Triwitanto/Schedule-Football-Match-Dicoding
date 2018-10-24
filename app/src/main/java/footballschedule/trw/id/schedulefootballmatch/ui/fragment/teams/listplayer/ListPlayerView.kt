package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.listplayer

import footballschedule.trw.id.schedulefootballmatch.network.model.Player

interface ListPlayerView{

    fun showLoading()
    fun hideLoading()
    fun showListPalyer(data: List<Player>)
}
