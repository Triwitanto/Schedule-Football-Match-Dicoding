package footballschedule.trw.id.schedulefootballmatch.network

import footballschedule.trw.id.schedulefootballmatch.BuildConfig

object ApiUrl {
    fun getNextMatch(eventNext: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + eventNext
    }

    fun getPreviousMatch(eventPrevious: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + eventPrevious
    }

    fun getDetail(idEvent: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + idEvent
    }

    fun getTeam(idTeam: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + idTeam
    }

    fun getTeamList(nameLeague: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + nameLeague
    }

    fun getTeamDetail(nameTeam: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + nameTeam
    }

     fun getListPalyer(namaTeam: String?): String{
         return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchplayers.php?t=" + namaTeam
     }

    fun getDetailPlayer(idPlayer: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupplayer.php?id=" + idPlayer
    }
    fun getSearchTeam(namaTeam: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + namaTeam
    }

    fun getSearchMatch(namaTeamVs: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + namaTeamVs
    }

}
