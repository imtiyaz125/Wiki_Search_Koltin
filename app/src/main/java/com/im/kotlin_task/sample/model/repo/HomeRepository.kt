package com.im.kotlin_task.sample.model.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.im.kotlin_task.sample.model.bean.requests.GetWikiSearchRequest
import com.im.kotlin_task.sample.model.bean.responses.Page
import com.im.kotlin_task.sample.model.bean.responses.Query
import com.im.kotlin_task.sample.model.bean.responses.WikiSearchResponse
import com.im.kotlin_task.sample.model.local.preference.PreferenceConstants
import com.im.kotlin_task.sample.model.local.preference.PreferenceHelper
import com.im.kotlin_task.sample.model.local.room_db.dao.SearchDao
import com.im.kotlin_task.sample.model.remote.ApiResponse
import com.im.kotlin_task.sample.model.remote.ApiServices
import com.im.kotlin_task.sample.model.remote.DataFetchCall
import com.im.kotlin_task.sample.model.remote.ReflectionUtil
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response


class HomeRepository(
    private val apiServices: ApiServices,
    private val preferences: PreferenceHelper,
    private val contactDao: SearchDao
) : KoinComponent {

    /** ReflectionUtil get Object using koin DI
     * used to convert  request Model class to HashMap */
    private val reflectionUtil: ReflectionUtil by inject()

    fun getWikiSearchedPages(
        req: GetWikiSearchRequest,
        response: MutableLiveData<ApiResponse<WikiSearchResponse>>
    ) {
        object : DataFetchCall<WikiSearchResponse>(response) {

            /*** if return true loadFromDB called else createCallAsync is called */
            override fun shouldFetchFromDB(): Boolean {
                return (System.currentTimeMillis().minus(
                    preferences[PreferenceConstants.LAST_API_CALL_TIME,
                            0L]!!
                )) < 60000
            }

            /*** called when shouldFetchFromDB is true */
            override fun loadFromDB(): WikiSearchResponse? {
                val queryUpdated = Query()
                val allStoredData = contactDao.retrieveAllPages() as? ArrayList<Page>
                val searchedList = ArrayList<Page>()
                allStoredData?.forEach {
                    if (it.title!!.contains(req.gpssearch, ignoreCase = true)) {
                        Log.e("<><>", it.title)
                        searchedList.add(it)
                    }
                }
                queryUpdated.apply {
                    pages = searchedList
                }

                return WikiSearchResponse().apply {
                    query = queryUpdated
                }
            }

            /*** called when shouldFetchFromDB is false */
            override suspend fun createCallAsync(): Response<WikiSearchResponse> {
                return apiServices.getWikiSearch(
                    reflectionUtil.convertPojoToMap(
                        req
                    )
                )
            }

            /***  called when  API Response is success and before post response to livedata */
            override fun saveResult(result: WikiSearchResponse) {
                contactDao.insertAllPages(result.query?.pages!!)
                preferences.put(PreferenceConstants.LAST_API_CALL_TIME, System.currentTimeMillis())
            }
        }.execute()
        /*** execute function is used to call the above dataFetch Request from network/DB */
    }


}