package com.aldredo.core.base.navigation.mediator

interface ProvideMediator {
    fun getAuthoMediator(): IAuthoMediator

    fun getHomeMediator(): IHomeMeidator

    fun getProfilePerson(): IAuthoCodeMediator

    fun getProfileMediator(): IProfilePersonMediator
}