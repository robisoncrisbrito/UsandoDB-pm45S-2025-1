package br.edu.utfpr.usandodb_pm45s_2025_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.usandodb_pm45s_2025_1.adapter.MeuAdapter
import br.edu.utfpr.usandodb_pm45s_2025_1.database.DatabaseHandler

class ListarActivity : AppCompatActivity() {

    private lateinit var lvRegistros : ListView

    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        lvRegistros = findViewById(R.id.lvRegistros)
        banco = DatabaseHandler(this)

    }

    override fun onStart() {
        super.onStart()
        val registros = banco.listar()
        val adapter = MeuAdapter( this, registros )
        lvRegistros.adapter = adapter
    }

    fun btIncluirOnClick(view: View) {
        val intent = Intent( this, MainActivity::class.java )

        startActivity(intent)
    }
}