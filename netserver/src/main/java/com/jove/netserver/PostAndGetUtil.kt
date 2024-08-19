package com.jove.netserver

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.lang.reflect.Type

val GsonUtil by lazy { Gson() }

object PostAndGetUtil {
    fun post(url: String, paramsJson: String, onCallback:(Boolean, String)->Unit) {
        var httpUrl = url
        if (!url.startsWith("http:")) {
            httpUrl = "http://$httpUrl"
        }
        RetrofitUtil.getHttpService().doHttpPost(httpUrl, getMap(paramsJson))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Log.d("cjw", e.toString())
                    onCallback(false, e.toString())
                }

                override fun onComplete() {
                }

                override fun onNext(t: ResponseBody) {
                    Log.d("cjw", String(t.bytes()))
                    onCallback(true, String(t.bytes()))
                }
            })
    }

    fun get(url: String, paramsJson: String, onCallback:(Boolean, String)->Unit) {
        var httpUrl = url
        if (!url.startsWith("http:")) {
            httpUrl = "http://$httpUrl"
        }
        RetrofitUtil.getHttpService().doHttpGet(httpUrl, getMap(paramsJson))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Log.d("cjw", e.toString())
                    onCallback(false, e.toString())
                }

                override fun onComplete() {
                }

                override fun onNext(t: ResponseBody) {
                    Log.d("cjw", String(t.bytes()))
                    onCallback(true, String(t.bytes()))
                }
            })
    }

    /**
     * json 转map
     */
    private fun getMap(jsonString: String): Map<String, Any> {
        // 将 JSON 字符串转换为 Map<String, Object>
        val type: Type = object : TypeToken<Map<String?, Any?>?>() {}.type
        val map: Map<String, Any> = GsonUtil.fromJson(jsonString, type)
        return map
    }
}