package com.ponykamni.spacestamploader.stamp.data.di;

import com.ponykamni.spacestamploader.stamp.data.StampRepository

interface StampFeature {

    fun getStampRepository(): StampRepository
}