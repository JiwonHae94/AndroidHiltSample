package com.jiwon.examplehiltdagger.api

import com.jiwon.examplehiltdagger.data.model.Account
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.http.POST
import retrofit2.http.Url
import javax.inject.Singleton

interface UserService {
    @POST
    suspend fun getAllMembers(@Url url : String?) : List<Account>

}