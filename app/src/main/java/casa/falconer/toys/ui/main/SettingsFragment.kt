package casa.falconer.toys.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import casa.falconer.toys.R
import com.google.android.material.slider.Slider

class SettingsFragment : Fragment() {

    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val voicesRadioGroup = view.findViewById<RadioGroup>(R.id.voiceSelectionRadios)
        voicesRadioGroup.orientation = RadioGroup.VERTICAL
        for (voicename in viewModel.voiceOptions) {
            val rb = RadioButton(context)
            rb.text = voicename
            rb.id = voicename.hashCode()
            voicesRadioGroup.addView(rb)
        }
        viewModel.getSelectedVoiceId()?.let {
            voicesRadioGroup.check(it)
        }
        voicesRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            viewModel.setSelectedVoiceId(checkedId)
            Log.d(TAG, "voice changed to ${viewModel.selectedVoiceName}")
        }

        val pitchSlicer = view.findViewById<Slider>(R.id.voicePitchSlider)
        pitchSlicer.value = viewModel.voicePitch
        pitchSlicer.addOnChangeListener { slider, value, fromUser ->
            Log.d(TAG, "pitch changed to $value")
            viewModel.voicePitch = value
        }

        val speedSlicer = view.findViewById<Slider>(R.id.voiceSpeedSlider)
        speedSlicer.value = viewModel.voicePitch
        speedSlicer.addOnChangeListener { slider, value, fromUser ->
            Log.d(TAG, "speed changed to $value")
            viewModel.voiceSpeed = value
        }

        val saveButton: AppCompatButton = view.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            viewModel.saveOptions()
            findNavController().navigateUp()
        }

        return view
    }

}