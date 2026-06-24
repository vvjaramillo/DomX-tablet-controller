package com.example.domx.robot

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Retrofit interface for NAO Robot REST API
 * Documentation: https://developer.softbankrobotics.com/nao6/index
 */
interface NAOApiService {

    // Motion endpoints
    @POST("motion/moveTo")
    suspend fun moveTo(
        @Query("x") x: Float,
        @Query("y") y: Float,
        @Query("theta") theta: Float
    ): Response<Void>

    @POST("motion/moveToward")
    suspend fun moveToward(
        @Query("x") x: Float,
        @Query("y") y: Float
    ): Response<Void>

    @POST("motion/stopMove")
    suspend fun stopMove(): Response<Void>

    @POST("motion/setAngles")
    suspend fun setAngles(
        @Query("names") names: String,
        @Query("angles") angles: String,
        @Query("fractionMaxSpeed") fractionMaxSpeed: Float = 0.5f
    ): Response<Void>

    // Posture endpoints
    @POST("posture/goToPosture")
    suspend fun goToPosture(
        @Query("postureName") postureName: String,
        @Query("maxSpeedFraction") maxSpeedFraction: Float = 0.5f
    ): Response<Void>

    // Animation endpoints
    @POST("behavior/startBehavior")
    suspend fun startBehavior(
        @Query("behaviorName") behaviorName: String
    ): Response<Void>

    @POST("behavior/stopBehavior")
    suspend fun stopBehavior(): Response<Void>

    // Text-to-Speech endpoints
    @POST("tts/say")
    suspend fun say(
        @Query("text") text: String
    ): Response<Void>

    // Robot status endpoints
    @GET("robot/getStatus")
    suspend fun getRobotStatus(): Response<RobotStatus>

    @GET("motion/getRobotPosition")
    suspend fun getRobotPosition(): Response<RobotPosition>
}

data class RobotStatus(
    val battery: Int,
    val isMoving: Boolean,
    val currentPosture: String
)

data class RobotPosition(
    val x: Float,
    val y: Float,
    val theta: Float
)
