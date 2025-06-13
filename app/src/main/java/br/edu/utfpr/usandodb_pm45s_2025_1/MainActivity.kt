package br.edu.utfpr.usandodb_pm45s_2025_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.usandodb_pm45s_2025_1.database.DatabaseHandler
import br.edu.utfpr.usandodb_pm45s_2025_1.entity.Cadastro

class MainActivity : AppCompatActivity() {

    private lateinit var etCod: EditText
    private lateinit var etNome: EditText
    private lateinit var etTelefone: EditText
    private lateinit var btExcluir: Button
    private lateinit var btPesquisar: Button

    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById(R.id.etCod)
        etNome = findViewById(R.id.etNome)
        etTelefone = findViewById(R.id.etTelefone)
        btExcluir = findViewById(R.id.btExcluir)
        btPesquisar = findViewById(R.id.btPesquisar)

        if ( intent.getIntExtra( "id", 0 ) != 0 ) {
            etCod.setText(intent.getIntExtra("id", 0).toString())
            etNome.setText(intent.getStringExtra("nome"))
            etTelefone.setText(intent.getStringExtra("telefone"))
        } else {
            btExcluir.visibility = View.GONE
            btPesquisar.visibility = View.GONE
        }

        banco = DatabaseHandler(this)
    }

    fun btAlterarOnClick(view: View) {
        if (etCod.text.toString().isEmpty()) {
            val cadastro = Cadastro(
                0,
                etNome.text.toString(),
                etTelefone.text.toString()
            )
            banco.insert( cadastro )
        } else {
            val cadastro = Cadastro(
                etCod.text.toString().toInt(),
                etNome.text.toString(),
                etTelefone.text.toString()
            )
            banco.update(  cadastro )
        }

        Toast.makeText(
            this,
            "Registro salvo com sucesso!",
            Toast.LENGTH_SHORT
        ).show()

        finish()

    }
    fun btExcluirOnClick(view: View) {

        banco.delete( etCod.text.toString().toInt() )

        Toast.makeText(
            this,
            "Exclusão realizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()

        finish()

    }
    fun btPesquisarOnClick(view: View) {

        val etCodPesquisa = EditText(this)

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Código" )
        builder.setView( etCodPesquisa )
        builder.setCancelable( false )
        builder.setNegativeButton( "Fechar", null )
        builder.setPositiveButton ( "Pesquisar",
            { dialogInterface, i ->

                val registro = banco.pesquisar(
                    etCodPesquisa.text.toString().toInt()
                )

                if ( registro != null ) {
                    etCod.setText( etCodPesquisa.text.toString() )
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
        )

        builder.show()
    }

} //fim da MainActivity