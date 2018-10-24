package footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import footballschedule.trw.id.schedulefootballmatch.R

class FavoriteAllFragment : Fragment() {

    companion object {
        fun getInstance() : FavoriteAllFragment = FavoriteAllFragment()
    }
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_all, container, false)

        viewPager = view.findViewById(R.id.pager_favorite) as ViewPager
        setupViewPager(viewPager!!)

        tabLayout = view.findViewById(R.id.tabs_main_favorite) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)

        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = TavViewPagerAdapterFavorite(childFragmentManager)
        viewPager.adapter = adapter
    }
}
