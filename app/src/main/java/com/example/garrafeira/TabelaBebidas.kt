package com.example.garrafeira

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBebidas(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, marca TEXT NOT NULL, TEOR_ALCOOLICO BOOLEAN NOT NULL, id_tipos INTEGER NOT NULL, FOREIGN KEY (id_tipos) REFERENCES ${TabelaTipos.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME_TABELA = "Provas"
    }
}