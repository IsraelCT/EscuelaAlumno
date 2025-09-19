package com.cg.escuelaalumno.di

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
// 👇 Esto crea la extensión context.dataStore
val Context.dataStore by preferencesDataStore(name = "user_prefs")


val USER_TOKEN_KEY = stringPreferencesKey("user_token")
val ID_ALUMNO_KEY = stringPreferencesKey("id_alumno")