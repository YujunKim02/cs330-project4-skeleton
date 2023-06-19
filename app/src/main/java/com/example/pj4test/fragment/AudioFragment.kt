package com.example.pj4test.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pj4test.MainActivity
import com.example.pj4test.ProjectConfiguration
import com.example.pj4test.audioInference.SnapClassifier
import com.example.pj4test.databinding.FragmentAudioBinding

class AudioFragment: Fragment(), SnapClassifier.DetectorListener {
    private val TAG = "AudioFragment"

    private var _fragmentAudioBinding: FragmentAudioBinding? = null
    private var resultButton: Button? = null
    private var noiseScore: Float = 0f
    private var snoreScore: Float = 0f

    private val fragmentAudioBinding
        get() = _fragmentAudioBinding!!

    // classifiers
    lateinit var snapClassifier: SnapClassifier

    // views
    lateinit var snapView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentAudioBinding = FragmentAudioBinding.inflate(inflater, container, false)

        return fragmentAudioBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snapView = fragmentAudioBinding.SnapView
//        snapView.setBackgroundColor(Color.TRANSPARENT)
        resultButton = Button(activity)
        snapClassifier = SnapClassifier()
        snapClassifier.initialize(requireContext())
        snapClassifier.setDetectorListener(this)
    }

    override fun onPause() {
        super.onPause()
        snapClassifier.stopInferencing()
    }

    override fun onResume() {
        super.onResume()
        snapClassifier.startInferencing()
    }

    fun onResultsMultiple(score: ArrayList<Float>) {
        activity?.runOnUiThread {
//            if (score > SnapClassifier.THRESHOLD) {
//                snapView.text = "SNAP"
//                snapView.setBackgroundColor(ProjectConfiguration.activeBackgroundColor)
//                snapView.setTextColor(ProjectConfiguration.activeTextColor)
//            } else {
//                snapView.text = "NO SNAP"
//                snapView.setBackgroundColor(ProjectConfiguration.idleBackgroundColor)
//                snapView.setTextColor(ProjectConfiguration.idleTextColor)
//            }
            val snapScore = score[0]
            snoreScore = (0.2f*score[1] + 0.8f*snoreScore)
            noiseScore = (0.2f*score[2] + 0.8f*noiseScore)

//            snapView.text = "snore" + "%.2f".format(snoreScore) + "\n" + "noise" + "%.2f".format(noiseScore)
            (activity as MainActivity).progressBar?.progress = (noiseScore!!*100).toInt()
            (activity as MainActivity).snoreProgressBar?.progress = (snoreScore!!*100).toInt()
            if (snoreScore > THRESHOLD) {
                (activity as MainActivity).vibrate()
            }
        }
    }

    override fun onResults(score: Float) {
//        activity?.runOnUiThread {
//            if (score > SnapClassifier.THRESHOLD) {
//                snapView.text = "SNAP"
//                snapView.setBackgroundColor(ProjectConfiguration.activeBackgroundColor)
//                snapView.setTextColor(ProjectConfiguration.activeTextColor)
//            } else {
//                snapView.text = "NO SNAP"
//                snapView.setBackgroundColor(ProjectConfiguration.idleBackgroundColor)
//                snapView.setTextColor(ProjectConfiguration.idleTextColor)
//            }
//        }
    }
    companion object {
        const val THRESHOLD = 0.5f
    }
}
