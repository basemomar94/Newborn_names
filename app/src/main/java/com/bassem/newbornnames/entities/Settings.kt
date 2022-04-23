package com.bassem.newbornnames.entities

import java.sql.Date
import java.sql.Timestamp

data class Settings(
    var isUpdate: Boolean? = true,
    var timestamp: com.google.firebase.Timestamp? = null
)
