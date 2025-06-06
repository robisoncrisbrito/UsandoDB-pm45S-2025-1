package br.edu.utfpr.usandodb_pm45s_2025_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.usandodb_pm45s_2025_1.database.DatabaseHandler
import br.edu.utfpr.usandodb_pm45s_2025_1.entity.Cadastro

class MainActivity : AppCompatActivity() {

    private lateinit var etCod: EditText
    private lateinit var etNome: EditText
    private lateinit var etTelefone: EditText

    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById(R.id.etCod)
        etNome = findViewById(R.id.etNome)
        etTelefone = findViewById(R.id.etTelefone)

        banco = DatabaseHandler(this)
    }

    fun btIncluirOnClick(view: View) {
        val cadastro = Cadastro(
            0,
            etNome.text.toString(),
            etTelefone.text.toString()
        )

        banco.insert( cadastro )

        Toast.makeText(
            this,
            "Inclusão realizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun btAlterarOnClick(view: View) {
        val cadastro = Cadastro(
            etCod.text.toString().toInt(),
            etNome.text.toString(),
            etTelefone.text.toString()
        )

        banco.update(  cadastro )

        Toast.makeText(
            this,
            "Alteração realizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()

    }
    fun btExcluirOnClick(view: View) {

        banco.delete( etCod.text.toString().toInt() )

        Toast.makeText(
            this,
            "Exclusão realizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()

    }
    fun btPesquisarOnClick(view: View) {

        val registro = banco.pesquisar(
            etCod.text.toString().toInt()
        )

        if ( registro != null ) {
            etNome.setText( registro.nome )
            etTelefone.setText( registro.telefone )
        } else {
            Toast.makeText(
                this,
                "Registro não encontrado!",
                Toast.LENGTH_SHORT
            ).show()
        }



    }
    fun btListarOnClick(view: View) {

        val intent = Intent( this, ListarActivity::class.java )
        startActivity( intent )




//        val registros = banco.listar()
//
//        while (registros.moveToNext()) {
//            val id = registros.getInt(0)
//            val nome = registros.getString(1)
//            val telefone = registros.getString(2)
//
//            Toast.makeText(
//                this,
//                "$nome - $telefone",
//                Toast.LENGTH_SHORT
//            ).show()
//
//        }

    }

} //fim da MainActivity