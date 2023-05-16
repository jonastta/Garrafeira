package com.example.garrafeira

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaTipos(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_TIPO TEXT NOT NULL, $CAMPO_SABOR TEXT NOT NULL,$CAMPO_QUANTIDADE DOUBLE NOT NULL )")
    }

    companion object {
        const val NOME_TABELA = "tipos"
        const val CAMPO_TIPO = "tipo"
        const val CAMPO_SABOR = "sabor"
        const val CAMPO_QUANTIDADE = "quantidade"

        val CAMPOS = arrayOf(BaseColumns._ID, CAMPO_TIPO, CAMPO_SABOR, CAMPO_QUANTIDADE)
    }
}