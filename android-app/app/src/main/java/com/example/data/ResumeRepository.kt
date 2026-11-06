package com.example.data

import com.example.model.*

object ResumeRepository {

    val profile = UserProfile(
        name = "HARIKRISHNAN",
        location = "Tirupur, Tamil Nadu",
        phone = "+91 7639294309",
        email = "harikrishnansenthil79@gmail.com",
        linkedin = "https://linkedin.com/in/harikrishnan",
        objective = "Motivated and tech-savvy Computer Science student (Final year) with a strong foundation in cybersecurity, programming, and hardware–software integration. Experienced in developing full-stack applications using Python (Flask), and MongoDB, with hands-on experience in cloud-based systems and real-world project implementation. Passionate about transforming innovative ideas into practical, scalable solutions.",
        currentRole = "Final Year B.E. CSE Student | Cybersecurity & IoT Developer",
        leadership = "Project Team Leader @ Akshaya College of Engineering & Tech (Oct 2023 – Present)",
        languages = listOf(
            "English" to "Fluent",
            "Tamil" to "Native"
        ),
        hobbies = listOf(
            "Chess (College-level player, team captain experience)",
            "Public Speaking & Project Building",
            "Participating in Hackathons",
            "Participating in Paper Presentations",
            "Participating in Cybersecurity Events",
            "Poster, Flyer, and Graphic Design Creation"
        )
    )

    val educationList = listOf(
        EducationItem(
            degree = "B.E. Computer Science and Engineering",
            institution = "Akshaya College of Engineering and Technology",
            score = "7.27 CGPA",
            period = "2023 – 2027",
            level = "Undergraduate"
        ),
        EducationItem(
            degree = "HSC (Higher Secondary Certificate)",
            institution = "Sri Sai Matriculation Higher Secondary School, Tirupur",
            score = "71%",
            period = "2021 – 2023",
            level = "Higher Secondary"
        ),
        EducationItem(
            degree = "SSLC (Secondary School Leaving Certificate)",
            institution = "Sri Sai Matriculation Higher Secondary School, Tirupur",
            score = "70.6%",
            period = "2020 – 2021",
            level = "Secondary School"
        )
    )

    val projectList = listOf(
        ProjectItem(
            id = "proj_cyberresilience",
            title = "CyberResilience AI",
            subtitle = "Intelligent Vulnerability Assessment & Intrusion Detection",
            description = "AI-driven cybersecurity platform analysing network telemetry, detecting anomalies via sequential LSTM/GRU models, and predicting multi-step attack progression with automated MITRE ATT&CK mapping.",
            shortDescription = "AI-powered network intrusion detection & vulnerability platform using LSTM/GRU forecasting, Scapy packet analysis, and MITRE ATT&CK mapping.",
            bullets = listOf(
                "Developing an AI-driven cybersecurity platform to analyse network telemetry, identify suspicious activity, and forecast attack progression.",
                "Designing temporal ML pipeline transforming PCAP/network-flow data into time-windowed states using LSTM/GRU architectures.",
                "Integrating Scapy-based packet feature extraction and MITRE ATT&CK technique mapping.",
                "Built full-stack React/Vite dashboard and FastAPI backend with Nmap, Nikto, Suricata, and Zeek integration."
            ),
            technologies = listOf("React/Vite", "FastAPI", "Python", "Scapy", "LSTM/GRU", "Nmap", "Suricata", "Zeek", "Wireshark"),
            award = "AI Security Flagship",
            status = "Active Development",
            category = "Cybersecurity & AI",
            demoType = DemoType.CYBER_RESILIENCE,
            visualType = ProjectVisualType.CYBER_RESILIENCE,
            githubUrl = "https://github.com/Krishnan1212006",
            keyHighlight = "LSTM/GRU Neural Pipeline & MITRE ATT&CK"
        ),
        ProjectItem(
            id = "proj_datanix",
            title = "Datanix",
            subtitle = "AI-Assisted Secure Data Sanitization Platform",
            description = "AI-powered secure data sanitization platform permanently erasing sensitive data aligned with NIST SP 800-88 standards with cryptographic audit logging and forensic validation.",
            shortDescription = "NIST SP 800-88 compliant secure data destruction system with AI classification, Kali Linux wipe utilities, and tamper-evident audit logs.",
            bullets = listOf(
                "Developed an AI-powered secure data sanitization platform that identifies, analyzes, and permanently erases sensitive data.",
                "Integrated Kali Linux secure wipe utilities (shred, dd, nvme-cli) aligned with NIST SP 800-88.",
                "Implemented AI-assisted file classification, risk assessment, and trust-score generation.",
                "Built automated audit logging, PDF/JSON certificate generation, and Autopsy forensic validation."
            ),
            technologies = listOf("Python", "Flask", "Electron.js", "JavaScript", "MongoDB", "Kali Linux", "Autopsy"),
            award = "NIST SP 800-88 Aligned",
            status = "In Progress",
            category = "Cybersecurity & AI",
            demoType = DemoType.DATANIX_SANITIZER,
            visualType = ProjectVisualType.DATANIX_SANITIZER,
            githubUrl = "https://github.com/Krishnan1212006",
            keyHighlight = "Permanent Secure Wipe & Forensic Proof"
        ),
        ProjectItem(
            id = "proj_safetrack",
            title = "SafeTrack SOS",
            subtitle = "Smart Emergency Alert & Location Tracking System",
            description = "IoT-based emergency alert system with hardware button trigger, GPS/GSM telemetry, live Google Maps links, and interactive real-time police dashboard.",
            shortDescription = "Instant IoT SOS system triggering real-time GPS tracking, GSM notifications, and web dashboard mapping for rapid emergency response.",
            bullets = listOf(
                "Built an IoT-based emergency alert system with real-time location tracking and SOS notifications.",
                "Instant SOS triggering via ESP32 hardware button or web interface with OLED telemetry display.",
                "Captures real-time GPS coordinates, generates live Google Maps links, and displays alerts on live dashboard.",
                "Designed with LoRa fallback and lithium battery backup for critical emergencies."
            ),
            technologies = listOf("ESP32", "Python", "Embedded C", "Google Maps API", "GPS Module", "GSM Module", "LoRa", "OLED"),
            award = "Tech Day Winner",
            status = "Completed",
            category = "Hardware & IoT",
            demoType = DemoType.SAFETRACK_SOS,
            visualType = ProjectVisualType.SAFETRACK_SOS,
            githubUrl = "https://github.com/Krishnan1212006",
            keyHighlight = "ESP32 Hardware SOS + Google Maps Telemetry"
        ),
        ProjectItem(
            id = "proj_satlink",
            title = "SATLINK",
            subtitle = "Cybersecure Satellite Communication Module",
            description = "Secure IoT-based satellite communication module utilizing ESP32-S3 and Iridium constellation with end-to-end AES encryption, device authentication, and replay protection.",
            shortDescription = "Secure satellite telemetry & emergency SOS system over Iridium network with robust encryption and replay protection.",
            bullets = listOf(
                "Developing secure IoT-based satellite communication system using ESP32-S3, Iridium, GNSS, and BLE.",
                "Implementing end-to-end AES-256 encryption, device authentication, secure key storage, and replay protection.",
                "Analyzing security threats across firmware, BLE, satellite link, and cloud APIs.",
                "Secure GNSS coordinate transmission with cloud-based emergency dispatch."
            ),
            technologies = listOf("ESP32-S3", "Iridium SBD", "GNSS", "BLE", "AES-256", "Embedded C", "Python"),
            award = "Satellite Tech Innovation",
            status = "Prototype In Progress",
            category = "Hardware & IoT",
            demoType = DemoType.SATLINK,
            visualType = ProjectVisualType.SATLINK_SATELLITE,
            githubUrl = "https://github.com/Krishnan1212006",
            keyHighlight = "Iridium Satellite Orbit Link + Hardware Crypto"
        ),
        ProjectItem(
            id = "proj_car",
            title = "Autonomous Obstacle Car",
            subtitle = "Arduino & Sensor-Based Smart Vehicle",
            description = "Arduino-based autonomous vehicle with ultrasonic real-time obstacle avoidance, path calculation, live location reporting, and collision warning alerts.",
            shortDescription = "Autonomous smart vehicle featuring ultrasonic sonar obstacle detection, dynamic pathing, and emergency collision safeguards.",
            bullets = listOf(
                "Developed Arduino-based autonomous vehicle with real-time obstacle detection using ultrasonic sensors.",
                "Integrated servo-steered ultrasonic sweep with dynamic steering obstacle avoidance algorithm.",
                "Integrated navigation, live location sharing, and emergency alerts.",
                "Awarded 1st Prize at Demo Day project competition."
            ),
            technologies = listOf("Arduino", "Embedded C", "Python", "Ultrasonic Sensors", "GPS", "Motor Drivers"),
            award = "1st Prize @ Demo Day",
            status = "Completed",
            category = "Hardware & IoT",
            demoType = DemoType.OBSTACLE_CAR,
            visualType = ProjectVisualType.OBSTACLE_CAR,
            githubUrl = "https://github.com/Krishnan1212006",
            keyHighlight = "1st Prize Winner @ Demo Day Competition"
        ),
        ProjectItem(
            id = "proj_ui_ux",
            title = "Graphic Design & UI/UX",
            subtitle = "Mobile App Prototypes & Branding Assets",
            description = "Comprehensive user interfaces, mobile app prototypes, design systems, and digital marketing materials crafted in Figma, Adobe Illustrator, and Photoshop.",
            shortDescription = "User-centered mobile app prototypes, high-fidelity UI systems, and vector branding created with Figma and Adobe Suite.",
            bullets = listOf(
                "Designed modern, accessible user interfaces, mobile app prototypes, and design systems.",
                "Created user-centered UX architectures tested with real-world student workflows.",
                "Crafted vector graphics, brand identities, posters, and award-winning logo designs (2nd Prize Logo Lingo)."
            ),
            technologies = listOf("Figma", "Adobe Photoshop", "Adobe Illustrator", "Canva", "UI/UX"),
            award = "2nd Prize Logo Lingo",
            status = "Portfolio Collection",
            category = "Design",
            demoType = DemoType.UI_SHOWCASE,
            visualType = ProjectVisualType.UI_UX_DESIGN,
            githubUrl = "https://github.com/Krishnan1212006",
            keyHighlight = "Figma Design System & Award-Winning Branding"
        )
    )

    val internshipList = listOf(
        InternshipItem(
            role = "Cybersecurity & Ethical Hacking Intern",
            company = "Hack up Technology",
            location = "Coimbatore",
            period = "23 June 2025 – 23 July 2025",
            achievements = listOf(
                "Completed a 1-month intensive internship focused on cybersecurity and ethical hacking.",
                "Performed network scanning and vulnerability assessment using Nmap.",
                "Practiced penetration testing on Hack The Box and TryHackMe platforms.",
                "Worked with data recovery tools and forensic recovery techniques.",
                "Conducted web security testing using Burp Suite and network analysis using Wireshark.",
                "Developed skills in identifying, analyzing, and mitigating security threats."
            ),
            toolsUsed = listOf("Nmap", "Burp Suite", "Wireshark", "Hack The Box", "TryHackMe", "Data Recovery Tools")
        )
    )

    val skillCategories = listOf(
        SkillCategory(
            categoryName = "Cybersecurity",
            iconName = "Security",
            skills = listOf("Nmap", "Burp Suite", "Wireshark", "Penetration Testing", "Hack The Box", "TryHackMe", "Data Recovery Tools", "Kali Linux", "Autopsy")
        ),
        SkillCategory(
            categoryName = "Programming",
            iconName = "Code",
            skills = listOf("Python", "C", "C++", "Embedded C", "JavaScript", "HTML", "CSS")
        ),
        SkillCategory(
            categoryName = "Hardware & IoT",
            iconName = "DeveloperBoard",
            skills = listOf("Arduino", "ESP32", "Ultrasonic Sensors", "GPS Modules", "Hardware Servicing")
        ),
        SkillCategory(
            categoryName = "Web & Cloud / DB",
            iconName = "Cloud",
            skills = listOf("Flask", "MongoDB", "Google Maps API", "Oracle Cloud Infrastructure")
        ),
        SkillCategory(
            categoryName = "Tools & OS",
            iconName = "Build",
            skills = listOf("Git", "GitHub", "Google Colab", "Linux", "Ubuntu", "Windows")
        ),
        SkillCategory(
            categoryName = "UI/UX & Design",
            iconName = "Palette",
            skills = listOf("Figma", "Adobe Photoshop", "Adobe Illustrator", "Canva")
        ),
        SkillCategory(
            categoryName = "Soft Skills",
            iconName = "Groups",
            skills = listOf("Problem Solving", "Quick Learning", "Teamwork", "Public Speaking", "Team Leadership")
        )
    )

    val awardsList = listOf(
        AwardItem(
            title = "First Prize – Demo Day Project Competition",
            organizer = "Akshaya College of Engineering and Technology",
            date = "Nov 2023",
            prizeLevel = "1st Prize"
        ),
        AwardItem(
            title = "First Prize – Paper Presentation Competition",
            organizer = "Hindustan College of Engineering",
            date = "Mar 2026",
            prizeLevel = "1st Prize"
        ),
        AwardItem(
            title = "First Prize – Tech Day Project Competition",
            organizer = "Akshaya College of Engineering and Technology",
            date = "Mar 2026",
            prizeLevel = "1st Prize"
        ),
        AwardItem(
            title = "Second Prize – Logo Lingo Competition",
            organizer = "PSG College of Arts & Science",
            date = "Mar 2025",
            prizeLevel = "2nd Prize"
        ),
        AwardItem(
            title = "Participant – C-DAC BOSS OS National Bug Bounty Challenge 2026",
            organizer = "VIT Chennai Hub",
            date = "2026",
            prizeLevel = "National Participant"
        )
    )

    val certificationsList = listOf(
        CertificationItem(
            title = "Oracle Cloud Infrastructure 2025 Certified Foundations Associate",
            issuer = "Oracle University",
            date = "May 2025",
            code = "OCI-2025-FA"
        ),
        CertificationItem(
            title = "Full Stack Development Certification",
            issuer = "NoviTech R&D Private Limited",
            date = "Apr 2025"
        ),
        CertificationItem(
            title = "Graphic Designing Certification",
            issuer = "Fortune Innovative",
            date = "Oct 2023"
        ),
        CertificationItem(
            title = "Diploma in Computer Hardware Servicing",
            issuer = "Mohan Institute of Technology",
            date = "Jul 2023"
        )
    )
}
