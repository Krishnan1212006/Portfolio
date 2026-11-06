package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentTeal
import com.example.ui.theme.CyanPrimary
import com.example.ui.theme.GoldPrize
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ObstacleCarDemo(modifier: Modifier = Modifier) {
    var distance by remember { mutableFloatStateOf(45f) }
    var servoAngle by remember { mutableFloatStateOf(90f) } // 0 = Left, 90 = Center, 180 = Right
    var isEmergencyAlertSent by remember { mutableStateOf(false) }

    val isObstacleTooClose = distance < 25f

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("obstacle_car_demo_card"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = "Car Demo",
                        tint = CyanPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Arduino Ultrasonic Radar Simulator",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Surface(
                    color = if (isObstacleTooClose) Color(0xFFFF5252) else AccentTeal,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (isObstacleTooClose) "OBSTACLE DETECTED" else "PATH CLEAR",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Radar Display Canvas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF070D1D)),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val center = Offset(size.width / 2f, size.height * 0.9f)
                    val maxRadius = size.height * 0.8f

                    // Draw concentric radar arcs
                    for (i in 1..3) {
                        drawCircle(
                            color = CyanPrimary.copy(alpha = 0.2f * i),
                            radius = maxRadius * (i / 3f),
                            center = center,
                            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
                        )
                    }

                    // Draw Servo Sweep Line
                    val radians = Math.toRadians(servoAngle.toDouble())
                    val endX = center.x + (maxRadius * Math.cos(radians - Math.PI)).toFloat()
                    val endY = center.y + (maxRadius * Math.sin(radians - Math.PI)).toFloat()

                    drawLine(
                        color = CyanPrimary,
                        start = center,
                        end = Offset(endX, endY),
                        strokeWidth = 3f
                    )

                    // Draw Obstacle Dot
                    val obstacleRadius = (distance / 100f) * maxRadius
                    val obsX = center.x + (obstacleRadius * Math.cos(radians - Math.PI)).toFloat()
                    val obsY = center.y + (obstacleRadius * Math.sin(radians - Math.PI)).toFloat()

                    drawCircle(
                        color = if (isObstacleTooClose) Color(0xFFFF5252) else AccentTeal,
                        radius = if (isObstacleTooClose) 14f else 8f,
                        center = Offset(obsX, obsY)
                    )
                }

                // Overlay Info
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 8.dp)
                ) {
                    Text(
                        text = "${distance.toInt()} cm",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Monospace,
                        color = if (isObstacleTooClose) Color(0xFFFF5252) else CyanPrimary
                    )
                    Text(
                        text = "Ultrasonic Sensor Reading",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Slider Controls
            Text(
                text = "Simulate Ultrasonic Distance (cm):",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Slider(
                value = distance,
                onValueChange = {
                    distance = it
                    if (it < 20f && !isEmergencyAlertSent) {
                        isEmergencyAlertSent = true
                    } else if (it >= 20f) {
                        isEmergencyAlertSent = false
                    }
                },
                valueRange = 5f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = CyanPrimary,
                    activeTrackColor = CyanPrimary
                ),
                modifier = Modifier.testTag("distance_slider")
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Servo Angle: ${servoAngle.toInt()}°",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { servoAngle = 30f },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("servo_left_btn")
                    ) {
                        Text("← Left", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                    Button(
                        onClick = { servoAngle = 90f },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("servo_center_btn")
                    ) {
                        Text("Center", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                    Button(
                        onClick = { servoAngle = 150f },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("servo_right_btn")
                    ) {
                        Text("Right →", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }

            AnimatedVisibility(visible = isObstacleTooClose) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0x33FF5252)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Alert",
                            tint = Color(0xFFFF5252)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Emergency Braking Engaged!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color(0xFFFF5252)
                            )
                            Text(
                                text = "Live Location Shared: 11.1085° N, 77.3411° E (Tirupur Hub)",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DatanixSanitizerDemo(modifier: Modifier = Modifier) {
    var wipeMethod by remember { mutableStateOf("NIST SP 800-88 Clear (3-Pass)") }
    var selectedDrive by remember { mutableStateOf("/dev/nvme0n1 - 512GB SSD (Confidential Data)") }
    var isRunning by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }
    var statusText by remember { mutableStateOf("Ready for sanitization audit.") }
    var auditCompleted by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("datanix_demo_card"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "Data Sanitization",
                    tint = AccentTeal,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Datanix AI Wipe & Audit Simulator",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "NIST SP 800-88 Compliant Verification Engine",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Drive Selector Mock
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.outlinedCardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Target Device:", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        text = selectedDrive,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = CyanPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Method Chips
            Text("Sanitization Standard:", style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf("NIST 800-88 Clear", "Crypto Wipe", "Purge (Cryptographic)").forEach { method ->
                    val isSelected = wipeMethod.contains(method)
                    FilterChip(
                        selected = isSelected,
                        onClick = { wipeMethod = method },
                        label = { Text(method, fontSize = 10.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AccentTeal,
                            selectedLabelColor = Color.Black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (isRunning) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = AccentTeal,
                        trackColor = Color(0xFF1E293B)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = statusText,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = CyanPrimary
                    )
                }
            } else {
                Button(
                    onClick = {
                        scope.launch {
                            isRunning = true
                            auditCompleted = false
                            progress = 0.1f
                            statusText = "[1/4] AI Risk Assessment & Data Pattern Search..."
                            delay(800)
                            progress = 0.4f
                            statusText = "[2/4] Executing NIST SP 800-88 $wipeMethod..."
                            delay(1000)
                            progress = 0.8f
                            statusText = "[3/4] Forensic Verification via Autopsy/Kali Engine..."
                            delay(900)
                            progress = 1.0f
                            statusText = "[4/4] Cryptographic Certificate Generated!"
                            delay(400)
                            isRunning = false
                            auditCompleted = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("run_wipe_sim_btn"),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentTeal)
                ) {
                    Icon(imageVector = Icons.Default.CleaningServices, contentDescription = null, tint = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Execute AI Sanitization & Verification", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }

            AnimatedVisibility(visible = auditCompleted) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = Color(0xFF064E3B))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Verified, contentDescription = "Verified", tint = AccentTeal)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "NIST SP 800-88 Audit Certificate Generated",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 13.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Certificate Hash: SHA256-DATANIX-994A1B28C\nStatus: Permanent Data Destruction Verified\nOperator: Harikrishnan (Lead Architect)",
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFFA7F3D0)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SafeTrackSosDemo(modifier: Modifier = Modifier) {
    var isSosActive by remember { mutableStateOf(false) }
    var logList by remember { mutableStateOf(listOf("System Online. GPS Signal Locked.")) }
    val scope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("safetrack_demo_card"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "SafeTrack SOS",
                    tint = Color(0xFFFF5252),
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "SafeTrack ESP32 SOS Tracker",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "IoT Live Emergency Telemetry & Alert Hub",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Big SOS Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    color = if (isSosActive) Color(0xFFFF1744) else Color(0xFF1E293B),
                    shape = CircleShape,
                    modifier = Modifier
                        .size(90.dp)
                        .border(
                            4.dp,
                            if (isSosActive) Color.White else Color(0xFFFF5252),
                            CircleShape
                        )
                        .clickable {
                            if (!isSosActive) {
                                isSosActive = true
                                scope.launch {
                                    logList = logList + "🚨 [SOS TRIGGERED] ESP32 Panic Button Pressed!"
                                    delay(600)
                                    logList = logList + "📍 GPS Lat: 11.1085° N, Lon: 77.3411° E (Tirupur Hub)"
                                    delay(700)
                                    logList = logList + "📲 Alert SMS sent to +91 7639294309"
                                    delay(800)
                                    logList = logList + "🖥️ Web Dashboard incident response active!"
                                }
                            } else {
                                isSosActive = false
                                logList = listOf("System Reset. GPS Standby Mode.")
                            }
                        }
                        .testTag("sos_trigger_btn"),
                    shadowElevation = 8.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = if (isSosActive) "SOS\nACTIVE" else "HOLD\nSOS",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Live Event Stream:", style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(4.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF090E17))
                    .padding(8.dp)
            ) {
                logList.takeLast(4).forEach { log ->
                    Text(
                        text = log,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        color = if (log.contains("SOS")) Color(0xFFFF5252) else CyanPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun UiUxShowcaseDemo(modifier: Modifier = Modifier) {
    val designItems = listOf(
        "Figma Mobile Prototype" to "Cybersecurity Dashboard UI",
        "Adobe Illustrator" to "Brand Assets & Logo Vector Art",
        "Photoshop & Canva" to "Digital Marketing & Posters"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("ui_ux_demo_card"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Palette,
                    contentDescription = "UI/UX",
                    tint = GoldPrize,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "UI/UX & Graphic Design Gallery",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "User-Centered Wireframes & Visual Prototypes",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            designItems.forEach { (tool, title) ->
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = Color(0xFF131C31))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.White)
                            Text(tool, fontSize = 11.sp, color = GoldPrize)
                        }
                        Icon(
                            imageVector = Icons.Default.DesignServices,
                            contentDescription = null,
                            tint = CyanPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CyberResilienceDemo(modifier: Modifier = Modifier) {
    var isSimulating by remember { mutableStateOf(false) }
    var threatScore by remember { mutableFloatStateOf(18f) }
    var currentTechnique by remember { mutableStateOf("T1046: Network Service Scanning") }
    var detectedAnomalies by remember { mutableIntStateOf(3) }
    var modelState by remember { mutableStateOf("LSTM Prediction: Normal Baseline") }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("cyberresilience_demo_card"),
        colors = CardDefaults.cardColors(containerColor = Color(0x660F172A)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3300E5FF)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Security, contentDescription = null, tint = CyanPrimary, modifier = Modifier.size(22.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AI Threat & Telemetry Engine",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
                Surface(
                    color = if (threatScore > 50f) Color(0x33FF5252) else Color(0x1A00E5FF),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (threatScore > 50f) Color(0xFFFF5252) else CyanPrimary)
                ) {
                    Text(
                        text = if (threatScore > 50f) "ATTACK DETECTED" else "MONITORING LIVE",
                        color = if (threatScore > 50f) Color(0xFFFF5252) else CyanPrimary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Telemetry stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    color = Color(0x33FFFFFF),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Risk Score", fontSize = 10.sp, color = Color(0xFF94A3B8))
                        Text("${threatScore.toInt()}%", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if (threatScore > 50f) Color(0xFFFF5252) else CyanPrimary)
                    }
                }
                Surface(
                    color = Color(0x33FFFFFF),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Packet Anomalies", fontSize = 10.sp, color = Color(0xFF94A3B8))
                        Text("$detectedAnomalies", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
                Surface(
                    color = Color(0x33FFFFFF),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1.3f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Active Engine", fontSize = 10.sp, color = Color(0xFF94A3B8))
                        Text("Suricata + GRU", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AccentTeal)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                color = Color(0x1A000000),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1AFFFFFF)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("MITRE ATT&CK Mapping:", fontSize = 11.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
                    Text(currentTechnique, fontSize = 12.sp, color = Color.White, fontFamily = FontFamily.Monospace)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(modelState, fontSize = 11.sp, color = CyanPrimary, fontFamily = FontFamily.Monospace)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    isSimulating = !isSimulating
                    if (isSimulating) {
                        threatScore = 78f
                        detectedAnomalies = 14
                        currentTechnique = "T1110: Brute Force & T1190: Exploit Public-Facing App"
                        modelState = "GRU Sequential Alert: Multi-Step Intrusion In Progress"
                    } else {
                        threatScore = 18f
                        detectedAnomalies = 3
                        currentTechnique = "T1046: Network Service Scanning"
                        modelState = "LSTM Prediction: Normal Baseline"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = if (isSimulating) Color(0xFFFF5252) else CyanPrimary),
                modifier = Modifier.fillMaxWidth().testTag("cyberresilience_simulate_btn")
            ) {
                Text(
                    text = if (isSimulating) "Reset Threat Simulation" else "Simulate Live Attack Telemetry (PCAP/GRU)",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun SatlinkDemo(modifier: Modifier = Modifier) {
    var isTransmitting by remember { mutableStateOf(false) }
    var encryptedPacket by remember { mutableStateOf("0x7F2A...AES256_GCM") }
    var uplinkStatus by remember { mutableStateOf("Iridium SBD Constellation: Standby") }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("satlink_demo_card"),
        colors = CardDefaults.cardColors(containerColor = Color(0x660F172A)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x337C4DFF)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CloudSync, contentDescription = null, tint = Color(0xFF7C4DFF), modifier = Modifier.size(22.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "SATLINK Orbital Transceiver",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
                Surface(
                    color = Color(0x1A7C4DFF),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x337C4DFF))
                ) {
                    Text(
                        text = "LEO IRIDIUM",
                        color = Color(0xFFB388FF),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                color = Color(0x1A000000),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1AFFFFFF)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("Crypto Layer: Hardware AES-256 + Device Auth", fontSize = 11.sp, color = Color(0xFF94A3B8))
                    Text("Payload: $encryptedPacket", fontSize = 11.sp, color = CyanPrimary, fontFamily = FontFamily.Monospace)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Telemetry: $uplinkStatus", fontSize = 11.sp, color = Color.White, fontFamily = FontFamily.Monospace)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    isTransmitting = !isTransmitting
                    if (isTransmitting) {
                        encryptedPacket = "ENC: [GNSS: 11.0168°N, 77.3411°E | SOS_CRITICAL | NONCE: 0x9B1C]"
                        uplinkStatus = "Uplink ACK: Transmitted via Iridium Satellite (Burst ID: 4892)"
                    } else {
                        encryptedPacket = "0x7F2A...AES256_GCM"
                        uplinkStatus = "Iridium SBD Constellation: Standby"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
                modifier = Modifier.fillMaxWidth().testTag("satlink_transmit_btn")
            ) {
                Text(
                    text = if (isTransmitting) "Clear Uplink Session" else "Dispatch Encrypted Satellite SOS Uplink",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}

