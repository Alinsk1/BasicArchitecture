package ru.otus.basicarchitecture

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidApp
class WizardApp: Application() {
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun userInfo() = WizardCache()
}

class WizardCache @Inject constructor(){
    var userName: UserName = UserName("", "", "")
    var userAddress: UserAddress = UserAddress("", "", "")
    var interests: List<String> = listOf()
}

