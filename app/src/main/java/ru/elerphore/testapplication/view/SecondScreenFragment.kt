package ru.elerphore.testapplication.view

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.elerphore.testapplication.AnimatedProgressBar
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.adapter.ProgressBarAdapter
import ru.elerphore.testapplication.databinding.SecondScreenBinding
import ru.elerphore.testapplication.extension.fakeLoading
import ru.elerphore.testapplication.extension.generateSecondsInRange
import ru.elerphore.testapplication.viewmodel.SecondScreenViewModel

class SecondScreenFragment : Fragment(R.layout.second_screen) {

    lateinit var secondScreenViewModel: SecondScreenViewModel

    lateinit var firstAnimatedProgressBar: AnimatedProgressBar
    lateinit var secondAnimatedProgressBar: AnimatedProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondScreenViewModel = ViewModelProvider(requireActivity())[SecondScreenViewModel::class.java]
        secondScreenViewModel.fetchReviews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(SecondScreenBinding.bind(view)) {

            CountDown.init(hours, minutes, seconds)
            CountDown.start()

            secondScreenViewModel.updateProgressBar(requireActivity(), this.progressBar4, this.loadingFromServerPercentage)
            secondScreenViewModel.loadRecycleView(requireContext(), requireActivity(), reviewsRecycleView)

            backArrow.setOnClickListener { findNavController().navigate(R.id.action_SecondScreen_to_FirstScreen) }

            firstAnimatedProgressBar = AnimatedProgressBar(progressBar2)
            secondAnimatedProgressBar = AnimatedProgressBar(progressBar3)

            firstAnimatedProgressBar.fakeLoading(Long.generateSecondsInRange())
            secondAnimatedProgressBar.fakeLoading(Long.generateSecondsInRange())

            progressBar2.setOnSeekBarChangeListener(ProgressBarAdapter(textView2))
            progressBar3.setOnSeekBarChangeListener(ProgressBarAdapter(secondProgressBarState))

            randomizeButton.setOnClickListener {
                firstAnimatedProgressBar.fakeLoading(Long.generateSecondsInRange())
                secondAnimatedProgressBar.fakeLoading(Long.generateSecondsInRange())
            }
        }
    }

    override fun onResume() {
        super.onResume()

        firstAnimatedProgressBar.resume()
        secondAnimatedProgressBar.resume()
    }

    override fun onPause() {
        super.onPause()

        firstAnimatedProgressBar.pause()
        secondAnimatedProgressBar.pause()
    }
}

object CountDown : CountDownTimer(3600 * 1000, 1) {

    private var hours: TextView? = null
    private var minutes: TextView? = null
    private var seconds: TextView? = null

    override fun onTick(millisUntilFinished: Long) {
        val secondsUntilFinished = millisUntilFinished / 1000

        hours!!.text = (secondsUntilFinished / 3600).toString()
        minutes!!.text = ((secondsUntilFinished % 3600) / 60).toString()
        seconds!!.text = (secondsUntilFinished % 60).toString()
    }

    override fun onFinish() { }

    fun init(hours: TextView, minutes: TextView, seconds: TextView) {
        this.hours = hours
        this.minutes = minutes
        this.seconds = seconds
    }

}