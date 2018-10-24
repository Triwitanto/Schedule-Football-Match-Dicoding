@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.desteam

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import footballschedule.trw.id.schedulefootballmatch.R
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.model.Team
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams.DetailTeamsPresenter
import footballschedule.trw.id.schedulefootballmatch.ui.activity.detailteams.TabDetailTeamAdapter.Companion.KEY_TEAM
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.TeamFragmentView
import kotlinx.android.synthetic.main.fragment_des_team.*

class DesTeamFragment : Fragment(), DesTeamView {

    private lateinit var presenter: DesTeamPresenter
    private lateinit var namaTeam : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        namaTeam2 = arguments!!.getString("namaTeam")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_des_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bindData = arguments
        namaTeam = bindData?.getString(KEY_TEAM) ?: "namaTeam"

        val request = ApiRepository()
        val gson = Gson()
//        Log.i("nama",namaTeam2)
        presenter = DesTeamPresenter(this, request, gson)
        presenter.getDesTeam(namaTeam)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDescTeam(data: List<Team>) {
        tv_desc_team.text = data[0].strDescriptionEN
    }
}

