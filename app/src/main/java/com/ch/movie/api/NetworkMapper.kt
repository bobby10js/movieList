package com.ch.movie.api

import com.ch.movie.model.Movies
import com.ch.movie.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() :EntityMapper<MoviesNetworkEntity,Movies> {
    override fun mapFromEntity(entity: MoviesNetworkEntity): Movies = Movies(entity.page,entity.results)

    override fun mapToEntity(model: Movies): MoviesNetworkEntity = MoviesNetworkEntity(model.page,model.results)

    fun mapFromEntityList(entities: List<MoviesNetworkEntity>) = entities.map{ mapFromEntity(it) }
}