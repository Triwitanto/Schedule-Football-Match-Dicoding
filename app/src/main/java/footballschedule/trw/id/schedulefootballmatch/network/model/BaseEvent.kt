package footballschedule.trw.id.schedulefootballmatch.network.model

import com.google.gson.annotations.SerializedName

class BaseEvent{
    @SerializedName("event")
    var event: List<Event>? = null

}
