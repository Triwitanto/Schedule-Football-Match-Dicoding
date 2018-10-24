package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class SearchMatchAdapter(private val event: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<SearchMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_favorite, parent, false)
        return SearchMatchViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: SearchMatchViewHolder, position: Int) {
        holder.bindItem(event[position], listener)
    }

    override fun getItemCount(): Int = event.size
}

class SearchMatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val strHomeTeam: TextView = view.find(R.id.tv_team1)
    private val strAwayTeam: TextView = view.find(R.id.tv_team2)
    private val intHomeScore: TextView = view.find(R.id.tv_score_team1F)
    private val intAwayScore: TextView = view.find(R.id.tv_score_team2F)
    private val dateEvent: TextView = view.find(R.id.tv_date_match)
    private val tvVs: TextView = view.find(R.id.tv_vs)

    fun bindItem(event: Event, listener: (Event) -> Unit) {
        if (event.intHomeScore.equals(null)){
            strHomeTeam.text = event.strHomeTeam
            strAwayTeam.text = event.strAwayTeam
            intHomeScore.text = ""
            intAwayScore.text = ""
            dateEvent.text = event.dateEvent
            itemView.onClick { listener(event) }
        }else{
            strHomeTeam.text = event.strHomeTeam
            strAwayTeam.text = event.strAwayTeam
            intHomeScore.text = event.intHomeScore.toString()
            intAwayScore.text = event.intAwayScore.toString()
            dateEvent.text = event.dateEvent
            tvVs.text = "-"
            itemView.onClick { listener(event) }
        }

    }
}
