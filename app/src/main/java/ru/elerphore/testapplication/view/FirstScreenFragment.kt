package ru.elerphore.testapplication.view

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.databinding.FirstScreenBinding
import ru.elerphore.testapplication.extension.toPercentage

class FirstScreenFragment : Fragment(R.layout.first_screen) {
    private lateinit var firstScreenFragment: FirstScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstScreenFragment = FirstScreenBinding.bind(view)
        firstScreenFragment.goButton.setOnClickListener { findNavController().navigate(R.id.action_FirstScreen_to_SecondScreen) }
        firstScreenFragment.progressBar.fakeLoading()

        firstScreenFragment.progressBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                firstScreenFragment.progressBarPercentage.text = progress.toPercentage()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }
}