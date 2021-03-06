package br.com.dio.subscribersapp.repository

import androidx.lifecycle.LiveData
import br.com.dio.subscribersapp.data.db.dao.SubscriberDao
import br.com.dio.subscribersapp.data.db.entity.SubscriberEntity

class DatabaseDataSource(
    private val subscriberDao: SubscriberDao
) : SubscriberRepository {

    override suspend fun insertSubscriber(name: String, email: String): Long {
        val subscriber = SubscriberEntity(
            name = name,
            email = email
        )

        return subscriberDao.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntity(
            name = name,
            email = email
        )

        subscriberDao.insert(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscriberDao.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDao.deleteAll()
    }

    override suspend fun getAllSubscribers(): List<SubscriberEntity> {
        return subscriberDao.getAll()
    }

}