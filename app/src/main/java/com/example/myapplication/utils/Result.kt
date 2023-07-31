package com.example.myapplication.utils

import java.lang.Exception

sealed class Result

class Success(val msg : String) : Result()

class  Failure(val msg: String) : Result()