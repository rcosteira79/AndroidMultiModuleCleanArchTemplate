package com.rcosteira.core.extensions

import io.reactivex.Flowable

fun <Type : Any, ListType : List<Type>, MappedType : Any> Flowable<ListType>.mapListElements(mapper: (Type) -> MappedType): Flowable<List<MappedType>> {
    return this.map { list ->
        list.map { mapper(it) }
    }
}