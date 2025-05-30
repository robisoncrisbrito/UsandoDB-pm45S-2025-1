package br.edu.utfpr.usandodb_pm45s_2025_1.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.edu.utfpr.usandodb_pm45s_2025_1.MainActivity
import br.edu.utfpr.usandodb_pm45s_2025_1.entity.Cadastro

class DatabaseHandler (context : Context ) : SQLiteOpenHelper (context, BD_NAME, null, BD_VERSION )  {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS ${TABLE_NAME} (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, telefone TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME}")
        onCreate(db)
    }

    fun insert( cadastro : Cadastro ) {
        val db = this.writableDatabase
        val registro = ContentValues()
        registro.put("nome", cadastro.nome)
        registro.put("telefone", cadastro.telefone)
        db.insert(TABLE_NAME, null, registro)
    }

    fun update( cadastro : Cadastro ) {
        val db = this.writableDatabase
        val registro = ContentValues()
        registro.put("nome", cadastro.nome)
        registro.put("telefone", cadastro.telefone)
        db.update(TABLE_NAME, registro, "_id = ${cadastro._id}", null)
    }

    fun delete( id : Int ) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "_id = $id", null)
    }

    fun pesquisar( id : Int ) : Cadastro? {
        val db = this.readableDatabase
        val registro = db.query(
            TABLE_NAME,
            null,
            "_id = $id",
            null,
            null,
            null,
            null
        )
        if (registro.moveToNext()) {
            val _id = registro.getInt(COL_ID)
            val nome = registro.getString(COL_NOME)
            val telefone = registro.getString(COL_TELEFONE)
            return Cadastro(_id, nome, telefone)
        } else {
            return null
        }
    }

    fun listar() : Cursor {
        val db = this.readableDatabase

        val registros =  db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        return registros
    }

    companion object {
        private const val BD_NAME = "dbfile.sqlite"
        private const val TABLE_NAME = "cadastro"
        private const val BD_VERSION = 1
        private const val COL_ID = 0
        private const val COL_NOME = 1
        private const val COL_TELEFONE = 2
    }

} //fim do DatabaseHancler