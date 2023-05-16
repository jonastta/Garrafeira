package com.example.garrafeira

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Tipos(
    var tipos: String,
    var sabor: String,
    var quantidade: Double,
    var id: Long=-1) {
    fun  toContentValues() : ContentValues {
        val valores = ContentValues()
        valores.put(TabelaTipos.CAMPO_TIPO,tipos)
        valores.put(TabelaTipos.CAMPO_SABOR,sabor)
        valores.put(TabelaTipos.CAMPO_QUANTIDADE,quantidade)
        return valores
    }
    companion object{
    fun fromCursor(cursor: Cursor): Tipos {
        val posId = cursor.getColumnIndex(BaseColumns._ID)
        val posTipos = cursor.getColumnIndex(TabelaTipos.CAMPO_TIPO)
        val posSabor = cursor.getColumnIndex(TabelaTipos.CAMPO_SABOR)
        val posQuantidade = cursor.getColumnIndex(TabelaTipos.CAMPO_QUANTIDADE)

        val tipos = cursor.getString(posTipos)
        val quantidade = cursor.getDouble(posQuantidade)
        val sabor = cursor.getString(posSabor)
        val id = cursor.getLong(posId)

        return Tipos(tipos, sabor, quantidade, id)
        }

    }
}
