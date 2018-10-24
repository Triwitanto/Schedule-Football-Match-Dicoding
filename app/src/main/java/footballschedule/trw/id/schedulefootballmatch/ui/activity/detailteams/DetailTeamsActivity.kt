package footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.db.FavoriteTeams
import footballschedule.trw.id.schedulefootballmatch.network.db.database
import footballschedule.trw.id.schedulefootballmatch.network.model.Team
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.desteam.DesTeamFragment
import footballschedule.trw.id.schedulefootballmatch.util.invisible
import footballschedule.trw.id.schedulefootballmatch.util.visible
import kotlinx.android.synthetic.main.activity_detail_teams.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamsActivity : AppCompatActivity(), DetailTeamsView {


    private lateinit var presenter: DetailTeamsPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idTeam : String
    private lateinit var namaTeam : String
    private lateinit var imgTeam : String
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_teams)



        val intent = intent
        idTeam = intent.getStringExtra("idTeam")
        namaTeam = intent.getStringExtra("namaTeam")
        imgTeam = intent.getStringExtra("imgTeam")
        supportActionBar?.title = "Detail Team"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamsPresenter(this, request, gson)
        presenter.getDetailTeams(namaTeam)

        favoriteState()

        viewPager = findViewById<ViewPager>(R.id.pager_detail_teams)
        setupViewPager(viewPager!!)

        tabLayout = findViewById<TabLayout>(R.id.tabs_main_detail_team)
        tabLayout!!.setupWithViewPager(viewPager)

        val bundle = Bundle()
        bundle.putString("namaTeam", namaTeam)
        //set Fragmentclass Arguments
        val namaTeamKirim = DesTeamFragment()
        namaTeamKirim.arguments = bundle

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = TabDetailTeamAdapter(namaTeam, supportFragmentManager)

        viewPager.adapter = adapter
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeams.TABLE_FAVORITE2)
                    .whereArgs("(ID_TEAM_DETAIL = {idTeamDetail})",
                            "idTeamDetail" to  idTeam)
            val favoriteTeams = result.parseList(classParser<FavoriteTeams>())
            if (!favoriteTeams.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progress_detail_team.visible()
    }

    override fun hideLoading() {
        progress_detail_team.invisible()
    }

    override fun getDetailTeam(data: List<Team>) {
        idTeam = data[0].idTeam.toString()
        namaTeam = data[0].strTeam.toString()
        imgTeam = data[0].strTeamBadge.toString()
        Picasso.get().load(data[0].strTeamBadge.toString()).into(img_teams_detail)
        Picasso.get().load(data[0].strTeamBadge.toString()).into(iv_background)
        tv_nama_team_detail.text = data[0].strTeam
        tv_tahun_team_detail.text = data[0].intFormedYear
        tv_stadium_team_detail.text = data[0].strStadium
        hideLoading()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_teams, menu)
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
            R.id.add_to_favorite_teams -> {
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
                insert(FavoriteTeams.TABLE_FAVORITE2,
                        FavoriteTeams.ID_TEAM_DETAIL to idTeam,
                        FavoriteTeams.NAMA_TEAM_DETAIL to namaTeam,
                        FavoriteTeams.IMG_TEAM_DETAIL to imgTeam
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
                delete(FavoriteTeams.TABLE_FAVORITE2, "(ID_TEAM_DETAIL = {idTeamDetail})",
                        "idTeamDetail" to idTeam)
            }
            Log.i("####", "Menghapus favorite")
        } catch (e: SQLiteConstraintException){
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
