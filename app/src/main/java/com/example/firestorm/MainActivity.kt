package com.example.firestorm

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        val edtNome: EditText = findViewById(R.id.edtNome)
        val edtEndereco: EditText = findViewById(R.id.edtEndereco)
        val edtBairro: EditText = findViewById(R.id.edtBairro)
        val edtCep: EditText = findViewById(R.id.edtCep)
        val btnCad: Button = findViewById(R.id.btnCadastro)

        btnCad.setOnClickListener {
            // Create a new user with a first and last name
            val user = hashMapOf(
                "nome" to edtNome.text.toString(),
                "endereco" to edtEndereco.text.toString(),
                "bairro" to edtBairro.text.toString(),
                "cep" to edtCep.text.toString(),
            )

            // Add a new document with a generated ID
            db.collection("cadastro")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText( this, "Registrado Com Sucesso ${documentReference.id}", Toast.LENGTH_SHORT).show()
                    edtNome.setText(null)
                    edtEndereco.setText(null)
                    edtBairro.setText(null)
                    edtCep.setText(null)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}