package com.jiwon.examplehiltdagger.repository

import com.jiwon.examplehiltdagger.api.UserService
import com.jiwon.examplehiltdagger.data.model.Account
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val userService : UserService
) {
    suspend fun requestUsers()  =
        flow{
            emit(userService.getAllMembers(GET_ALL))
        }

    companion object{
        private const val DOMAIN = "gs25"
        private const val GET_ALL = "$DOMAIN/device/get_all_member"
    }
}