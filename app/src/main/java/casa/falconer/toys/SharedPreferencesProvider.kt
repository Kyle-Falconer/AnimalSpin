package casa.falconer.toys

import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit

class SharedPreferencesProvider {
    private val context = AnimalSpinApp.applicationContext()

    fun setVoicePitch(pitch: Float) {
        val prefs = context.getSharedPreferences(ANIMAL_SPIN_MAIN_PREFS, MODE_PRIVATE)
        prefs.edit {
            putFloat(VOICE_PITCH, pitch)
        }
    }

    fun getVoicePitch(): Float {
        val prefs = context.getSharedPreferences(ANIMAL_SPIN_MAIN_PREFS, MODE_PRIVATE)
        return prefs.getFloat(VOICE_PITCH, DEFAULT_VOICE_PITCH)
    }

    fun setVoiceSpeed(speed: Float) {
        val prefs = context.getSharedPreferences(ANIMAL_SPIN_MAIN_PREFS, MODE_PRIVATE)
        prefs.edit {
            putFloat(VOICE_SPEED, speed)
        }
    }

    fun getVoiceSpeed(): Float {
        val prefs = context.getSharedPreferences(ANIMAL_SPIN_MAIN_PREFS, MODE_PRIVATE)
        return prefs.getFloat(VOICE_SPEED, DEFAULT_VOICE_SPEED)
    }

    fun setSelectedVoiceName(voiceName: String) {
        val prefs = context.getSharedPreferences(ANIMAL_SPIN_MAIN_PREFS, MODE_PRIVATE)
        prefs.edit {
            putString(SELECTED_VOICE_NAME, voiceName)
        }
    }

    fun getSelectedVoiceName(): String? {
        val prefs = context.getSharedPreferences(ANIMAL_SPIN_MAIN_PREFS, MODE_PRIVATE)
        return prefs.getString(SELECTED_VOICE_NAME, DEFAULT_VOICE_NAME)
    }

    fun getVoiceOptions(): List<String> {
        val prefs = context.getSharedPreferences(ANIMAL_SPIN_MAIN_PREFS, MODE_PRIVATE)
        val opts =  prefs.getStringSet(VOICE_OPTIONS, mutableSetOf<String>())
        return opts!!.toList().sorted()
    }

    fun setVoiceOptions(voices: List<String>) {
        val prefs = context.getSharedPreferences(ANIMAL_SPIN_MAIN_PREFS, MODE_PRIVATE)
        prefs.edit {
            putStringSet(VOICE_OPTIONS, voices.toSet())
        }
    }

    companion object {
        private const val ANIMAL_SPIN_MAIN_PREFS = "animal_spin_prefs"
        private const val VOICE_PITCH = "voice_pitch"
        private const val VOICE_SPEED = "voice_speed"
        private const val SELECTED_VOICE_NAME = "voice_name"
        private const val VOICE_OPTIONS = "voice_options"
        private const val DEFAULT_VOICE_SPEED = 0.6f
        private const val DEFAULT_VOICE_PITCH = 0.6f
        private const val DEFAULT_VOICE_NAME = "en-us-x-iom-local"
    }
}