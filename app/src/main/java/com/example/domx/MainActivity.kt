package com.example.domx

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.domx.databinding.ActivityMainBinding
import com.example.domx.robot.RobotController
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var robotController: RobotController
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        robotController = RobotController()

        setupUI()
    }

    private fun setupUI() {
        // Connect button
        binding.btnConnect.setOnClickListener {
            val ipAddress = binding.etRobotIp.text.toString().trim()
            if (ipAddress.isEmpty()) {
                showToast("Please enter robot IP address")
                return@setOnClickListener
            }
            connectToRobot(ipAddress)
        }

        // Movement buttons
        binding.btnForward.setOnClickListener { moveRobot("forward") }
        binding.btnBackward.setOnClickListener { moveRobot("backward") }
        binding.btnTurnLeft.setOnClickListener { moveRobot("left") }
        binding.btnTurnRight.setOnClickListener { moveRobot("right") }
        binding.btnStand.setOnClickListener { postureRobot("Stand") }
        binding.btnSit.setOnClickListener { postureRobot("Sit") }

        // Animation buttons
        binding.btnWaveHand.setOnClickListener { playAnimation("wave") }
        binding.btnDance.setOnClickListener { playAnimation("dance") }

        // Stop button
        binding.btnStop.setOnClickListener { stopRobot() }
    }

    private fun connectToRobot(ipAddress: String) {
        lifecycleScope.launch {
            try {
                showToast("Connecting to $ipAddress...")
                robotController.initialize("http://$ipAddress:8080")
                isConnected = true
                binding.tvStatus.text = "Status: Connected to $ipAddress"
                binding.tvStatus.setTextColor(android.graphics.Color.GREEN)
                showToast("Connected successfully!")
            } catch (e: Exception) {
                isConnected = false
                binding.tvStatus.text = "Status: Connection failed"
                binding.tvStatus.setTextColor(android.graphics.Color.RED)
                showToast("Connection failed: ${e.message}")
            }
        }
    }

    private fun moveRobot(direction: String) {
        if (!isConnected) {
            showToast("Not connected to robot")
            return
        }

        lifecycleScope.launch {
            try {
                when (direction) {
                    "forward" -> robotController.moveForward()
                    "backward" -> robotController.moveBackward()
                    "left" -> robotController.turnLeft()
                    "right" -> robotController.turnRight()
                }
                showToast("Moving $direction")
            } catch (e: Exception) {
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun postureRobot(posture: String) {
        if (!isConnected) {
            showToast("Not connected to robot")
            return
        }

        lifecycleScope.launch {
            try {
                robotController.setPosture(posture)
                showToast("Posture: $posture")
            } catch (e: Exception) {
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun playAnimation(animation: String) {
        if (!isConnected) {
            showToast("Not connected to robot")
            return
        }

        lifecycleScope.launch {
            try {
                robotController.playAnimation(animation)
                showToast("Playing animation: $animation")
            } catch (e: Exception) {
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun stopRobot() {
        if (!isConnected) {
            showToast("Not connected to robot")
            return
        }

        lifecycleScope.launch {
            try {
                robotController.stopMovement()
                showToast("Robot stopped")
            } catch (e: Exception) {
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
