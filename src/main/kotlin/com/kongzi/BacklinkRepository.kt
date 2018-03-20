package com.kongzi

import com.kongzi.dagger.NetManager
import com.kongzi.model.Baqlink
import io.reactivex.Observable
import javax.inject.Inject

class BacklinkRepository @Inject constructor(private val netManager: NetManager) {

    private val localDataSource = BacklinkLocalDataSource()
    private val remoteDataSource = BacklinkRemoteDataSource()

    fun getRepositories(): Observable<List<Baqlink>> {

        netManager.isConnectedToInternet?.let {
            if (it) {
                //todo save those data to local data store
                return remoteDataSource.getBacklinks().flatMap {
                    return@flatMap localDataSource.saveBacklinks(it)
                            .toSingleDefault(it)
                            .toObservable()
                }
            }
        }

        return localDataSource.getBacklinks()
    }
}