package com.ballyscorp.ballylive.navigation

import kotlinx.coroutines.flow.Flow

internal interface NavEventProvider {

    val navigationEvent: Flow<NavEvent>
}
