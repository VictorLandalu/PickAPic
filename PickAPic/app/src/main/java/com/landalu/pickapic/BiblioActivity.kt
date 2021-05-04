package com.landalu.pickapic

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class BiblioActivity : AppCompatActivity() {
    private val requestBiblio= 1001
    var imageUri : Uri? = null
    val listaImagenes = arrayListOf<Uri?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblio)
        abrirBtnBiblio()
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
        intentBiblio.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intentBiblio.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intentBiblio, "selecciona"), requestBiblio)
//necesitamos saber si tenemos alguna respuesta para recuperar la imagen

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val clipData = data!!.clipData
        if (resultCode == RESULT_OK && requestCode == requestBiblio) {
            if (clipData == null) {
                imageUri = data.data
                listaImagenes.add(imageUri)
            } else {
                for (i in 0 until clipData.itemCount) {
                    listaImagenes.add(clipData.getItemAt(i).uri)
                }
            }
        }
        /*baseAdapter = GridViewAdapter(this@MainActivity, listaImagenes)
        gvImagenes.setAdapter(baseAdapter)*/
    }
}