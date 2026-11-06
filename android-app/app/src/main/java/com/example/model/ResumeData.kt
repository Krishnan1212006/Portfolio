package com.example.model

data class ProjectItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val shortDescription: String = description,
    val bullets: List<String>,
    val technologies: List<String>,
    val award: String? = null,
    val status: String? = null,
    val category: String, // "Cybersecurity & AI", "Hardware & IoT", "Design"
    val demoType: DemoType? = null,
    val visualType: ProjectVisualType = ProjectVisualType.CYBER_SECURITY,
    val githubUrl: String? = "https://github.com/Krishnan1212006",
    val keyHighlight: String? = null
)

enum class ProjectVisualType {
    CYBER_RESILIENCE,
    DATANIX_SANITIZER,
    SAFETRACK_SOS,
    SATLINK_SATELLITE,
    OBSTACLE_CAR,
    UI_UX_DESIGN,
    CYBER_SECURITY
}

enum class DemoType {
    OBSTACLE_CAR,
    DATANIX_SANITIZER,
    SAFETRACK_SOS,
    UI_SHOWCASE,
    CYBER_RESILIENCE,
    SATLINK
}

data class EducationItem(
    val degree: String,
    val institution: String,
    val score: String,
    val period: String,
    val level: String
)

data class AwardItem(
    val title: String,
    val organizer: String,
    val date: String,
    val prizeLevel: String // "1st Prize", "2nd Prize", "Participant"
)

data class CertificationItem(
    val title: String,
    val issuer: String,
    val date: String,
    val code: String? = null
)

data class InternshipItem(
    val role: String,
    val company: String,
    val location: String,
    val period: String,
    val achievements: List<String>,
    val toolsUsed: List<String>
)

data class SkillCategory(
    val categoryName: String,
    val iconName: String,
    val skills: List<String>
)

data class UserProfile(
    val name: String,
    val location: String,
    val phone: String,
    val email: String,
    val linkedin: String,
    val objective: String,
    val currentRole: String,
    val leadership: String,
    val languages: List<Pair<String, String>>, // Language to fluency
    val hobbies: List<String>
)
