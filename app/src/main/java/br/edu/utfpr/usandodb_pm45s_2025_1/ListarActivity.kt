package br.edu.utfpr.usandodb_pm45s_2025_1

import android.os.Bundle
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

        val registros = banco.listar()

        val adapter = MeuAdapter( this, registros )

        lvRegistros.adapter = adapter

    }
}