package com.example.lab8.layouts.data.repository

import com.example.lab8.layouts.data.local.daos.LocationDao
import com.example.lab8.layouts.data.local.entity.LocationEntity

class LocationRepository(private val locationDao: LocationDao) {

    // Sincroniza las ubicaciones, insertando todos los registros en la tabla.
    suspend fun syncLocations(locations: List<LocationEntity>) {
        locationDao.insertAll(locations)
    }

    // Obtiene todas las ubicaciones de la base de datos.
    suspend fun getAllLocations(): List<LocationEntity> {
        return locationDao.getAllLocations()
    }

    // Obtiene una ubicación específica por su ID.
    suspend fun getLocationById(id: Int): LocationEntity? {
        return locationDao.getLocationById(id)
    }
}
