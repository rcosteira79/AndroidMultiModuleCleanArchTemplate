package com.rcosteira.core.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun <Type : Any, ListType : List<Type>, MappedType : Any> Flow<ListType>.mapListElements(mapper: (Type) -> MappedType): Flow<List<MappedType>> {
    return this.map { list ->
        list.map { mapper(it) }
    }
}