package com.zuhlke.upskilling.departureboard.seku.utils

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
