package footballschedule.trw.id.schedulefootballmatch.network.model

import com.google.gson.annotations.SerializedName

class BaseListTeam {

    @SerializedName("teams")
    var teams: List<Team>? = null

}
