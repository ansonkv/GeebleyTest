package com.jeebley.ansongeebleytest.networking

import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("status")
    var status: String = ""

    constructor() {}

    constructor(status: String) {
        this.status = status
    }
}
