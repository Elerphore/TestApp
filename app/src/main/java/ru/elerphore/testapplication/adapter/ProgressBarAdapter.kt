package ru.elerphore.testapplication.adapter

import android.widget.SeekBar
import android.widget.TextView
import ru.elerphore.testapplication.extension.toPercentage

class ProgressBarAdapter(private val textView: TextView) : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        textView.text = progress.toPercentage()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) { }
    override fun onStopTrackingTouch(seekBar: SeekBar?) { }

}