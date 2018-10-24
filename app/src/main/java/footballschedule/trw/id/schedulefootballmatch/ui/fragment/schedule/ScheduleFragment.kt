package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule

import android.app.SearchManager
import android.content.Context
import android.os.Bundle

import android.support.v4.app.Fragment
import footballschedule.trw.id.schedulefootballmatch.R
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detail.DetailActivity
import footballschedule.trw.id.schedulefootballmatch.util.invisible
import footballschedule.trw.id.schedulefootballmatch.util.visible
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class ScheduleFragment : Fragment(), ScheduleView {

    companion object {
        fun getInstance() : ScheduleFragment = ScheduleFragment()
    }

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private var eventMatch: MutableList<Event> = mutableListOf()
    private var searchView: SearchView? = null
    private lateinit var adapterSearch: SearchMatchAdapter
    private lateinit var presenter: SchedulePresenter
    private lateinit var rvSearchMatch: RecyclerView
    private lateinit var queryTextListener: SearchView.OnQueryTextListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        viewPager = view.findViewById(R.id.pager) as ViewPager
        setupViewPager(viewPager)

        rvSearchMatch = view.findViewById(R.id.rv_search_match) as RecyclerView // Add this
        rvSearchMatch.layoutManager = LinearLayoutManager(activity)

        tabLayout = view.findViewById(R.id.tabs_main) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = SchedulePresenter(this, request, gson)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.actionSearch)
        val searchManager: SearchManager = ctx.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (searchItem != null){
            searchView = searchItem.actionView as SearchView
        }
        if (searchView !=null){
            searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    var searchMatch = query
                    searchMatch.toLowerCase()
                    searchMatch = searchMatch.replace(" ", "_")
                    adapterSearch = SearchMatchAdapter(eventMatch) {
                        activity!!.startActivity<DetailActivity>("idEvent" to "${it.idEvent}", "idTeamH" to "${it.idHomeTeam}", "idTeamA" to "${it.idAwayTeam}")
                    }
                    rvSearchMatch.adapter = adapterSearch
                    Log.i("matchEvent",searchMatch)
                    presenter.getSearchMatch(searchMatch)

                    return true
                }
            }
            searchView?.setOnQueryTextListener(queryTextListener)
        }
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                viewPager.visibility = View.VISIBLE
                tabLayout.visibility =View.VISIBLE
                rvSearchMatch.visibility= View.GONE

                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSearch -> {
                return false
            }
        }
        searchView?.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }

//     Add Fragments to Tabs
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = TabViewPageAdapter(childFragmentManager)

        viewPager.adapter = adapter
    }

    override fun showLoading() {
        progress_match.visible()
    }

    override fun hideLoading() {
        progress_match.invisible()    }

    override fun showSearchMatch(data: List<Event>) {
        viewPager.visibility = View.GONE
        tabLayout.visibility =View.GONE
        rvSearchMatch.visibility= View.VISIBLE

        eventMatch.clear()
        eventMatch.addAll(data)
        adapterSearch.notifyDataSetChanged()
    }

}
