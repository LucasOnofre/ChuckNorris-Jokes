package com.onoffrice.norrisJokes

import androidx.multidex.BuildConfig

object Constants {
    const val PACKAGE_NAME = BuildConfig.APPLICATION_ID

    /** COMMON ERRORS **/
    const val NETWORK_ERROR  = "Sem conexão a internet"

    const val SELECTED_CATEGORY: String = "${PACKAGE_NAME}SELECTED_CATEGORY"


}