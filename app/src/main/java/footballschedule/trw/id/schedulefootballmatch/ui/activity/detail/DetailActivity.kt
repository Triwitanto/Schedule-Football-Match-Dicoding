package footballschedule.trw.id.schedulefootballmatch.ui.activity.detail

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.R.drawable.ic_add_to_favorites
import footballschedule.trw.id.schedulefootballmatch.R.drawable.ic_added_to_favorites
import footballschedule.trw.id.schedulefootballmatch.R.id.*
import footballschedule.trw.id.schedulefootballmatch.R.menu.detail_menu
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.db.Favorite
import footballschedule.trw.id.schedulefootballmatch.network.db.database
import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import footballschedule.trw.id.schedulefootballmatch.network.model.Team
import footballschedule.trw.id.schedulefootballmatch.util.invisible
import footballschedule.trw.id.schedulefootballmatch.util.visible
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("Registered")
class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idEvent : String
    private lateinit var idTeamH : String
    private lateinit var idTeamA : String
    private lateinit var strHomeName : String
    private lateinit var strAwayName : String
    private lateinit var strHomeScore : String
    private lateinit var strAwayScore : String
    private lateinit var strTimeEvent : String
    private lateinit var strTimeEvent2 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        idEvent = intent.getStringExtra("idEvent")
        idTeamH = intent.getStringExtra("idTeamH")
        idTeamA = intent.getStringExtra("idTeamA")
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getDetailMatch(idEvent)
        presenter.getImageHome(idTeamH)
        presenter.getImageAway(idTeamA)

        favoriteState()
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {idEvent})",
                            "idEvent" to  idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progress_bar_detail.visible()
    }

    override fun hideLoading() {
        progress_bar_detail.invisible()
    }

    fun formatDateToString(date: Date?, format: String,
                           timeZone: String?): String? {
        var timeZone = timeZone
        // null check
        if (date == null) return null
        // create SimpleDateFormat object with input format
        val sdf = SimpleDateFormat(format)
        // default system timezone if passed null or empty
        if (timeZone == null || "".equals(timeZone.trim { it <= ' ' }, ignoreCase = true)) {
            timeZone = Calendar.getInstance().getTimeZone().getID()
        }
        // set timezone to SimpleDateFormat
        sdf.timeZone = TimeZone.getTimeZone(timeZone)
        // return Date in required format with timezone as String
        return sdf.format(date)
    }

    override fun showDetailMatch(data: List<Event>) {
        Log.i("####", "triwiiii")

//        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        simpleDateFormat.timeZone =  data[0].strTime.toString()
//        val myDate = simpleDateFormat.parse(rawQuestion.getString("AskDateTime"))

//        System.out.println("System Date in GMT: "+formatDateToString(data[0].strTime, "dd MMM yyyy hh:mm:ss a", "GMT"));

        val df =  SimpleDateFormat("HH:mm", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date = df.parse(data[0].strTime)
        df.timeZone = TimeZone.getDefault()
        val time = df.format(date)
        Log.e("###", data[0].strTime)

        strHomeName = data[0].strHomeTeam.toString()
        strAwayName = data[0].strAwayTeam.toString()
        strHomeScore = data[0].intHomeScore.toString()
        strAwayScore = data[0].intAwayScore.toString()
        strTimeEvent = data[0].dateEvent.toString()
        strTimeEvent2 = data[0].strTime.toString()
        iv_name_team1.text = data[0].strHomeTeam
        iv_name_team2.text = data[0].strAwayTeam
        iv_score_team1.text = data[0].intHomeScore
        iv_score_team2.text = data[0].intAwayScore
        tv_formation1.text = data[0].strHomeFormation
        tv_formation2.text = data[0].strAwayFormation
        tv_goal1.text = data[0].strHomeGoalDetails
        tv_goal2.text = data[0].strAwayGoalDetails
        tv_date_match.text = data[0].dateEvent
        tv_time_match.text = time
        tv_name_gk1.text = data[0].strHomeLineupGoalkeeper
        tv_name_gk2.text =data[0].strAwayLineupGoalkeeper
        tv_name_defense1.text = data[0].strHomeLineupMidfield
        tv_name_defense2.text = data[0].strAwayLineupMidfield
        tv_name_center_player1.text = data[0].strHomeLineupForward
        tv_name_center_player2.text = data[0].strAwayLineupForward
        tv_name_attack1.text = data[0].strHomeLineupSubstitutes
        tv_name_attack2.text = data[0].strAwayLineupSubstitutes
    }
    override fun getHomeBadge(data: List<Team>) {
        Log.i("####", "triwiiii")
        Picasso.get().load(data[0].strTeamBadge).into(iv_logo_team1)
    }

    override fun getAwayBadge(data: List<Team>) {
        Log.i("####", "triwiiii")
        Picasso.get().load(data[0].strTeamBadge.toString()).into(iv_logo_team2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite)
                    removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENT to idEvent,
                        Favorite.ID_TEAMH to idTeamH,
                        Favorite.ID_TEAMA to idTeamA,
                        Favorite.TEAM_HOME_NAME to strHomeName,
                        Favorite.TEAM_AWAY_NAME to strAwayName,
                        Favorite.TEAM_HOME_SCORE to strHomeScore,
                        Favorite.TEAM_AWAY_SCORE to strAwayScore,
                        Favorite.DATE_TIME to strTimeEvent,
                        Favorite.DATE_TIME2 to strTimeEvent2
                )
            }
            Log.i("####", "Berhasil Menyimpan")
//            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }


    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(ID_EVENT = {idEvent})",
                        "idEvent" to idEvent)
            }
            Log.i("####", "Menghapus favorite")
        } catch (e: SQLiteConstraintException){
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
