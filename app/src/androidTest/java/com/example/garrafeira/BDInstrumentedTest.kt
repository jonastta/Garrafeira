package com.example.garrafeira

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private fun getAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BdGarrafeira.NOME_BASE_DADOS)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BdGarrafeira(getAppContext())
        val bd = openHelper.readableDatabase
        assert(bd.isOpen)
    }

    @Test
    fun ConsegueInserirTipos(){
        val bd = getWritableDatabase()

        val tipo = Tipos("sumos", "laranja",1.00)
        insereTipos(bd, tipo)
    }

    private fun insereTipos(bd: SQLiteDatabase, tipo: Tipos) {
        tipo.id = TabelaTipos(bd).insere(tipo.toContentValues())
        assertNotEquals(-1, tipo.id)
    }


    @Test
    fun ConsegueInserirBebidas(){
        val bd = getWritableDatabase()

        val tipo = Tipos("Vinho", "Tinto",0.75)
        insereTipos(bd, tipo)

        val Bebida1 = Bebidas("Joy","Não", tipo.id)
        insereBebida(bd, Bebida1)

        val Bebida2 = Bebidas("Papa Figos", "Sim", tipo.id)
        insereBebida(bd, Bebida1)
    }

    @Test
    fun consegueLerTipos() {
        val bd = getWritableDatabase()

        val tipo1 = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo1)

        val tipo2 = Tipos("Sumos", "Laranja",1.00)
        insereTipos(bd, tipo2)

        val tabelaTipos = TabelaTipos(bd)

        val cursor = tabelaTipos.consulta(
            TabelaTipos.CAMPOS, "${BaseColumns._ID}=?", arrayOf(tipo1.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())
        val tipoBD = Tipos.fromCursor(cursor)

        assertEquals(tipo1,tipoBD)

        val cursorTodosTipos = tabelaTipos.consulta(
            TabelaTipos.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaTipos.CAMPO_TIPO
        )

        assert(cursorTodosTipos.count > 1)
    }
    @Test
    fun consegueLerBebidas() {
        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)

        val Bebida1 = Bebidas("Joy","Não",tipo.id)
        insereBebida(bd, Bebida1)

        val Bebida2 = Bebidas("Papa Figo","Sim",tipo.id)
        insereBebida(bd, Bebida2)

        val tabelaBebidas = TabelaTipos(bd)

        val cursor = tabelaBebidas.consulta(
            TabelaTipos.CAMPOS, "${BaseColumns._ID}=?", arrayOf(Bebida1.id_tipos.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())
        val tipoBD = Tipos.fromCursor(cursor)

        assertEquals(Bebida1,tipoBD)

        val cursorTodosTipos = tabelaBebidas.consulta(
            TabelaTipos.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaTipos.CAMPO_TIPO
        )

        assert(cursorTodosTipos.count > 1)
    }


    private fun insereBebida(bd: SQLiteDatabase, bebida: Bebidas) {
        bebida.id_tipos = TabelaBebidas(bd).insere(bebida.toContentValues())
        assertNotEquals(-1, bebida.id_tipos)
    }

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BdGarrafeira(getAppContext())
        val bd = openHelper.writableDatabase
        return bd
    }

    @Test

    fun consegueAlterarTipos(){

        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)

        tipo.tipos = "Sumos"
        val registoAlterado = TabelaTipos(bd).altera(tipo.toContentValues(),
        "${BaseColumns._ID}=?",
        arrayOf(tipo.id.toString()),
        )

        assertEquals(1,registoAlterado)
    }

    @Test

    fun consegueAlterarBebidas(){

        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)

        val tipo2 = Tipos("Sumos", "Laranja",1.00)
        insereTipos(bd, tipo2)

        val Bebida1 = Bebidas("Papa Figos","Sim",tipo.id)
        insereBebida(bd, Bebida1)

        Bebida1.id_tipos = tipo2.id
        Bebida1.marca = "Sumol"
        Bebida1.TEOR_ALCOOLICO="Não"


        val registoAlterado = TabelaTipos(bd).altera(Bebida1.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(tipo.id.toString()),
        )

        assertEquals(1,registoAlterado)

    }

    @Test

    fun consegueEleminarTipos(){

        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)


        val registoEliminado = TabelaTipos(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(tipo.id.toString()),
        )

        assertEquals(1,registoEliminado)
    }

    @Test

    fun consegueEliminarBebidas(){

        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)


        val Bebida1 = Bebidas("Papa Figos","Sim",tipo.id)
        insereBebida(bd, Bebida1)




        val registoEliminado = TabelaTipos(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(tipo.id.toString()),
        )

        assertEquals(1,registoEliminado)

    }

}