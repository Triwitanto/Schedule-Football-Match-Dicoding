package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.google.gson.Gson

import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.model.Team
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams.DetailTeamsActivity
import footballschedule.trw.id.schedulefootballmatch.util.invisible
import footballschedule.trw.id.schedulefootballmatch.util.visible
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx


class TeamsFragment : Fragment(), TeamFragmentView {

    companion object {
        fun getInstance(): TeamsFragment = TeamsFragment()
    }

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamFragmentPresenter
    private lateinit var adapter: AllTeamsListAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var leagueName: String

    private var searchTeams: MutableList<Team> = mutableListOf()
    private var searchView: SearchView? = null
    private lateinit var adapterSearch: SearchTeamsListAdapter
    private lateinit var rvSearch: RecyclerView
    private lateinit var queryTextListener: SearchView.OnQueryTextListener

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_allteams.adapter = spinnerAdapter

        adapter = AllTeamsListAdapter(teams) {
            ctx.startActivity<DetailTeamsActivity>("idTeam" to "${it.idTeam}", "namaTeam" to "${it.strTeam}", "imgTeam" to "${it.strTeamBadge}")
        }
        listEvent.adapter = adapter


        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamFragmentPresenter(this, request, gson)
        spinner_allteams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_allteams.selectedItem.toString()
                Log.i("coba2", leagueName)
                presenter.getTeamsList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_teams, container, false)
        listEvent = rootView.findViewById(R.id.rv_teams_list) as RecyclerView // Add this
        listEvent.layoutManager = LinearLayoutManager(ctx)
        rvSearch = rootView.findViewById(R.id.rv_search) as RecyclerView // Add this
        rvSearch.layoutManager = LinearLayoutManager(activity)
        return rootView
    }

    override fun showLoading() {
        progress_allTeams.visible()
    }

    override fun hideLoading() {
        progress_allTeams.invisible()
    }

    override fun showListTeams(data: List<Team>) {
        rvSearch.visibility= View.GONE
        listEvent.visibility= View.VISIBLE
        spinner_allteams.visibility= View.VISIBLE

        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showSearchTeams(data: List<Team>) {
        rvSearch.visibility= View.VISIBLE
        listEvent.visibility= View.GONE
        spinner_allteams.visibility= View.GONE

        searchTeams.clear()
        searchTeams.addAll(data)
        adapterSearch.notifyDataSetChanged()    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.actionSearch)
        val searchManager:SearchManager = ctx?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
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
                    var searchTeam = query
                    searchTeam.toLowerCase()
                    searchTeam = searchTeam.replace(" ", "_")
                    adapterSearch = SearchTeamsListAdapter(searchTeams) {
                        activity!!.startActivity<DetailTeamsActivity>("idTeam" to "${it.idTeam}","namaTeam" to "${it.strTeam}","imgTeam" to "${it.strTeamBadge}")
                    }
                    rvSearch.adapter = adapterSearch
                    Log.i("triw",searchTeam)
                    presenter.getSearchTeam(searchTeam)
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
                listEvent.visibility = View.VISIBLE
                spinner_allteams.visibility =View.VISIBLE
                rvSearch.visibility= View.GONE

                return true
            }
        });
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
}




//    override fun showListTeams(data: List<Team>) {
//        Log.i("coba3","sukses")
//        teams.clear()
//        teams.addAll(data)
//        adapter.notifyDataSetChanged()
//    }

//    override fun showSearchTeams(data: List<Team>) {
//        Log.i("isidata",data.size.toString())
//
//        rvSearch.visibility= View.VISIBLE
//        listEvent.visibility= View.GONE
//        spinner_allteams.visibility= View.GONE
//
//        teams.clear()
//        teams.addAll(data)
//        adapterSearch.notifyDataSetChanged()
//
//    }


