package casa.falconer.toys.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import casa.falconer.toys.SharedPreferencesProvider

class SettingsViewModel  : ViewModel() {
    var prefs:SharedPreferencesProvider = SharedPreferencesProvider()
    var voicePitch = prefs.getVoicePitch()
    var voiceSpeed = prefs.getVoiceSpeed()
    var selectedVoiceName = prefs.getSelectedVoiceName()
    var voiceOptions = prefs.getVoiceOptions()
    var voiceOptionsHashToNameMap = mutableMapOf<Int, String>()
    var voiceOptionsNameToHashMap = mutableMapOf<String, Int>()

    init {
        for (voiceOption in voiceOptions) {
            voiceOptionsHashToNameMap[voiceOption.hashCode()] = voiceOption
            voiceOptionsNameToHashMap[voiceOption] = voiceOption.hashCode()
        }
    }

    fun getSelectedVoiceId() : Int? {
        return selectedVoiceName?.let {
            voiceOptionsNameToHashMap[it]
        } ?: voiceOptionsNameToHashMap[voiceOptions[0]]
    }

    fun setSelectedVoiceId(id:Int) {
        selectedVoiceName = voiceOptionsHashToNameMap[id]
    }

    fun saveOptions() {
        Log.d(TAG, "saving options")
        prefs.setVoicePitch(voicePitch)
        prefs.setVoiceSpeed(voiceSpeed)
        selectedVoiceName?.let {
            prefs.setSelectedVoiceName(it)
        }
    }

    companion object {
        val TAG: String = SettingsViewModel::class.java.simpleName
    }
}