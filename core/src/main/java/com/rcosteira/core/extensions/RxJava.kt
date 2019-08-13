package com.rcosteira.core.extensions

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun <Type : Any, ListType : List<Type>, MappedType : Any> Flowable<ListType>.mapListElements(mapper: (Type) -> MappedType): Flowable<List<MappedType>> {
    return this.map { list ->
        list.map { mapper(it) }
    }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}