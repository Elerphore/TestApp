package ru.elerphore.testapplication.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.adapter.ProgressBarAdapter
import ru.elerphore.testapplication.databinding.SecondScreenBinding
import ru.elerphore.testapplication.extension.generateSecondsInRange

class SecondScreenFragment : Fragment(R.layout.second_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(SecondScreenBinding.bind(view)) {
            backArrow.setOnClickListener { findNavController().navigate(R.id.action_SecondScreen_to_FirstScreen) }

            progressBar2.fakeLoading()
            progressBar3.fakeLoading()

            ProgressBarAdapter(textView2)

            progressBar2.setOnSeekBarChangeListener(ProgressBarAdapter(textView2))
            progressBar3.setOnSeekBarChangeListener(ProgressBarAdapter(secondProgressBarState))

            randomizeButton.setOnClickListener {
                progressBar2.fakeLoading(Long.generateSecondsInRange())
                progressBar3.fakeLoading(Long.generateSecondsInRange())
            }
        }
    }
}