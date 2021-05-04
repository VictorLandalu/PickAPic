package com.landalu.pickapic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.landalu.pickapic.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    }
    //El sistema lo llama cuando el fragmento debe diseñar su interfaz de usuario por primera vez. A fin de diseñar una IU para tu fragmento, debes mostrar un View desde este método, que será la raíz del diseño de tu fragmento. Puedes mostrar un valor nulo si el fragmento no proporciona una IU.
    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

