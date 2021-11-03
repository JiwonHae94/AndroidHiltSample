package com.jiwon.examplehiltdagger.api

import com.jiwon.examplehiltdagger.data.model.Account
import retrofit2.http.POST
import retrofit2.http.Url

interface UserService {
    @POST
    suspend fun getAllMembers(@Url url : String?) : List<Account>

}