package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.previousmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson

import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detail.DetailActivity
import footballschedule.trw.id.schedulefootballmatch.util.invisible
import footballschedule.trw.id.schedulefootballmatch.util.visible
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx


class PreviousMatchFragment : Fragment(), PreviousMatchView {

    companion object {

        fun newInstance(): PreviousMatchFragment {
            return PreviousMatchFragment()
        }
    }

    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: PreviousMatchPresenter
    private lateinit var adapter: PreviousMatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var leagueName: String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_previous.adapter = spinnerAdapter

        adapter = PreviousMatchAdapter(event) {
            ctx.startActivity<DetailActivity>("idEvent" to "${it.idEvent}", "idTeamH" to "${it.idHomeTeam}", "idTeamA" to "${it.idAwayTeam}")
        }
        listEvent.adapter = adapter


        val request = ApiRepository()
        val gson = Gson()
        presenter = PreviousMatchPresenter(this, request, gson)
        spinner_previous.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                if (spinner_previous.selectedItem.toString().equals("English Premier League")){
                    leagueName = "4328"
                }else if (spinner_previous.selectedItem.toString().equals("English League Championship")){
                    leagueName = "4329"
                }else if (spinner_previous.selectedItem.toString().equals("German Bundesliga")){
                    leagueName = "4331"
                }else if (spinner_previous.selectedItem.toString().equals("Italian Serie A")){
                    leagueName = "4332"
                }else if (spinner_previous.selectedItem.toString().equals("French Ligue 1")){
                    leagueName = "4334"
                }else if (spinner_previous.selectedItem.toString().equals("Spanish La Liga")){
                    leagueName = "4335"
                }else{
                    leagueName = "4328"
                }
                presenter.getPreviousMatch(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_previous_match, container, false)
        listEvent = rootView.findViewById(R.id.rvPreviousmatch) as RecyclerView // Add this
        listEvent.layoutManager = LinearLayoutManager(ctx)
        return rootView


    }

    override fun showLoading() {
        progress_bar_up_coming.visible()
    }

    override fun hideLoading() {
        progress_bar_up_coming.invisible()
    }

    override fun showPriviousMatchList(data: List<Event>) {
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()

    }

}