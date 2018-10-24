package footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite.teamfavorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.db.FavoriteTeams
import footballschedule.trw.id.schedulefootballmatch.network.db.database
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detailplayer.DetailPlayerActivity
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams.DetailTeamsActivity
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite.matchfavorite.FavoriteMatchFragment
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteTeamFragment : Fragment() {

    private var favorites: MutableList<FavoriteTeams> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var listEvent: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamAdapter(favorites) {
            ctx.startActivity<DetailTeamsActivity>("idTeam" to "${it.idTeamDetail}","namaTeam" to "${it.namaTeamDetail}","imgTeam" to "${it.imgTeamDetail}")
        }

        listEvent.adapter = adapter
        showFavorite()
        swipe_refresh_team.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            swipe_refresh_team.isRefreshing = false
            val result = select(FavoriteTeams.TABLE_FAVORITE2)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        listEvent = rootView.findViewById(R.id.rv_favorite_team) as RecyclerView // Add this
        listEvent.layoutManager = LinearLayoutManager(ctx)
        return rootView
    }

}
