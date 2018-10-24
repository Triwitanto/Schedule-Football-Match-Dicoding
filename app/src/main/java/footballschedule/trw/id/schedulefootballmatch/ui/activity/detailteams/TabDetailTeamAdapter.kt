package footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.nextmatch.NextMatchFragment
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.previousmatch.PreviousMatchFragment
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.desteam.DesTeamFragment
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.listplayer.ListPlayerFragment
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.listplayer.ListPlayerPresenter

class TabDetailTeamAdapter(private val namaTeam: String, fm: FragmentManager): FragmentPagerAdapter(fm){

//    private val pages = listOf(
//            DesTeamFragment(),
//            ListPlayerFragment()
//    )

    // menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                newInstance(namaTeam)
            }
            1 -> {
                newInstancePlayer(namaTeam)
            }
            else -> {
                return ListPlayerFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    // judul untuk tabs
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Desc"
            1 -> "Player"
            else -> null
        }
    }

    companion object {
        const val KEY_TEAM = "KEY_TEAM"
        const val KEY_TEAM_2 = "KEY_TEAM_2"
        fun newInstance(id: String): DesTeamFragment {
            val bindData = Bundle()
            bindData.putString(KEY_TEAM, id)

            val desciptionTeamFragment = DesTeamFragment()
            desciptionTeamFragment.arguments = bindData
            return desciptionTeamFragment
        }

        fun newInstancePlayer(id: String): ListPlayerFragment {
            val bindData = Bundle()
            bindData.putString(KEY_TEAM_2, id)

            val playerTeamFragment = ListPlayerFragment()
            playerTeamFragment.arguments = bindData
            return playerTeamFragment
        }
    }
}
