package com.devlabs.data.mapper

abstract class BaseMapper<IN, OUT> {
    abstract fun transform(entity: IN): OUT
}