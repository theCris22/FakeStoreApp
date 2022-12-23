package com.crisnavarro.fakestore.data.network.models

import java.io.Serializable

data class Rating(
    val count: Int,
    val rate: Double
): Serializable