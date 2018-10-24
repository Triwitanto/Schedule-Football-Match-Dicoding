package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.previousmatch


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class PreviousMatchAdapter(private val event: List<Event>,private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<PreviousViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_previous_match_schedule, parent, false)
        return PreviousViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: PreviousViewHolder, position: Int) {
        holder.bindItem(event[position], listener)
    }

    override fun getItemCount(): Int = event.size
}

class PreviousViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val strHomeTeam: TextView = view.find(R.id.tv_name_team1)
    private val strAwayTeam: TextView = view.find(R.id.tv_name_team2)
    private val intHomeScore: TextView = view.find(R.id.tv_score_team1)
    private val intAwayScore: TextView = view.find(R.id.tv_score_team2)
    private val dateEvent: TextView = view.find(R.id.tv_date_event)
    private val timeEvent: TextView = view.find(R.id.tv_time_event)

    fun bindItem(event: Event, listener: (Event) -> Unit) {
        val df =  SimpleDateFormat("HH:mm", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date = df.parse(event.strTime)
        df.timeZone = TimeZone.getDefault()
        val time = df.format(date)
        if (event.intHomeScore==(null) || event.intAwayScore==(null)){
            strHomeTeam.text = event.strHomeTeam
            strAwayTeam.text = event.strAwayTeam
            intHomeScore.text = ""
            intAwayScore.text = ""
            timeEvent.text= time
            dateEvent.text = event.dateEvent
        }else{
            strHomeTeam.text = event.strHomeTeam
            strAwayTeam.text = event.strAwayTeam
            intHomeScore.text = event.intHomeScore.toString()
            intAwayScore.text = event.intAwayScore.toString()
            dateEvent.text = event.dateEvent
            timeEvent.text= time
            itemView.onClick { listener(event) }
        }

    }
}
