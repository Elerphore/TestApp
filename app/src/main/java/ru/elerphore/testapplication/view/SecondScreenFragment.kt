package ru.elerphore.testapplication.view

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.databinding.SecondScreenBinding
import ru.elerphore.testapplication.extension.toPercentage

class SecondScreenFragment : Fragment(R.layout.second_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(SecondScreenBinding.bind(view)) {
            backArrow.setOnClickListener {
                findNavController().navigate(R.id.action_SecondScreen_to_FirstScreen)
            }

            progressBar2.fakeLoading()
            progressBar3.fakeLoading()

            progressBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    textView2.text = progress.toPercentage()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            progressBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    secondProgressBarState.text = progress.toPercentage()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            randomizeButton.setOnClickListener {
                progressBar2.fakeLoading(((5..25).random() * 1000).toLong())
                progressBar3.fakeLoading(((5..25).random() * 1000).toLong())
            }

        }
    }

}