package com.example.hapi.di

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.CurrentDetectionDao
import com.example.hapi.data.local.room.dao.DetectionOfHistoryDao
import com.example.hapi.data.local.room.dao.FarmerDao
import com.example.hapi.data.local.room.dao.LandDataDao
import com.example.hapi.data.remote.ApiHandler
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.api.LandApiService
import com.example.hapi.data.remote.api.LandownerApiService
import com.example.hapi.data.repository.AuthRepository
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.data.repository.DiseaseDetectionRepository
import com.example.hapi.data.repository.LandRepository
import com.example.hapi.data.repository.LandownerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApiService: AuthApiService,
        authPreference: UserDataPreference,
        apiHandler: ApiHandler
    ) = AuthRepository(
        authApiService,
        authPreference,
        apiHandler
    )

    @Provides
    @Singleton
    fun provideDetectionRepository(
        detectionApiService: DetectionApiService,
        detectionDao: CurrentDetectionDao,
        userDataPreference: UserDataPreference,
    ) = DiseaseDetectionRepository(
        detectionApiService,
        detectionDao,
        userDataPreference,
    )

    @Provides
    @Singleton
    fun provideDetectionHistoryRepository(
        detectionOfHistoryDao: DetectionOfHistoryDao,
        detectionApiService: DetectionApiService,
        userDataPreference: UserDataPreference,
        apiHandler: ApiHandler
    ) = DetectionHistoryRepository(
        detectionApiService,
        detectionOfHistoryDao,
        userDataPreference,
        apiHandler
    )

    @Provides
    @Singleton
    fun provideLandownerRepository(
        landownerApiService: LandownerApiService,
        authPreference: UserDataPreference,
        farmerDao: FarmerDao,
        apiHandler: ApiHandler
    ) = LandownerRepository(
        landownerApiService,
        authPreference,
        farmerDao,
        apiHandler
    )

    @Provides
    @Singleton
    fun provideLandRepository(
        landApiService: LandApiService,
        landDataDao: LandDataDao,
        userDataPreference: UserDataPreference
    ) = LandRepository(
        landApiService,
        landDataDao,
        userDataPreference
    )
}