package com.kotlin.mvvm.unittestdemo.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStore:DataStore<Preferences>
) :LocalDataSource {
    private object PreferencesKeys{
        val isFirstOpenState = booleanPreferencesKey("isFirstOpen")
    }

    override fun getIsAppFirstOpenState(): Flow<Boolean> =
        dataStore.data
            .catch { e->
                if(e is IOException){
                    emit(emptyPreferences())
                }else{
                    throw e
                }
            }
            .map {preference->
                preference[PreferencesKeys.isFirstOpenState] ?: true
            }

    override suspend fun saveAppFirstOpenState(isFirstOpen: Boolean) {
        dataStore.edit {preference->
            preference[PreferencesKeys.isFirstOpenState] = isFirstOpen
        }

    }
}