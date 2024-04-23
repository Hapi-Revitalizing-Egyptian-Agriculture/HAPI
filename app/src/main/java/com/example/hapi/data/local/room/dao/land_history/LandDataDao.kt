package com.example.hapi.data.local.room.dao.land_history

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.land_history.LandData

@Dao
interface LandDataDao {
    @Upsert
    suspend fun upsert(landData: LandData)

    @Upsert
    suspend fun upsertList(landData: List<LandData>)

    @Query("DELETE FROM LandData")
    suspend fun deleteAll()

    @Query("SELECT * FROM LandData")
    suspend fun getAllLandData(): List<LandData>?

    @Query("SELECT * FROM LandData ORDER BY remote_id DESC LIMIT 1")
    suspend fun getLastLandDataByRemoteId(): LandData?
}