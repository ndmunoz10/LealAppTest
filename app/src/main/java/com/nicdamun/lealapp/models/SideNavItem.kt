package com.nicdamun.lealapp.models

import androidx.annotation.DrawableRes

data class SideNavItem (
    val id: Int,
    val itemName: String,
    @DrawableRes val resourceId: Int
)