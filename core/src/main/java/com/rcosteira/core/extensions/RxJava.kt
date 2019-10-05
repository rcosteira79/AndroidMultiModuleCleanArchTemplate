package com.rcosteira.core.extensions

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <Type : Any, ListType : List<Type>, MappedType : Any> Flowable<ListType>.mapListElements(mapper: (Type) -> MappedType): Flowable<List<MappedType>> {
    return this.map { list ->
        list.map { mapper(it) }
    }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <Type : Any, ListType : List<Type>, MappedType : Any> Flow<ListType>.mapListElements(mapper: (Type) -> MappedType): Flow<List<MappedType>> {
    return this.map { list ->
        list.map { mapper(it) }
    }
}