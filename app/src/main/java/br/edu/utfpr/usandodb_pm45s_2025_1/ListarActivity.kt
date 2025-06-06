package br.edu.utfpr.usandodb_pm45s_2025_1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        val adapter = SimpleCursorAdapter (
            this,
            android.R.layout.simple_list_item_2,
            registros,
            arrayOf( "nome", "telefone" ),
            intArrayOf( android.R.id.text1, android.R.id.text2 ),
            0
        )

        lvRegistros.adapter = adapter

    }
}