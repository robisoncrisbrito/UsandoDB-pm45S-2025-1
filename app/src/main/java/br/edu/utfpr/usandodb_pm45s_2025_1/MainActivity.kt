package br.edu.utfpr.usandodb_pm45s_2025_1

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etCod: EditText
    private lateinit var etNome: EditText
    private lateinit var etTelefone: EditText

    private lateinit var banco : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById(R.id.etCod)
        etNome = findViewById(R.id.etNome)
        etTelefone = findViewById(R.id.etTelefone)

        banco = openOrCreateDatabase( BD_NAME, MODE_PRIVATE, null)
        banco.execSQL("CREATE TABLE IF NOT EXISTS ${TABLE_NAME} (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, telefone TEXT)")
    }

    fun btIncluirOnClick(view: View) {
        val registro = ContentValues()
        registro.put("nome", etNome.text.toString())
        registro.put("telefone", etTelefone.text.toString())
        banco.insert(TABLE_NAME, null, registro)

        Toast.makeText(
            this,
            "Inclusão realizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun btAlterarOnClick(view: View) {
        val registro = ContentValues()
        registro.put("nome", etNome.text.toString())
        registro.put("telefone", etTelefone.text.toString())

        banco.update(
            TABLE_NAME,
            registro,
            "_id = ${etCod.text.toString()}",
            null
        )

        Toast.makeText(
            this,
            "Alteração realizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()

    }
    fun btExcluirOnClick(view: View) {

        banco.delete(
            TABLE_NAME,
            "_id = ${etCod.text.toString()}",
            null
        )

        Toast.makeText(
            this,
            "Exclusão realizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()

    }
    fun btPesquisarOnClick(view: View) {
        val registro = banco.query(
            TABLE_NAME,
            null,
            "_id = ${etCod.text.toString()}",
            null,
            null,
            null,
            null
        )

        if (registro.moveToNext()) {
            val id = registro.getInt(COL_ID)
            val nome = registro.getString(COL_NOME)
            val telefone = registro.getString(COL_TELEFONE)

            etNome.setText(nome)
            etTelefone.setText(telefone)
        } else {
            Toast.makeText(
                this,
                "Registro não encontrado!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
    fun btListarOnClick(view: View) {
        val registros = banco.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (registros.moveToNext()) {
            val id = registros.getInt(COL_ID)
            val nome = registros.getString(COL_NOME)
            val telefone = registros.getString(COL_TELEFONE)

            Toast.makeText(
                this,
                "$nome - $telefone",
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    companion object {
        private const val BD_NAME = "dbfile.sqlite"
        private const val TABLE_NAME = "cadastro"
        private const val COL_ID = 0
        private const val COL_NOME = 1
        private const val COL_TELEFONE = 2
    }

} //fim da MainActivity