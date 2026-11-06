package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DemoType
import com.example.model.ProjectItem
import com.example.model.ProjectVisualType
import com.example.ui.theme.AccentTeal
import com.example.ui.theme.CyanPrimary
import com.example.ui.theme.GoldPrize

/**
 * Responsive Grid Component to showcase portfolio projects across
 * laptops (3 columns), iPads/tablets (2 columns), and smartphones (1 column).
 */
@Composable
fun ResponsiveProjectGrid(
    projects: List<ProjectItem>,
    expandedProjectIds: Set<String>,
    onToggleExpand: (String) -> Unit,
    modifier: Modifier = Modifier,
    onViewProjectDetails: (ProjectItem) -> Unit = {}
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val columnCount = when {
            maxWidth >= 960.dp -> 3 // Desktop / Laptop wide view
            maxWidth >= 600.dp -> 2 // Tablet / iPad medium view
            else -> 1               // Smartphone compact view
        }

        val chunkedProjects = projects.chunked(columnCount)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            chunkedProjects.forEach { rowProjects ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowProjects.forEach { project ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            ProjectGridCard(
                                project = project,
                                isExpanded = expandedProjectIds.contains(project.id),
                                onToggleExpand = { onToggleExpand(project.id) },
                                onViewDetails = { onViewProjectDetails(project) }
                            )
                        }
                    }

                    // Fill remaining empty columns in the last row for equal width
                    if (rowProjects.size < columnCount) {
                        repeat(columnCount - rowProjects.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

/**
 * Individual Project Card featuring an eye-catching visual image banner,
 * category badges, title, short description, technology tags, and interactive demo triggers.
 */
@Composable
fun ProjectGridCard(
    project: ProjectItem,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onViewDetails: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("project_grid_card_${project.id}"),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x660F172A) // Glassmorphic slate-900 / 40%
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isExpanded) CyanPrimary.copy(alpha = 0.6f) else Color(0x1FFFFFFF)
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // 1. Rich Visual Image Showcase Banner
            ProjectImageBanner(
                project = project,
                onClick = onToggleExpand
            )

            // 2. Card Content (Title, Short Description, Badges, Tech Stack)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Key Highlight / Award Ribbon
                project.keyHighlight?.let { highlight ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 6.dp)
                    ) {
                        Icon(
                            imageVector = if (project.award != null) Icons.Default.EmojiEvents else Icons.Default.Bolt,
                            contentDescription = null,
                            tint = if (project.award != null) GoldPrize else CyanPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = highlight,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (project.award != null) GoldPrize else CyanPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                // Project Title
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Subtitle
                Text(
                    text = project.subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF94A3B8),
                    modifier = Modifier.padding(bottom = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Short Description
                Text(
                    text = project.shortDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFCBD5E1), // text-slate-300
                    lineHeight = 18.sp,
                    maxLines = if (isExpanded) 10 else 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Tech Stack Tags
                OptInFlowRow(project.technologies.take(5))

                Spacer(modifier = Modifier.height(14.dp))

                // Action Bar (Interactive Demo Toggle & Details / Github)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Interactive Demo / Preview Button
                    OutlinedButton(
                        onClick = onToggleExpand,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (isExpanded) CyanPrimary.copy(alpha = 0.15f) else Color.Transparent,
                            contentColor = CyanPrimary
                        ),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isExpanded) CyanPrimary else Color(0x4D00E5FF)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("demo_btn_${project.id}")
                    ) {
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isExpanded) "Hide Demo" else "Live Demo",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        // GitHub Link
                        IconButton(
                            onClick = {
                                val url = project.githubUrl ?: "https://github.com/Krishnan1212006"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            },
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color(0x1AFFFFFF), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Code,
                                contentDescription = "View GitHub",
                                tint = Color.White.copy(alpha = 0.85f),
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        // Full Specs / Details Button
                        IconButton(
                            onClick = onViewDetails,
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color(0x1A00E5FF), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = "Project Specs",
                                tint = CyanPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                // Expandable Interactive Live Demo Section
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Column(modifier = Modifier.padding(top = 16.dp)) {
                        Divider(
                            color = Color(0x1AFFFFFF),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        when (project.demoType) {
                            DemoType.CYBER_RESILIENCE -> CyberResilienceDemo()
                            DemoType.DATANIX_SANITIZER -> DatanixSanitizerDemo()
                            DemoType.SAFETRACK_SOS -> SafeTrackSosDemo()
                            DemoType.SATLINK -> SatlinkDemo()
                            DemoType.OBSTACLE_CAR -> ObstacleCarDemo()
                            DemoType.UI_SHOWCASE -> UiUxShowcaseDemo()
                            else -> {
                                Text(
                                    text = "Interactive preview module in deployment.",
                                    fontSize = 12.sp,
                                    color = Color(0xFF94A3B8)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Renders a visually compelling, branded project preview image banner
 * with canvas graphics, radar waves, telemetry lines, and floating badges.
 */
@Composable
fun ProjectImageBanner(
    project: ProjectItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "banner_pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    val gradientBrush = when (project.visualType) {
        ProjectVisualType.CYBER_RESILIENCE -> Brush.linearGradient(
            colors = listOf(Color(0xFF031628), Color(0xFF0A2B42), Color(0xFF041A2E))
        )
        ProjectVisualType.DATANIX_SANITIZER -> Brush.linearGradient(
            colors = listOf(Color(0xFF03221E), Color(0xFF083C32), Color(0xFF041E1A))
        )
        ProjectVisualType.SAFETRACK_SOS -> Brush.linearGradient(
            colors = listOf(Color(0xFF241334), Color(0xFF381A45), Color(0xFF1E1030))
        )
        ProjectVisualType.SATLINK_SATELLITE -> Brush.linearGradient(
            colors = listOf(Color(0xFF130E36), Color(0xFF221752), Color(0xFF110C30))
        )
        ProjectVisualType.OBSTACLE_CAR -> Brush.linearGradient(
            colors = listOf(Color(0xFF0A2234), Color(0xFF163E56), Color(0xFF0A1E30))
        )
        ProjectVisualType.UI_UX_DESIGN -> Brush.linearGradient(
            colors = listOf(Color(0xFF281136), Color(0xFF3B1854), Color(0xFF1B0D29))
        )
        ProjectVisualType.CYBER_SECURITY -> Brush.linearGradient(
            colors = listOf(Color(0xFF0A192F), Color(0xFF172A46), Color(0xFF0B1B33))
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(155.dp)
            .background(gradientBrush)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .clickable { onClick() }
    ) {
        // Decorative Canvas Background Graphics
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            when (project.visualType) {
                ProjectVisualType.CYBER_RESILIENCE -> {
                    // Neural Network / Telemetry Waveform Nodes
                    drawLine(
                        color = Color(0x3300E5FF),
                        start = Offset(0f, height * 0.7f),
                        end = Offset(width * 0.3f, height * 0.4f),
                        strokeWidth = 2f
                    )
                    drawLine(
                        color = Color(0x3300E5FF),
                        start = Offset(width * 0.3f, height * 0.4f),
                        end = Offset(width * 0.65f, height * 0.6f),
                        strokeWidth = 2f
                    )
                    drawLine(
                        color = Color(0x3300E5FF),
                        start = Offset(width * 0.65f, height * 0.6f),
                        end = Offset(width, height * 0.3f),
                        strokeWidth = 2f
                    )
                    drawCircle(Color(0x8000E5FF), radius = 6f, center = Offset(width * 0.3f, height * 0.4f))
                    drawCircle(Color(0x8000E5FF), radius = 8f, center = Offset(width * 0.65f, height * 0.6f))
                }
                ProjectVisualType.SAFETRACK_SOS -> {
                    // Concentric GPS Radar Rings
                    val center = Offset(width * 0.75f, height * 0.5f)
                    drawCircle(Color(0x26FF5252), radius = 30f, center = center)
                    drawCircle(Color(0x1AFF5252), radius = 60f, center = center)
                    drawCircle(Color(0x0DFF5252), radius = 95f, center = center)
                }
                ProjectVisualType.SATLINK_SATELLITE -> {
                    // Orbital Ellipse Arc
                    drawLine(
                        color = Color(0x4D7C4DFF),
                        start = Offset(0f, height * 0.85f),
                        end = Offset(width * 0.8f, height * 0.2f),
                        strokeWidth = 2f
                    )
                    drawCircle(Color(0xFF7C4DFF), radius = 5f, center = Offset(width * 0.5f, height * 0.45f))
                }
                ProjectVisualType.OBSTACLE_CAR -> {
                    // Ultrasonic Sonar Arcs
                    val origin = Offset(width * 0.25f, height * 0.6f)
                    drawCircle(Color(0x2600E5FF), radius = 25f, center = origin)
                    drawCircle(Color(0x1A00E5FF), radius = 50f, center = origin)
                }
                else -> {
                    // Subtle Tech Grid
                    drawLine(
                        color = Color(0x14FFFFFF),
                        start = Offset(0f, height * 0.5f),
                        end = Offset(width, height * 0.5f),
                        strokeWidth = 1f
                    )
                }
            }
        }

        // Center Visual Icon & Branding
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(64.dp)
                .background(Color(0x33FFFFFF), CircleShape)
                .border(1.5.dp, Color(0x4DFFFFFF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            val iconVector = when (project.visualType) {
                ProjectVisualType.CYBER_RESILIENCE -> Icons.Default.Security
                ProjectVisualType.DATANIX_SANITIZER -> Icons.Default.FolderDelete
                ProjectVisualType.SAFETRACK_SOS -> Icons.Default.GpsFixed
                ProjectVisualType.SATLINK_SATELLITE -> Icons.Default.Satellite
                ProjectVisualType.OBSTACLE_CAR -> Icons.Default.DirectionsCar
                ProjectVisualType.UI_UX_DESIGN -> Icons.Default.Palette
                ProjectVisualType.CYBER_SECURITY -> Icons.Default.Shield
            }

            val iconTint = when (project.visualType) {
                ProjectVisualType.CYBER_RESILIENCE -> CyanPrimary
                ProjectVisualType.DATANIX_SANITIZER -> AccentTeal
                ProjectVisualType.SAFETRACK_SOS -> Color(0xFFFF5252)
                ProjectVisualType.SATLINK_SATELLITE -> Color(0xFFB388FF)
                ProjectVisualType.OBSTACLE_CAR -> GoldPrize
                ProjectVisualType.UI_UX_DESIGN -> Color(0xFFFF4081)
                ProjectVisualType.CYBER_SECURITY -> CyanPrimary
            }

            Icon(
                imageVector = iconVector,
                contentDescription = project.title,
                tint = iconTint,
                modifier = Modifier.size(34.dp)
            )
        }

        // Top-Left Category Badge
        Surface(
            color = Color(0x66000000),
            shape = RoundedCornerShape(8.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x33FFFFFF)),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        ) {
            Text(
                text = project.category.uppercase(),
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = CyanPrimary,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        // Top-Right Live Status or Award Pill
        val badgeText = project.award ?: project.status ?: "FEATURED"
        val isAward = project.award != null
        Surface(
            color = if (isAward) Color(0x664A3508) else Color(0x66000000),
            shape = RoundedCornerShape(8.dp),
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                if (isAward) GoldPrize.copy(alpha = 0.6f) else Color(0x33FFFFFF)
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            if (isAward) GoldPrize else CyanPrimary.copy(alpha = pulseAlpha),
                            CircleShape
                        )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = badgeText.uppercase(),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isAward) GoldPrize else Color.White
                )
            }
        }
    }
}

/**
 * Clean technology pill tags for project cards
 */
@Composable
private fun OptInFlowRow(technologies: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        technologies.forEach { tech ->
            Surface(
                color = Color(0x26FFFFFF),
                shape = RoundedCornerShape(6.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1AFFFFFF))
            ) {
                Text(
                    text = tech,
                    fontSize = 10.sp,
                    color = Color(0xFFE2E8F0),
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

/**
 * Comprehensive Website Modal Dialog for full project specifications,
 * MITRE ATT&CK techniques, architecture bullets, and links.
 */
@Composable
fun ProjectDetailDialog(
    project: ProjectItem,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(contentColor = CyanPrimary)
            ) {
                Text("Close", fontWeight = FontWeight.Bold)
            }
        },
        containerColor = Color(0xFF0F172A),
        shape = RoundedCornerShape(20.dp),
        title = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Surface(
                        color = Color(0x1A00E5FF),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3300E5FF))
                    ) {
                        Text(
                            text = project.category,
                            fontSize = 10.sp,
                            color = CyanPrimary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                Text(
                    text = project.subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF94A3B8)
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("project_detail_dialog")
            ) {
                // Key Highlight / Award
                project.keyHighlight?.let { highlight ->
                    Surface(
                        color = Color(0x1A00E5FF),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Verified,
                                contentDescription = null,
                                tint = CyanPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = highlight,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }

                Text(
                    text = "Project Overview & Engineering Highlights",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = CyanPrimary,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                project.bullets.forEach { bullet ->
                    Row(
                        modifier = Modifier.padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("• ", color = CyanPrimary, fontWeight = FontWeight.Bold)
                        Text(
                            text = bullet,
                            fontSize = 12.sp,
                            color = Color(0xFFCBD5E1),
                            lineHeight = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Technologies Used",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    project.technologies.forEach { tech ->
                        Surface(
                            color = Color(0x33FFFFFF),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = tech,
                                fontSize = 10.sp,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Direct GitHub Repo Action
                Button(
                    onClick = {
                        val url = project.githubUrl ?: "https://github.com/Krishnan1212006"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Default.Code,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "View Code on GitHub (Krishnan1212006)",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    )
}
