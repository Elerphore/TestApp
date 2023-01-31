package ru.elerphore.testapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.elerphore.testapplication.R
import ru.elerphore.testapplication.databinding.FirstScreenBinding
import ru.elerphore.testapplication.databinding.FragmentFirstBinding

class FirstScreenFragment : Fragment(R.layout.first_screen) {

    private var firstScreenFragment: FirstScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        firstScreenFragment = FirstScreenBinding.inflate(inflater, container, false)
        return firstScreenFragment!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstScreenFragment!!.goButton.setOnClickListener {
            Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show()
        }
    }
}