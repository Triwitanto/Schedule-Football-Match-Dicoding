package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.nextmatch

import android.support.v7.widget.RecyclerView
import android.util.Log
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

class NextMatchAdapter(private val event: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<NextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_next_match_schedule, parent, false)
        return NextViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: NextViewHolder, position: Int) {
        holder.bindItem(event[position], listener)
    }

    override fun getItemCount(): Int = event.size
}

class NextViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val strHomeTeam: TextView = view.find(R.id.tv_team1)
    private val strAwayTeam: TextView = view.find(R.id.tv_team2)
    private val dateEvent: TextView = view.find(R.id.tv_date_match)


    fun bindItem(event: Event, listener: (Event) -> Unit) {

        val df =  SimpleDateFormat("HH:mm", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date = df.parse(event.strTime)
        df.timeZone = TimeZone.getDefault()
        val time = df.format(date)

            strHomeTeam.text = event.strHomeTeam
            strAwayTeam.text = event.strAwayTeam
            dateEvent.text = event.dateEvent +" "+time
            itemView.onClick { listener(event) }
    }
}