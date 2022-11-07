package casa.falconer.toys

import android.app.Application
import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


class AnimalSpinApp : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.start()
        }
    }

    companion object {
        private var instance: AnimalSpinApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}