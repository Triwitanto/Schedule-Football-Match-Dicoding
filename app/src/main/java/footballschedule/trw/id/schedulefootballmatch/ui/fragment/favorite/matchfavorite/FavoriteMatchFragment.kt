package footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite.matchfavorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.db.Favorite
import footballschedule.trw.id.schedulefootballmatch.network.db.database
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment() {
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var listEvent: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchAdapter(favorites) {
            ctx.startActivity<DetailActivity>("idEvent" to "${it.idEvent}", "idTeamH" to "${it.idTeamH}", "idTeamA" to "${it.idTeamA}")
        }

        listEvent.adapter = adapter
        showFavorite()
        swipe_refresh_layout.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            swipe_refresh_layout.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        listEvent = rootView.findViewById(R.id.rv_favorite) as RecyclerView // Add this
        listEvent.layoutManager = LinearLayoutManager(ctx)
        return rootView
    }
}
