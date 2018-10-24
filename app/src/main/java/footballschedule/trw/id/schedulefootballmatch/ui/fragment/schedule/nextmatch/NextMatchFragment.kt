package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.nextmatch

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
import footballschedule.trw.id.schedulefootballmatch.R.array.league
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detail.DetailActivity
import footballschedule.trw.id.schedulefootballmatch.util.invisible
import footballschedule.trw.id.schedulefootballmatch.util.visible
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class NextMatchFragment : Fragment(), NextMatchView {


    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: NextMatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var leagueName: String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_next.adapter = spinnerAdapter

        adapter = NextMatchAdapter(event) {
            ctx.startActivity<DetailActivity>("idEvent" to "${it.idEvent}", "idTeamH" to "${it.idHomeTeam}", "idTeamA" to "${it.idAwayTeam}")
        }
        listEvent.adapter = adapter


        val request = ApiRepository()
        val gson = Gson()
        presenter = NextMatchPresenter(this, request, gson)
        spinner_next.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                leagueName = when {
                    spinner_next.selectedItem.toString() == "English Premier League" -> "4328"
                    spinner_next.selectedItem.toString() == "English League Championship" -> "4329"
                    spinner_next.selectedItem.toString() == "German Bundesliga" -> "4331"
                    spinner_next.selectedItem.toString() == "Italian Serie A" -> "4332"
                    spinner_next.selectedItem.toString() == "French Ligue 1" -> "4334"
                    spinner_next.selectedItem.toString() == "Spanish La Liga" -> "4335"
                    else -> "4328"
                }
                presenter.getNextMatch(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_next_match, container, false)
        listEvent = rootView.findViewById(R.id.rv_nextmatch) as RecyclerView // Add this
        listEvent.layoutManager = LinearLayoutManager(ctx)
        return rootView
    }

    override fun showLoading() {
        progress_next.visible()
    }

    override fun hideLoading() {
        progress_next.invisible()
    }

    override fun showNextMatchList(data: List<Event>) {
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
