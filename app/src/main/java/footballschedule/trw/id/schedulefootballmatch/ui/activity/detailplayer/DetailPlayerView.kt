package footballschedule.trw.id.schedulefootballmatch.ui.activity.detailplayer

import footballschedule.trw.id.schedulefootballmatch.network.model.Player

interface DetailPlayerView{

    fun showLoading()
    fun hideLoading()
    fun showDetailPalyer(data: List<Player>)
}
