package com.example.domx.robot

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Main controller for NAO Robot
 * Provides high-level methods for robot control
 */
class RobotController {

    private lateinit var apiService: NAOApiService
    private var baseUrl: String = ""

    /**
     * Initialize connection to NAO robot
     * @param robotBaseUrl Base URL of the robot (e.g., http://192.168.1.100:8080)
     */
    fun initialize(robotBaseUrl: String) {
        baseUrl = if (robotBaseUrl.endsWith("/")) robotBaseUrl else "$robotBaseUrl/"

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(NAOApiService::class.java)
    }

    // ==================== Movement Methods ====================

    suspend fun moveForward(distance: Float = 0.5f, speed: Float = 0.5f) {
        apiService.moveToward(x = distance, y = 0f)
    }

    suspend fun moveBackward(distance: Float = 0.5f, speed: Float = 0.5f) {
        apiService.moveToward(x = -distance, y = 0f)
    }

    suspend fun turnLeft(angle: Float = 0.5f, speed: Float = 0.5f) {
        apiService.moveToward(x = 0f, y = angle)
    }

    suspend fun turnRight(angle: Float = 0.5f, speed: Float = 0.5f) {
        apiService.moveToward(x = 0f, y = -angle)
    }

    suspend fun stopMovement() {
        apiService.stopMove()
    }

    suspend fun moveTo(x: Float, y: Float, theta: Float) {
        apiService.moveTo(x, y, theta)
    }

    // ==================== Posture Methods ====================

    suspend fun setPosture(postureName: String) {
        // Valid postures: Stand, Sit, SitRelax, StandInit, Crouch, etc.
        apiService.goToPosture(postureName, maxSpeedFraction = 0.5f)
    }

    suspend fun stand() {
        setPosture("Stand")
    }

    suspend fun sit() {
        setPosture("Sit")
    }

    suspend fun sitRelax() {
        setPosture("SitRelax")
    }

    // ==================== Animation Methods ====================

    suspend fun playAnimation(animationName: String) {
        // Common animations: wave, dance, hello, etc.
        // Format: "animations/[category]/[animation_name]"
        val fullAnimationPath = when (animationName.lowercase()) {
            "wave" -> "animations/gestures/wave"
            "dance" -> "animations/gestures/dance"
            "hello" -> "animations/gestures/hello"
            "victory" -> "animations/gestures/victory"
            else -> "animations/gestures/$animationName"
        }
        apiService.startBehavior(fullAnimationPath)
    }

    suspend fun stopAnimation() {
        apiService.stopBehavior()
    }

    // ==================== Speech Methods ====================

    suspend fun speak(text: String) {
        apiService.say(text)
    }

    // ==================== Status Methods ====================

    suspend fun getRobotStatus(): RobotStatus {
        val response = apiService.getRobotStatus()
        return response.body() ?: throw Exception("Failed to get robot status")
    }

    suspend fun getRobotPosition(): RobotPosition {
        val response = apiService.getRobotPosition()
        return response.body() ?: throw Exception("Failed to get robot position")
    }
}
