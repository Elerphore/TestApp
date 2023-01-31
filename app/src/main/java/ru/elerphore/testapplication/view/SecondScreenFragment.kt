package ru.elerphore.testapplication.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.databinding.SecondScreenBinding

class SecondScreenFragment : Fragment(R.layout.second_screen) {
    private lateinit var secondScreenBinding: SecondScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        secondScreenBinding = SecondScreenBinding.bind(view)

        secondScreenBinding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_SecondScreen_to_FirstScreen)
        }
    }

}