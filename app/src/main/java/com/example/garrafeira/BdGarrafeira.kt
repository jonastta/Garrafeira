package com.example.garrafeira

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private const val VERSAO_BASE_DADOS = 1


class BdGarrafeira(
    context: Context?,
) : SQLiteOpenHelper(context, NOME_BASE_DADOS, null,VERSAO_BASE_DADOS) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        TabelaTipos(db).cria()
        TabelaBebidas(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    companion object{
        const val NOME_BASE_DADOS = "Garrafeira.bd"
    }

}