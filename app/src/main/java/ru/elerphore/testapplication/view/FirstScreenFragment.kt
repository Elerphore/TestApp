package ru.elerphore.testapplication.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.databinding.CustomAlertDialogBinding
import ru.elerphore.testapplication.databinding.FirstScreenBinding
import ru.elerphore.testapplication.extension.toPercentage

class FirstScreenFragment : Fragment(R.layout.first_screen) {
    private lateinit var firstScreenFragment: FirstScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(FirstScreenBinding.bind(view)) {
            goButton.setOnClickListener { findNavController().navigate(R.id.action_FirstScreen_to_SecondScreen) }
            progressBar.fakeLoading()

            progressBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    progressBarPercentage.text = progress.toPercentage()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            startAnimationButton.setOnClickListener {
                animationLottie.playAnimation()
            }

            stopAnimationButton.setOnClickListener {
                animationLottie.cancelAnimation()
            }

            hideShowAnimationButton.setOnClickListener {
                animationLottie.isVisible = !firstScreenFragment.animationLottie.isVisible
            }

            customerAlert.setOnClickListener {
                var dialog: AlertDialog? = null
                val builder = AlertDialog.Builder(requireContext())
                val view = CustomAlertDialogBinding.inflate(layoutInflater)
                view.closeButton.setOnClickListener {
                    dialog?.cancel()
                }
                builder.setView(view.root)

                dialog = builder.create()
                dialog.setCancelable(false)
                dialog.show()
            }

        }
    }
}