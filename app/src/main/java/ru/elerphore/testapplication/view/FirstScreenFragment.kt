package ru.elerphore.testapplication.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.databinding.FirstScreenBinding

class FirstScreenFragment : Fragment(R.layout.first_screen) {

    private lateinit var firstScreenFragment: FirstScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstScreenFragment = FirstScreenBinding.bind(view)

        firstScreenFragment.goButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstScreen_to_SecondScreen)
        }
    }
}