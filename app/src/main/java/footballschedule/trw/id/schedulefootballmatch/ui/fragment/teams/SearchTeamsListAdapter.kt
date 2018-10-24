package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import footballschedule.trw.id.schedulefootballmatch.network.model.Team
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class SearchTeamsListAdapter(private val teams: List<Team>,private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<SearchTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTeamsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_teams, parent, false)
        return SearchTeamsViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: SearchTeamsViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)

    }

    override fun getItemCount(): Int = teams.size
}

class SearchTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val imgTeams: ImageView = view.find(R.id.iv_logo_team)
    private val nameTeams: TextView = view.find(R.id.tv_teams)

    fun bindItem(teams: Team, listener: (Team) -> Unit) {
        Picasso.get().load(teams.strTeamBadge).into(imgTeams)
        nameTeams.text = teams.strTeam
        itemView.onClick { listener(teams) }
    }
}
