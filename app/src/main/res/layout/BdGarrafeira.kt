package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.garrafeira.TabelaBD
import com.example.garrafeira.TabelaProvas
import com.example.garrafeira.TabelaTipos

class bdGarrafeira(
    context: Context?,
) : SQLiteOpenHelper(context, "Garrafeira.db", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        TabelaTipos(db).cria()
        TabelaProvas(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}