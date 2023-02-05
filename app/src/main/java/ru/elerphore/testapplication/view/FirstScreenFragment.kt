package ru.elerphore.testapplication.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import ru.elerphore.testapplication.AnimatedProgressBar
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.adapter.ProgressBarAdapter
import ru.elerphore.testapplication.databinding.CustomAlertDialogBinding
import ru.elerphore.testapplication.databinding.FirstScreenBinding

class FirstScreenFragment : Fragment(R.layout.first_screen) {

    lateinit var animatedProgressBar: AnimatedProgressBar
    lateinit var lottieAnimator: LottieAnimationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(FirstScreenBinding.bind(view)) {
            goButton.setOnClickListener { findNavController().navigate(R.id.action_FirstScreen_to_SecondScreen) }


            animatedProgressBar = AnimatedProgressBar(progressBar)
            lottieAnimator = animationLottie

            animatedProgressBar.fakeLoading()
            progressBar.setOnSeekBarChangeListener(ProgressBarAdapter(progressBarPercentage))

            startAnimationButton.setOnClickListener { lottieAnimator.resumeAnimation() }
            stopAnimationButton.setOnClickListener { lottieAnimator.pauseAnimation() }
            hideShowAnimationButton.setOnClickListener { lottieAnimator.isVisible = !lottieAnimator.isVisible }

            customerAlert.setOnClickListener {
                CustomAlertDialogBinding.inflate(layoutInflater).apply {
                    with(AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialog).setView(root).create()) {
                        closeButton.setOnClickListener { cancel() }
                        setCancelable(false)
                        show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        animatedProgressBar.resume()
        lottieAnimator.resumeAnimation()
    }

    override fun onPause() {
        super.onPause()
        animatedProgressBar.pause()
        lottieAnimator.pauseAnimation()
    }

}