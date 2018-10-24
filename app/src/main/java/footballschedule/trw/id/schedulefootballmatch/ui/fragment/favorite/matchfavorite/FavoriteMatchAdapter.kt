package footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite.matchfavorite

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.db.Favorite
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class FavoriteMatchAdapter(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_favorite, parent, false)
        return FavoriteMatchViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size
}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val strHomeTeam: TextView = view.find(R.id.tv_team1)
    private val strAwayTeam: TextView = view.find(R.id.tv_team2)
    private val intHomeScore: TextView = view.find(R.id.tv_score_team1F)
    private val intAwayScore: TextView = view.find(R.id.tv_score_team2F)
    private val dateEvent: TextView = view.find(R.id.tv_date_match)
    private val tvVs: TextView = view.find(R.id.tv_vs)

    fun bindItem(event: Favorite, listener: (Favorite) -> Unit) {
        val df =  SimpleDateFormat("HH:mm", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date = df.parse(event.timeEvent)
        df.timeZone = TimeZone.getDefault()
        val time = df.format(date)
//        Log.e("###", data[0].strTime)
        if (event.intHomeScore.equals("null")){
            strHomeTeam.text = event.strHomeTeam
            strAwayTeam.text = event.strAwayTeam
            intHomeScore.text = ""
            intAwayScore.text = ""
            Log.i("####", event.strHomeTeam)
            dateEvent.text = event.dateEvent+" "+time
            itemView.onClick { listener(event) }
        }else{
            strHomeTeam.text = event.strHomeTeam
            strAwayTeam.text = event.strAwayTeam
            intHomeScore.text = event.intHomeScore.toString()
            intAwayScore.text = event.intAwayScore.toString()
            Log.i("####", event.intHomeScore)
            dateEvent.text = event.dateEvent+" "+time
            tvVs.text = "-"
            itemView.onClick { listener(event) }
        }

    }
}
