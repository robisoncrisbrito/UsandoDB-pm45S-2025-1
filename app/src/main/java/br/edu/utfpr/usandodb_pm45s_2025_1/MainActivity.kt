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

        banco = openOrCreateDatabase("dbfile.sqlite", MODE_PRIVATE, null)
        banco.execSQL("CREATE TABLE IF NOT EXISTS cadastro (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, telefone TEXT)")
    }

    fun btIncluirOnClick(view: View) {
        val registro = ContentValues()
        registro.put("nome", etNome.text.toString())
        registro.put("telefone", etTelefone.text.toString())
        banco.insert("cadastro", null, registro)

        Toast.makeText(
            this,
            "Inclusão realizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()
    }
    fun btAlterarOnClick(view: View) {

    }
    fun btExcluirOnClick(view: View) {

    }
    fun btPesquisarOnClick(view: View) {

    }
    fun btListarOnClick(view: View) {

    }
}