package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.listplayer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.model.Player
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class ListPalyerAdapter(private val player: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_player, parent, false)
        return PlayerViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(player[position], listener)
    }

    override fun getItemCount(): Int = player.size
}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val imgListPlayer: ImageView = view.find(R.id.iv_player)
    private val namaPlayer: TextView = view.find(R.id.tv_name_player)
    private val positionPlayer: TextView = view.find(R.id.tv_position)

    fun bindItem(player: Player, listener: (Player) -> Unit) {
        Picasso.get().load(player.strCutout).into(imgListPlayer)
        namaPlayer.text = player.strPlayer
        positionPlayer.text = player.strPosition
        itemView.onClick { listener(player) }
    }
}
