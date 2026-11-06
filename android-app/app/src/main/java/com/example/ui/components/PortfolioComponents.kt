package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.*
import com.example.ui.theme.AccentTeal
import com.example.ui.theme.CyanPrimary
import com.example.ui.theme.GoldPrize

@Composable
fun HeaderSection(
    profile: UserProfile,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showFullObjective by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("header_section_card"),
        colors = CardDefaults.cardColors(containerColor = Color(0x660F172A)), // bg-slate-900/40
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x0DFFFFFF)), // border-white/5
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            // Hero Banner Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_banner_1785305151608),
                    contentDescription = "Portfolio Hero Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC0B132B))
                            )
                        )
                )

                // Quick Badge
                Surface(
                    color = AccentTeal,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Final Year B.E. CSE",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            // Profile info & Avatar
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.offset(y = (-36).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_profile_avatar_1785305138719),
                        contentDescription = profile.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Brush.horizontalGradient(listOf(Color(0xFF00E5FF), Color(0xFF7C4DFF))), CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.padding(bottom = 4.dp)) {
                        Text(
                            text = profile.name,
                            style = MaterialTheme.typography.headlineMedium, 
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            letterSpacing = (-0.5).sp
                        )
                        Text(
                            text = profile.currentRole.uppercase(),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF00E5FF),
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(-24.dp))

                // Objective Text
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0x660F172A)) // bg-slate-900/40
                        .border(1.dp, Color(0x0DFFFFFF), RoundedCornerShape(12.dp)) // border-white/5
                        .padding(12.dp)
                ) {
                    Text(
                        text = if (showFullObjective) profile.objective else profile.objective.take(130) + "...",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF94A3B8), // text-slate-400
                        lineHeight = 18.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                    Text(
                        text = if (showFullObjective) "SHOW LESS" else "READ FULL OBJECTIVE",
                        color = Color(0xFF00E5FF),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable { showFullObjective = !showFullObjective }
                            .testTag("objective_toggle")
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Contact Quick Action Chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AssistChip(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${profile.phone}"))
                            context.startActivity(intent)
                        },
                        label = { Text("Call", fontSize = 11.sp) },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        colors = AssistChipDefaults.assistChipColors(labelColor = MaterialTheme.colorScheme.onSurface),
                        modifier = Modifier.weight(1f).testTag("call_chip")
                    )

                    AssistChip(
                        onClick = {
                            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${profile.email}"))
                            context.startActivity(intent)
                        },
                        label = { Text("Email", fontSize = 11.sp) },
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        colors = AssistChipDefaults.assistChipColors(labelColor = MaterialTheme.colorScheme.onSurface),
                        modifier = Modifier.weight(1f).testTag("email_chip")
                    )

                    Button(
                        onClick = onContactClick,
                        colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.weight(1.2f).testTag("hire_me_header_btn")
                    ) {
                        Text("Connect", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectCard(
    project: ProjectItem,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .testTag("project_card_${project.id}"),
        colors = CardDefaults.cardColors(containerColor = Color(0x660F172A)), // bg-slate-900/40
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x0DFFFFFF)), // border-white/5
        shape = RoundedCornerShape(24.dp), // rounded-3xl
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Top Right Arrow Icon
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(40.dp)
                    .background(Color(0x0DFFFFFF), CircleShape) // bg-white/5
                    .clickable { onToggleExpand() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.NorthEast,
                    contentDescription = "Expand",
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(18.dp)
                )
            }

            Column(modifier = Modifier.padding(20.dp)) {
                // Category & Year Tags
                Row(
                    modifier = Modifier.padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        color = Color(0x1A00E5FF), // bg-[#00E5FF]/10
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3300E5FF)), // border-[#00E5FF]/20
                        shape = RoundedCornerShape(percent = 50)
                    ) {
                        Text(
                            text = project.category.uppercase(),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00E5FF),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                    if (project.award != null) {
                        Surface(
                            color = Color(0x33FFFFFF), // bg-slate-800
                            shape = RoundedCornerShape(percent = 50)
                        ) {
                            Text(
                                text = "AWARD WINNER",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF94A3B8), // text-slate-400
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = project.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF94A3B8), // text-slate-400
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )

                // Tech stack circles
                Row(
                    horizontalArrangement = Arrangement.spacedBy((-8).dp) // -space-x-2
                ) {
                    project.technologies.take(4).forEach { tech ->
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color(0x33FFFFFF), CircleShape) // bg-slate-800
                                .border(2.dp, Color(0x33FFFFFF), CircleShape), // border-[#0A0A0A]
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tech.take(2).uppercase(),
                                fontSize = 10.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    if (project.technologies.size > 4) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color(0x4DFFFFFF), CircleShape) // bg-slate-700
                                .border(2.dp, Color(0x33FFFFFF), CircleShape), // border-[#0A0A0A]
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+${project.technologies.size - 4}",
                                fontSize = 10.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                AnimatedVisibility(visible = isExpanded) {
                    Column(modifier = Modifier.padding(top = 16.dp)) {
                        when (project.demoType) {
                            DemoType.CYBER_RESILIENCE -> CyberResilienceDemo()
                            DemoType.DATANIX_SANITIZER -> DatanixSanitizerDemo()
                            DemoType.SAFETRACK_SOS -> SafeTrackSosDemo()
                            DemoType.SATLINK -> SatlinkDemo()
                            DemoType.OBSTACLE_CAR -> ObstacleCarDemo()
                            DemoType.UI_SHOWCASE -> UiUxShowcaseDemo()
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InternshipCard(
    internship: InternshipItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("internship_card"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Security, contentDescription = null, tint = AccentTeal)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = internship.role,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = "${internship.company} • ${internship.location}",
                style = MaterialTheme.typography.bodySmall,
                color = CyanPrimary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = internship.period,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            internship.achievements.forEach { achievement ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("• ", color = AccentTeal, fontWeight = FontWeight.Bold)
                    Text(
                        text = achievement,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Tools & Platforms:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(internship.toolsUsed) { tool ->
                    Surface(
                        color = Color(0x1AFFFFFF),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x33FFFFFF))
                    ) {
                        Text(
                            text = tool,
                            fontSize = 11.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EducationCard(
    items: List<EducationItem>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("education_card"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.School, contentDescription = null, tint = CyanPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Education Background",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            items.forEachIndexed { index, edu ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(edu.degree, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                            Text(edu.institution, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(edu.period, fontSize = 11.sp, color = CyanPrimary)
                        }
                        Surface(
                            color = AccentTeal,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = edu.score,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 12.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    if (index < items.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AwardsSection(
    awards: List<AwardItem>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("awards_section_card"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = GoldPrize)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Awards & Achievements",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            awards.forEach { award ->
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.MilitaryTech,
                            contentDescription = null,
                            tint = GoldPrize,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = award.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "${award.organizer} • ${award.date}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CertificationsSection(
    certifications: List<CertificationItem>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("certifications_card"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.WorkspacePremium, contentDescription = null, tint = AccentTeal)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Certifications",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            certifications.forEach { cert ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = AccentTeal, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(cert.title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface)
                        Text("${cert.issuer} (${cert.date})", fontSize = 11.sp, color = CyanPrimary)
                    }
                }
            }
        }
    }
}

@Composable
fun WebsiteNavigationBar(
    currentTab: String,
    onTabSelected: (String) -> Unit,
    onContactClick: () -> Unit,
    onDownloadResumeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xE60A0E17))
            .border(width = 1.dp, color = Color(0x1AFFFFFF))
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .testTag("website_nav_bar")
    ) {
        val isWideScreen = maxWidth >= 800.dp
        val isMediumScreen = maxWidth in 520.dp..799.dp

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Brand Logo
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onTabSelected("All") }
                    .testTag("nav_logo_brand")
            ) {
                Surface(
                    color = Color(0xFF00E5FF),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "H",
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "HARIKRISHNAN",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = Color.White,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            color = Color(0x3300E5FF),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x8000E5FF)),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = ".DEV",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF00E5FF),
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                    }
                    Text(
                        text = "Portfolio • Cybersecurity & IoT",
                        fontSize = 10.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            // Desktop & Tablet Navigation links
            if (isWideScreen) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val navItems = listOf("All", "Projects", "Cybersecurity & AI", "Hardware & IoT", "Interactive Demos", "Experience", "Skills", "Awards & Certs")
                    navItems.forEach { navItem ->
                        val isSelected = currentTab == navItem
                        TextButton(
                            onClick = { onTabSelected(navItem) },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = if (isSelected) Color(0xFF00E5FF) else Color(0xFFCBD5E1)
                            ),
                            modifier = Modifier.testTag("nav_item_$navItem")
                        ) {
                            Text(
                                text = navItem,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            // Right Action CTAs
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onDownloadResumeClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF00E5FF)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x6600E5FF)),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("nav_download_resume_btn")
                ) {
                    Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                    if (!isMediumScreen && isWideScreen) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Resume (PDF)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Button(
                    onClick = onContactClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF)),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("nav_contact_btn")
                ) {
                    Text("Get in Touch", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun WebsiteHeroSection(
    profile: UserProfile,
    onExploreProjectsClick: () -> Unit,
    onViewDemosClick: () -> Unit,
    onContactClick: () -> Unit,
    onDownloadResumeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("website_hero_card"),
        colors = CardDefaults.cardColors(containerColor = Color(0x660F172A)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1AFFFFFF)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            // Status Pill
            Surface(
                color = Color(0x3300E5FF),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x6600E5FF)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF00E676), CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Available for Cybersecurity & IoT Roles • Tirupur, India • CGPA 7.27",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF00E5FF)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main Web Headline
            Text(
                text = "Building Resilient Cyber Defense & IoT Architecture",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                lineHeight = 38.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Bio Summary
            Text(
                text = "Hi, I'm Harikrishnan S. Final Year Computer Science & Engineering student at Sri Ramakrishna Engineering College. Specialized in autonomous threat telemetry, DoD 5220.22-M disk sanitization, and satellite telemetry ground terminals.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFCBD5E1),
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Metric Counters Ribbon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "6 Projects" to "AI & Hardware",
                    "CGPA 7.27" to "B.E. Computer Sci",
                    "1st Prize" to "Symposium",
                    "NIST & DoD" to "Compliant Tools"
                ).forEach { (stat, label) ->
                    Surface(
                        color = Color(0x331E293B),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1AFFFFFF)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stat,
                                fontWeight = FontWeight.Black,
                                fontSize = 13.sp,
                                color = Color(0xFF00E5FF)
                            )
                            Text(
                                text = label,
                                fontSize = 9.sp,
                                color = Color(0xFF94A3B8),
                                maxLines = 1
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Web CTA Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onExploreProjectsClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF)),
                    modifier = Modifier.weight(1f).testTag("hero_explore_projects_btn")
                ) {
                    Text("Explore 6 Projects", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                OutlinedButton(
                    onClick = onViewDemosClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x4DFFFFFF)),
                    modifier = Modifier.weight(1f).testTag("hero_view_demos_btn")
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFF00E5FF), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Live Demos", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }

                OutlinedButton(
                    onClick = onDownloadResumeClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF00BFA5)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x6600BFA5)),
                    modifier = Modifier.weight(1.1f).testTag("hero_download_cv_btn")
                ) {
                    Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Resume PDF", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun WebsiteFooterSection(
    profile: UserProfile,
    onTabSelected: (String) -> Unit,
    onContactClick: () -> Unit,
    onDownloadResumeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .testTag("website_footer"),
        colors = CardDefaults.cardColors(containerColor = Color(0xE60A0E17)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1AFFFFFF)),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = Color(0xFF00E5FF),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(28.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("H", fontWeight = FontWeight.Black, fontSize = 14.sp, color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "HARIKRISHNAN S",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                Text(
                    text = "B.E. CSE • Tirupur, Tamil Nadu",
                    fontSize = 11.sp,
                    color = Color(0xFF94A3B8)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Contact: harikrishnansenthil79@gmail.com • +91 97893 25965",
                fontSize = 12.sp,
                color = Color(0xFF00E5FF)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "© 2024 Harikrishnan S. Designed for Laptop, iPad & Smartphone Web.",
                    fontSize = 10.sp,
                    color = Color(0xFF64748B)
                )

                TextButton(onClick = onDownloadResumeClick) {
                    Icon(Icons.Default.Download, contentDescription = null, tint = Color(0xFF00E5FF), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Download Resume PDF", fontSize = 11.sp, color = Color(0xFF00E5FF))
                }
            }
        }
    }
}

