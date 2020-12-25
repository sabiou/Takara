package dev.farouk.takara.data.db

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Farouk on 25/12/2020.
 */
@Singleton
class EventRepository @Inject constructor(private val dao: EventDao) {
    fun getEvents() = dao.getEvents()
}