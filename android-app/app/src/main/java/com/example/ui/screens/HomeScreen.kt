package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ResumeRepository
import com.example.model.DemoType
import com.example.model.ProjectItem
import com.example.ui.components.*
import com.example.ui.theme.AccentTeal
import com.example.ui.theme.CyanPrimary
import com.example.ui.theme.GoldPrize
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }
    var expandedProjectIds by remember { mutableStateOf(setOf<String>()) }
    var favoritedProjectIds by remember { mutableStateOf(setOf<String>()) }
    var selectedProjectForDetail by remember { mutableStateOf<ProjectItem?>(null) }

    var showContactSheet by remember { mutableStateOf(false) }

    val tabs = listOf("All", "Projects", "Cybersecurity & AI", "Hardware & IoT", "Design", "Interactive Demos", "Skills", "Experience", "Awards & Certs")

    // Filter projects based on tab & query
    val filteredProjects = remember(selectedTab, searchQuery) {
        ResumeRepository.projectList.filter { proj ->
            val matchesQuery = searchQuery.isEmpty() ||
                    proj.title.contains(searchQuery, ignoreCase = true) ||
                    proj.description.contains(searchQuery, ignoreCase = true) ||
                    proj.shortDescription.contains(searchQuery, ignoreCase = true) ||
                    proj.technologies.any { it.contains(searchQuery, ignoreCase = true) }

            val matchesTab = when (selectedTab) {
                "Projects" -> true
                "Cybersecurity & AI" -> proj.category.contains("Cybersecurity", ignoreCase = true)
                "Hardware & IoT" -> proj.category.contains("Hardware", ignoreCase = true)
                "Design" -> proj.category.contains("Design", ignoreCase = true)
                "Interactive Demos" -> proj.demoType != null
                else -> true
            }

            matchesQuery && matchesTab
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF6200EA).copy(alpha = 0.5f), // Mesh Purple
                        Color(0xFF0F0C29),                   // Deep Navy
                        Color(0xFF00BFA5).copy(alpha = 0.4f)  // Mesh Teal
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                WebsiteNavigationBar(
                    currentTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                    onContactClick = { showContactSheet = true },
                    onDownloadResumeClick = {
                        Toast.makeText(context, "Downloading Harikrishnan's Resume (PDF)...", Toast.LENGTH_LONG).show()
                    }
                )
            },
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .widthIn(max = 1200.dp)
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            // 1. Website Hero Section
            item {
                WebsiteHeroSection(
                    profile = ResumeRepository.profile,
                    onExploreProjectsClick = { selectedTab = "Projects" },
                    onViewDemosClick = { selectedTab = "Interactive Demos" },
                    onContactClick = { showContactSheet = true },
                    onDownloadResumeClick = {
                        Toast.makeText(context, "Downloading Harikrishnan's Resume (PDF)...", Toast.LENGTH_LONG).show()
                    }
                )
            }

            // 2. Search Field
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search projects, Python, NIST, Arduino, Nmap...", fontSize = 13.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = CyanPrimary) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_text_field"),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CyanPrimary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    singleLine = true
                )
            }

            // 3. Filter Category Tabs
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(tabs) { tab ->
                        val isSelected = selectedTab == tab
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedTab = tab },
                            label = { Text(tab, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = CyanPrimary,
                                selectedLabelColor = Color.Black
                            ),
                            modifier = Modifier.testTag("tab_$tab")
                        )
                    }
                }
            }

            // 4. Projects Section
            if (selectedTab in listOf("All", "Projects", "Cybersecurity & AI", "Hardware & IoT", "Design", "Interactive Demos")) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (selectedTab == "Interactive Demos") "Live Interactive Demos" else "Featured Projects (${filteredProjects.size})",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Interactive projects showcasing AI intrusion analysis, secure wiping, IoT hardware & UI design",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF94A3B8)
                            )
                        }
                    }
                }

                item {
                    ResponsiveProjectGrid(
                        projects = filteredProjects,
                        expandedProjectIds = expandedProjectIds,
                        onToggleExpand = { id ->
                            expandedProjectIds = if (expandedProjectIds.contains(id)) {
                                expandedProjectIds - id
                            } else {
                                expandedProjectIds + id
                            }
                        },
                        onViewProjectDetails = { project ->
                            selectedProjectForDetail = project
                        }
                    )
                }
            }

            // 5. Internship & Experience Section
            if (selectedTab in listOf("All", "Experience")) {
                item {
                    Text(
                        text = "Work & Internship Experience",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                items(ResumeRepository.internshipList) { internship ->
                    InternshipCard(internship = internship)
                }
            }

            // 6. Skills Section
            if (selectedTab in listOf("All", "Skills")) {
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Technical Skills & Tools",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )

                        ResumeRepository.skillCategories.forEach { category ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = category.categoryName,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = CyanPrimary
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    FlowRow(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        category.skills.forEach { skill ->
                                            Surface(
                                                color = MaterialTheme.colorScheme.surfaceVariant,
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text(
                                                    text = skill,
                                                    fontSize = 11.sp,
                                                    color = MaterialTheme.colorScheme.onSurface,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 7. Education Section
            if (selectedTab in listOf("All", "Experience")) {
                item {
                    EducationCard(items = ResumeRepository.educationList)
                }
            }

            // 8. Awards & Certifications Section
            if (selectedTab in listOf("All", "Awards & Certs")) {
                item {
                    AwardsSection(awards = ResumeRepository.awardsList)
                }

                item {
                    CertificationsSection(certifications = ResumeRepository.certificationsList)
                }
            }

            // 9. Website Footer Section
            item {
                WebsiteFooterSection(
                    profile = ResumeRepository.profile,
                    onTabSelected = { selectedTab = it },
                    onContactClick = { showContactSheet = true },
                    onDownloadResumeClick = {
                        Toast.makeText(context, "Downloading Harikrishnan's Resume (PDF)...", Toast.LENGTH_LONG).show()
                    }
                )
            }

            // Bottom Spacing
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        } // Closes LazyColumn
        } // Closes Inner Box
    } // Closes Scaffold
    } // Closes Background Box

    // Contact & Hire Me Modal Dialog
    if (showContactSheet) {
        ContactDialog(
            profile = ResumeRepository.profile,
            onDismiss = { showContactSheet = false }
        )
    }

    // Project Specifications & Architecture Modal Dialog
    selectedProjectForDetail?.let { project ->
        ProjectDetailDialog(
            project = project,
            onDismiss = { selectedProjectForDetail = null }
        )
    }
}

@Composable
fun ContactDialog(
    profile: com.example.model.UserProfile,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var senderName by remember { mutableStateOf("") }
    var senderEmail by remember { mutableStateOf("") }
    var senderMessage by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            if (isSubmitted) {
                TextButton(onClick = onDismiss) {
                    Text("Close")
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("contact_dialog")
            ) {
            Text(
                text = "Connect with Harikrishnan",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Tirupur, Tamil Nadu • ${profile.phone}",
                style = MaterialTheme.typography.bodySmall,
                color = CyanPrimary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Quick Contact Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${profile.phone}"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f).testTag("sheet_call_btn")
                ) {
                    Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Call", fontSize = 12.sp)
                }

                OutlinedButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${profile.email}"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f).testTag("sheet_email_btn")
                ) {
                    Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Email", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!isSubmitted) {
                Text("Send a direct message:", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = senderName,
                    onValueChange = { senderName = it },
                    label = { Text("Your Name") },
                    modifier = Modifier.fillMaxWidth().testTag("sender_name_input"),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = senderEmail,
                    onValueChange = { senderEmail = it },
                    label = { Text("Your Email") },
                    modifier = Modifier.fillMaxWidth().testTag("sender_email_input"),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = senderMessage,
                    onValueChange = { senderMessage = it },
                    label = { Text("Project Enquiry / Message") },
                    modifier = Modifier.fillMaxWidth().height(100.dp).testTag("sender_msg_input")
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (senderName.isNotBlank() && senderMessage.isNotBlank()) {
                            isSubmitted = true
                            Toast.makeText(context, "Message sent to Harikrishnan!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Please fill in your name and message.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().testTag("submit_message_btn"),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary)
                ) {
                    Text("Send Message", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            } else {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = AccentTeal, modifier = Modifier.size(40.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Thank you, $senderName!", fontWeight = FontWeight.Bold, color = Color.White)
                        Text("Your message has been sent to Harikrishnan.", fontSize = 12.sp, color = Color(0xFFA7F3D0))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Download Resume Action Button
            TextButton(
                onClick = {
                    Toast.makeText(context, "Harikrishnan's Resume (PDF) Downloaded!", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.fillMaxWidth().testTag("download_resume_btn")
            ) {
                Icon(Icons.Default.Download, contentDescription = null, tint = CyanPrimary)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Download Harikrishnan's Resume (PDF)", color = CyanPrimary, fontWeight = FontWeight.Bold)
            }
        }
    })
}
