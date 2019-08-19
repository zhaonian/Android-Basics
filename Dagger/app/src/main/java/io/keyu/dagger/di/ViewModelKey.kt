package io.keyu.dagger.di

import androidx.lifecycle.ViewModel
import dagger.MapKey

import java.lang.annotation.*
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
