package com.ch.movie.util

interface EntityMapper<Entity, Model> {
    fun mapFromEntity(entity: Entity) : Model
    fun mapToEntity(model: Model) :Entity
}