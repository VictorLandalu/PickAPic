package com.landalu.pickapic

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class BiblioActivity : AppCompatActivity() {
    private val requestBiblio= 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblio)
    }
    private fun abrirBtnBiblio(){

        val btnBiblioteca= findViewById<Button>(R.id.btnBiblioteca)
        btnBiblioteca.setOnClickListener(){
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                //Preguntamos si tiene permiso
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    val permisoArchivos= arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permisoArchivos, requestBiblio)
                }else{
                    mostrarBiblio()
                }

            }else{
                mostrarBiblio()
            }
        }
    }
    //comprobamos si nos ha dado permiso
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            requestBiblio -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    mostrarBiblio()
                else
                    Toast.makeText(this, "No se puede acceder a galeria", Toast.LENGTH_LONG).show()

            }

        }

    }

    private fun mostrarBiblio(){

        //val intentBiblioteca= Intent(Intent.ACTION_PICK)
        //intentBiblioteca.type= "image/*"
        //startActivityForResult(intentBiblioteca, requestBiblio)
        val intentBiblio: Intent = Intent()
        intentBiblio.type= "image/*"
        intentBiblio.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intentBiblio, "selecciona"), requestBiblio)
//necesitamos saber si tenemos alguna respuesta para recuperar la imagen

    }
}