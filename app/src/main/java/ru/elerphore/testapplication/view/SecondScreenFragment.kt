package ru.elerphore.testapplication.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.adapter.ProgressBarAdapter
import ru.elerphore.testapplication.adapter.ReviewRecyclerAdapter
import ru.elerphore.testapplication.databinding.SecondScreenBinding
import ru.elerphore.testapplication.db.entity.ReviewDBEntity
import ru.elerphore.testapplication.db.entity.dtoEntity
import ru.elerphore.testapplication.extension.fakeLoading
import ru.elerphore.testapplication.extension.generateSecondsInRange
import ru.elerphore.testapplication.viewmodel.SecondScreenViewModel

class SecondScreenFragment : Fragment(R.layout.second_screen) {

    lateinit var secondScreenViewModel: SecondScreenViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondScreenViewModel = ViewModelProvider(requireActivity())[SecondScreenViewModel::class.java]
        secondScreenViewModel.fetchCountries()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(SecondScreenBinding.bind(view)) {

            secondScreenViewModel.getCountries().observe(requireActivity()) {
                it.map(ReviewDBEntity::dtoEntity).also {
                    with(ReviewRecyclerAdapter(requireActivity(), it)) {
                        reviewsRecycleView.adapter = this
                        reviewsRecycleView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayout.HORIZONTAL, false)
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