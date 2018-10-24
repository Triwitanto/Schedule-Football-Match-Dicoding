package footballschedule.trw.id.schedulefootballmatch.ui.activity.detailplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.model.Player
import footballschedule.trw.id.schedulefootballmatch.util.invisible
import footballschedule.trw.id.schedulefootballmatch.util.visible
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView {

    private lateinit var presenter: DetailPlayerPresenter
    private lateinit var idPlayer : String
    private lateinit var namaPlayer : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        val intent = intent
        idPlayer = intent.getStringExtra("idPlayer")
        namaPlayer = intent.getStringExtra("namaPlayer")
        supportActionBar?.title = namaPlayer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPlayerPresenter(this, request, gson)
        presenter.getDetailPlayer(idPlayer)
        Log.i("test",idPlayer)

    }

    override fun showLoading() {
        progress_bar_detail_player.visible()
    }

    override fun hideLoading() {
        progress_bar_detail_player.invisible()
    }

    override fun showDetailPalyer(data: List<Player>) {
        Picasso.get().load(data[0].strFanart1).into(iv_player_detail)
        tv_weight.text = data[0].strWeight
        tv_height.text = data[0].strHeight
        des_player.text = data[0].strDescriptionEN
        hideLoading()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
