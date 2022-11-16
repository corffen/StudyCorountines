package com.gordon

import kotlinx.coroutines.*


//挂起函数
// ↓
suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
        log("getUserInfo")
    }
    return "BoyCoder"
}

//挂起函数
// ↓
suspend fun getFriendList(user: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
        log(user)
    }
    return "Tom, Jack"
}

//挂起函数
// ↓
suspend fun getFeedList(list: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
        log(list)
    }
    return "{FeedList..}"
}

//fun main() = runBlocking {
//    val user = getUserInfo()
//    log(user)
//    val friendList = getFriendList(user)
//    log(friendList)
//    val feedList = getFeedList(friendList)
//    log(feedList)
//}

