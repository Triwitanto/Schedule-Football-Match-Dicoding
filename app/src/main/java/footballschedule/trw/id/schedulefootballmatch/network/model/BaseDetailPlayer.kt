package footballschedule.trw.id.schedulefootballmatch.network.model

import com.google.gson.annotations.SerializedName

internal class BaseDetailPlayer{

    @SerializedName("players")
    var player: List<Player>? = null
}
