package footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite.teamfavorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.db.FavoriteTeams
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteTeamAdapter(private val favoriteteam: List<FavoriteTeams>, private val listener: (FavoriteTeams) -> Unit)
    : RecyclerView.Adapter<FavoriteTeamViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_teams, parent, false)
        return FavoriteTeamViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favoriteteam[position], listener)
    }

    override fun getItemCount(): Int = favoriteteam.size
}

class FavoriteTeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val imgTeam: ImageView = view.find(R.id.iv_logo_team)
    private val strNamaTeam: TextView = view.find(R.id.tv_teams)

    fun bindItem(favorite: FavoriteTeams, listener: (FavoriteTeams) -> Unit) {
        Picasso.get().load(favorite.imgTeamDetail).into(imgTeam)
        strNamaTeam.text = favorite.namaTeamDetail
        itemView.onClick { listener(favorite) }


    }
}
