package ru.elerphore.testapplication.view

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.adapter.ProgressBarAdapter
import ru.elerphore.testapplication.adapter.ReviewRecyclerAdapter
import ru.elerphore.testapplication.databinding.SecondScreenBinding
import ru.elerphore.testapplication.db.entity.ReviewDBEntity
import ru.elerphore.testapplication.db.entity.dtoEntity
import ru.elerphore.testapplication.extension.fakeLoading
import ru.elerphore.testapplication.extension.generateSecondsInRange
import ru.elerphore.testapplication.extension.toPercentage
import ru.elerphore.testapplication.viewmodel.SecondScreenViewModel

class SecondScreenFragment : Fragment(R.layout.second_screen) {

    lateinit var secondScreenViewModel: SecondScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondScreenViewModel = ViewModelProvider(requireActivity())[SecondScreenViewModel::class.java]
        secondScreenViewModel.fetchReviews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(SecondScreenBinding.bind(view)) {

            object : CountDownTimer(3600 * 1000, 1) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsUntilFinished = millisUntilFinished / 1000

                    hours.text = (secondsUntilFinished / 3600).toString()
                    minutes.text = ((secondsUntilFinished % 3600) / 60).toString()
                    seconds.text = (secondsUntilFinished % 60).toString()
                }

                override fun onFinish() {

                }

            }.start()

            secondScreenViewModel.currentLoadingState.observe(requireActivity()) { progress ->
                secondScreenViewModel.getReviews().observe(requireActivity()) { list ->
                    this.progressBar4.progress = (progress.toDouble() / list.size.toDouble() * 100.0).toInt()
                    this.loadingFromServerPercentage.text = (progress.toDouble() / list.size.toDouble() * 100.0).toInt().toPercentage()
                }
            }

            secondScreenViewModel.getReviews().observe(requireActivity()) {
                it.map(ReviewDBEntity::dtoEntity).also {

                    it.forEach { entity ->
                        entity.imageView = ImageView(requireActivity())
                        Glide.with(requireActivity()).load(entity.image)
                        secondScreenViewModel.currentLoadingState.value = secondScreenViewModel.currentLoadingState.value?.plus(1)
                    }


                    with(ReviewRecyclerAdapter(secondScreenViewModel, requireActivity(), it)) {
                        reviewsRecycleView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayout.HORIZONTAL, false)
                        reviewsRecycleView.adapter = this
                        reviewsRecycleView.setHasFixedSize(true)
                    }
                }
            }

            backArrow.setOnClickListener { findNavController().navigate(R.id.action_SecondScreen_to_FirstScreen) }

            progressBar2.fakeLoading(Long.generateSecondsInRange())
            progressBar3.fakeLoading(Long.generateSecondsInRange())

            progressBar2.setOnSeekBarChangeListener(ProgressBarAdapter(textView2))
            progressBar3.setOnSeekBarChangeListener(ProgressBarAdapter(secondProgressBarState))

            randomizeButton.setOnClickListener {
                progressBar2.fakeLoading(Long.generateSecondsInRange())
                progressBar3.fakeLoading(Long.generateSecondsInRange())
            }
        }
    }
}
