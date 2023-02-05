package ru.elerphore.testapplication.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.adapter.ProgressBarAdapter
import ru.elerphore.testapplication.databinding.CustomAlertDialogBinding
import ru.elerphore.testapplication.databinding.FirstScreenBinding
import ru.elerphore.testapplication.extension.fakeLoading

class FirstScreenFragment : Fragment(R.layout.first_screen) {
    private lateinit var firstScreenFragment: FirstScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(FirstScreenBinding.bind(view)) {
            goButton.setOnClickListener { findNavController().navigate(R.id.action_FirstScreen_to_SecondScreen) }

            progressBar.fakeLoading()

            progressBar.setOnSeekBarChangeListener(ProgressBarAdapter(progressBarPercentage))

            startAnimationButton.setOnClickListener { animationLottie.playAnimation() }
            stopAnimationButton.setOnClickListener { animationLottie.cancelAnimation() }
            hideShowAnimationButton.setOnClickListener { animationLottie.isVisible = !animationLottie.isVisible }

            customerAlert.setOnClickListener {
                CustomAlertDialogBinding.inflate(layoutInflater).apply {
                    with(AlertDialog.Builder(requireContext()).setView(root).create()) {
                        closeButton.setOnClickListener { cancel() }
                        setCancelable(false)
                        show()
                    }
                }
            }

        }
    }
}