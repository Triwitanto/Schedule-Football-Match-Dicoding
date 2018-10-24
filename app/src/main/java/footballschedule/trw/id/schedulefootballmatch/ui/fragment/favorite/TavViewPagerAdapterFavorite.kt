package footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite.matchfavorite.FavoriteMatchFragment
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite.teamfavorite.FavoriteTeamFragment


class TavViewPagerAdapterFavorite(fm:FragmentManager):FragmentPagerAdapter(fm){
    private val pages = listOf(
            FavoriteMatchFragment(),
            FavoriteTeamFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Match"
            1 -> "Team"
            else -> null
        }
    }
}
