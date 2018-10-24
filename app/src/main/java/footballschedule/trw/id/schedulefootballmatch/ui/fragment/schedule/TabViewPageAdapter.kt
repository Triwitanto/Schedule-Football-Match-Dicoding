package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.nextmatch.NextMatchFragment
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.previousmatch.PreviousMatchFragment

class TabViewPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
            PreviousMatchFragment(),
            NextMatchFragment()
            )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Previous"
            1 -> "Next"
            else -> null
        }
    }
}