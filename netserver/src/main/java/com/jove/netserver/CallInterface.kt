package com.jove.netserver

import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface CallInterface {
    @FormUrlEncoded
    @POST
    fun doHttpPost(
        @Url url: String, @FieldMap params: Map<String, @JvmSuppressWildcards Any>
    ): Observable<ResponseBody>

    @GET
    fun doHttpGet(
        @Url url: String, @QueryMap params: Map<String, @JvmSuppressWildcards Any>
    ): Observable<ResponseBody>
}