package footballschedule.trw.id.schedulefootballmatch.network.db

data class FavoriteTeams(val id: Long?, val idTeamDetail: String?, val namaTeamDetail: String?, val imgTeamDetail: String?) {

    companion object {
        const val TABLE_FAVORITE2: String = "TABLE_FAVORITE2"
        const val ID: String = "ID_"
        const val ID_TEAM_DETAIL: String = "ID_TEAM_DETAIL"
        const val NAMA_TEAM_DETAIL: String = "NAMA_TEAM_DETAIL"
        const val IMG_TEAM_DETAIL: String = "IMG_TEAM_DETAIL" }
}