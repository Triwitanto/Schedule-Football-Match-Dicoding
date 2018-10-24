package footballschedule.trw.id.schedulefootballmatch.network.db

data class Favorite(val id: Long?, val idEvent: String?, val idTeamH: String?, val idTeamA: String?
                    , val strHomeTeam: String?, val strAwayTeam: String?, val intHomeScore: String?, val intAwayScore: String?, val dateEvent: String? , val timeEvent: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val ID_TEAMH: String = "ID_TEAMH"
        const val ID_TEAMA: String = "ID_TEAMA"
        const val TEAM_HOME_NAME: String = "TEAM_NAME1"
        const val TEAM_AWAY_NAME: String = "TEAM_NAME2"
        const val TEAM_HOME_SCORE: String = "SCORE_HOME1"
        const val TEAM_AWAY_SCORE: String = "SCORE_AWAY1"
        const val DATE_TIME: String = "DATE_EVENT"
        const val DATE_TIME2: String = "DATE_EVENT2"}
}