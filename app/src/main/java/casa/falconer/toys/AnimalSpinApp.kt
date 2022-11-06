package casa.falconer.toys

import android.app.Application
import android.content.Context

class AnimalSpinApp : Application() {

    init {
        instance = this
    }



    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private var instance: AnimalSpinApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}