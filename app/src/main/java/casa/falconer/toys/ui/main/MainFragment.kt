package casa.falconer.toys.ui.main

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.speech.tts.Voice
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import casa.falconer.toys.R
import casa.falconer.toys.SettingsActivity
import casa.falconer.toys.models.Animal
import java.util.*

class MainFragment : Fragment() {

    companion object {
        val TAG: String = MainFragment::class.java.simpleName
        fun newInstance() = MainFragment()
        var mp: MediaPlayer? = null
        var tts: TextToSpeech? = null
        val ttsLanguage = Locale.US
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        tts = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(status: Int) {
                if (status == TextToSpeech.SUCCESS) {
                    val result = tts!!.setLanguage(ttsLanguage)
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED
                    ) {
                        Log.e(TAG, "This Language is not supported")
                    } else {
                        ttsInitialized()
                    }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val animalsRV = view.findViewById<RecyclerView>(R.id.animalsRecyclerView)
//        animalsRV.layoutManager = LinearLayoutManager(context)
        animalsRV.layoutManager = GridLayoutManager(context, 2)
//        animalsRV.layoutManager = ArcLayoutManager(requireContext())

        animalsRV.adapter = AnimalsAdapter(Animal.values().asList()) { clickedAnimal ->
            viewModel.queueAnimalNoise(clickedAnimal)
            playAnimalNoise()
        }
//        val cowButton: AppCompatButton = view.findViewById(R.id.buttonCow)
//        cowButton.setOnClickListener {
//            viewModel.queueAnimalNoise(Animal.COW)
//            playAnimalNoise()
//        }
//        val donkeyButton: AppCompatButton = view.findViewById(R.id.buttonDonkey)
//        donkeyButton.setOnClickListener {
//            viewModel.queueAnimalNoise(Animal.DONKEY)
//            playAnimalNoise()
//        }
//        val frogButton: AppCompatButton = view.findViewById(R.id.buttonFrog)
//        frogButton.setOnClickListener {
//            viewModel.queueAnimalNoise(Animal.FROG)
//            playAnimalNoise()
//        }
//        val catButton: AppCompatButton = view.findViewById(R.id.buttonCat)
//        catButton.setOnClickListener {
//            viewModel.queueAnimalNoise(Animal.CAT)
//            playAnimalNoise()
//        }


        val settingsButton: AppCompatButton = view.findViewById(R.id.settingsButton)
        settingsButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        return view
    }


    fun playAnimalNoise() {
        mp?.stop()
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                Log.d(TAG, "tts started")
            }

            override fun onDone(utteranceId: String?) {
                Log.d(TAG, "tts finished")

                viewModel.currentAnimalNoise?.let { an ->
                    mp = MediaPlayer.create(context, an.noiseFile)
                    mp?.start()
                }
            }

            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) {
                Log.d(TAG, "tts error")
            }
        })
        viewModel.currentAnimalNoise?.let { an ->
            val ttsSays = getString(an.animal.tts_says)
            tts?.speak(ttsSays, TextToSpeech.QUEUE_FLUSH, null, ttsSays)
        }
    }

    fun ttsInitialized() {
        tts?.let { it ->
            it.setSpeechRate(viewModel.voiceSpeed)
            it.setPitch(viewModel.voicePitch)
            val voicesMap = it.voices.filter { voice ->
                voice.locale == ttsLanguage && voice.isNetworkConnectionRequired == false
            }.map { it.name to it }.toMap()

            val voiceNames: List<String> = voicesMap.values.map { voice -> voice.name }
            viewModel.saveVoiceOptions(voiceNames)

            viewModel.getSelectedVoiceName()?.let { selectedVoiceName ->
                it.voice = voicesMap[selectedVoiceName]
            }
        }
    }
}