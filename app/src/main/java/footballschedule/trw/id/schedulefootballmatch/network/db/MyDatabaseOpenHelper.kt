package footballschedule.trw.id.schedulefootballmatch.network.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables

        db.createTable(FavoriteTeams.TABLE_FAVORITE2, true,
                FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeams.ID_TEAM_DETAIL to TEXT,
                FavoriteTeams.NAMA_TEAM_DETAIL to TEXT,
                FavoriteTeams.IMG_TEAM_DETAIL to TEXT

        )

        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_EVENT to TEXT+ UNIQUE,
                Favorite.ID_TEAMH to TEXT,
                Favorite.ID_TEAMA to TEXT,
                Favorite.TEAM_HOME_NAME to TEXT,
                Favorite.TEAM_AWAY_NAME to TEXT,
                Favorite.TEAM_HOME_SCORE to TEXT,
                Favorite.TEAM_AWAY_SCORE to TEXT,
                Favorite.DATE_TIME to TEXT,
                Favorite.DATE_TIME2 to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.dropTable(FavoriteTeams.TABLE_FAVORITE2, true)
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)

    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
