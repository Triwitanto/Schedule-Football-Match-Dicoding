package footballschedule.trw.id.schedulefootballmatch.ui.activity.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import footballschedule.trw.id.schedulefootballmatch.R
import android.view.Menu
import android.view.MenuItem
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.favorite.FavoriteAllFragment
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.ScheduleFragment
import footballschedule.trw.id.schedulefootballmatch.ui.fragment.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = ""

        val menu : Menu = bottom_navigation.menu
        selectedMenu(menu.getItem(0))
        bottom_navigation.setOnNavigationItemSelectedListener {
            item: MenuItem ->  selectedMenu(item)

            false
        }
    }

    private fun selectedMenu(item : MenuItem) {
        item.isChecked = true
        when(item.itemId) {
            R.id.schedule_match -> selectedFragment(ScheduleFragment.getInstance())
            R.id.player_match -> selectedFragment(TeamsFragment.getInstance())
            R.id.favorite_match -> selectedFragment(FavoriteAllFragment.getInstance())
        }
    }


    private fun selectedFragment(fragment: Fragment) {
        val transaction : FragmentTransaction? = supportFragmentManager.beginTransaction()
        transaction?.replace(R.id.main_container, fragment)
        transaction?.commit()
    }
}
