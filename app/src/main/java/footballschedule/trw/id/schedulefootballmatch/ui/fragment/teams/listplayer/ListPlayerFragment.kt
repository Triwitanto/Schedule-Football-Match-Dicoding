package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.listplayer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.gson.Gson

import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.model.Player
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detail.DetailActivity
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detailplayer.DetailPlayerActivity
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams.TabDetailTeamAdapter.Companion.KEY_TEAM_2
import footballschedule.trw.id.schedulefootballmatch.util.invisible
import footballschedule.trw.id.schedulefootballmatch.util.visible
import kotlinx.android.synthetic.main.fragment_list_player.*
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx


class ListPlayerFragment : Fragment(), ListPlayerView {


    private var player: MutableList<Player> = mutableListOf()
    private lateinit var presenter: ListPlayerPresenter
    private lateinit var adapter: ListPalyerAdapter
    private lateinit var listPlayer: RecyclerView
    private lateinit var leagueName: String
    private lateinit var namaTeam: String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val spinnerItems = resources.getStringArray(R.array.league)
//        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
//        spinner_next.adapter = spinnerAdapter

        adapter = ListPalyerAdapter(player) {
            ctx.startActivity<DetailPlayerActivity>("idPlayer" to "${it.idPlayer}", "namaPlayer" to "${it.strPlayer}")
        }
        listPlayer.adapter = adapter


        val bindData = arguments
        namaTeam = bindData?.getString(KEY_TEAM_2) ?: "namaTeam"

        val request = ApiRepository()
        val gson = Gson()
        presenter = ListPlayerPresenter(this, request, gson)
        presenter.getListPlayer(namaTeam)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list_player, container, false)
        listPlayer = rootView.findViewById(R.id.rv_list_player) as RecyclerView // Add this
        listPlayer.layoutManager = LinearLayoutManager(ctx)
        return rootView
    }

    override fun showLoading() {
        progress_list_player.visible()
    }

    override fun hideLoading() {
        progress_list_player.invisible()
    }

    override fun showListPalyer(data: List<Player>) {
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()    }


}
