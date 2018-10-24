package footballschedule.trw.id.schedulefootballmatch.network.model

import com.google.gson.annotations.SerializedName

class BaseTeam(
        @SerializedName("teams")
        var teams: List<Team>
)
