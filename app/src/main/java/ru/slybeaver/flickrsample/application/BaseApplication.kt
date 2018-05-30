package ru.slybeaver.flickrsample.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration
import ru.slybeaver.flickrsample.utils.AppSettings

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        AppSettings.instance.initInstance(applicationContext)

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("flickrsample.realm")
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}