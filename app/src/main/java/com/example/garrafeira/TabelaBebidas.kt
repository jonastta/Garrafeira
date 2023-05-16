package com.example.garrafeira

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBebidas(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $NOME_MARCA TEXT NOT NULL, $NOME_TEOR_ALCOOLICO BOOLEAN NOT NULL, $NOME_FK_TIPOS INTEGER NOT NULL, FOREIGN KEY ($NOME_FK_TIPOS) REFERENCES ${TabelaTipos.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME_TABELA = "Bebidas"
        const val NOME_MARCA = "marca"
        const val NOME_TEOR_ALCOOLICO = "TEOR_ALCOOLICO"
        const val NOME_FK_TIPOS = "id_tipos"
    }
}