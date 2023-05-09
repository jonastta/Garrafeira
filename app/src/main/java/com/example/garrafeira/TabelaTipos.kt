package com.example.garrafeira

import android.database.sqlite.SQLiteDatabase

class TabelaTipos(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, tipo TEXT NOT NULL, sabor TEXT NOT NULL,quantidade DOUBLE NOT NULL )")
    }

    companion object {
        const val NOME_TABELA = "tipos"
    }
}