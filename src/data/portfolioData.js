export const profileData = {
  name: "Hari Krishnan.S",
  title: "Cybersecurity Engineer | Embedded IoT Developer | AI & Frontend Developer",
  degree: "B.E. Computer Science and Engineering",
  institution: "Akshaya College of Engineering and Technology",
  cgpa: "7.27 CGPA",
  period: "2023–2027",
  location: "Tirupur, Tamil Nadu, India",
  email: "harikrishnansenthil79@gmail.com",
  phone: "+91 7639294309",
  github: "https://github.com/Krishnan1212006",
  githubUsername: "Krishnan1212006",
  linkedin: "https://linkedin.com/in/hari-krishnan-031b46284",
  resumePdfUrl: "./assets/documents/HariKrishnan_Resume.pdf",
  avatarUrl: "./assets/images/avatar.jpg",
  heroBannerUrl: "./assets/images/hero-banner.jpg",
  bio: "Motivated final-year CS student with a strong foundation in cybersecurity, IoT-based systems, and frontend/UI design. Experienced in building secure data-sanitization tools, IoT hardware (ESP32, sensor integration, Fusion 360 design) with real-time dashboards, and clean UI/branding using Figma.",
  objective: "Motivated final-year CS student with a strong foundation in cybersecurity, IoT-based systems, and frontend/UI design. Experienced in building secure data-sanitization tools, IoT hardware (ESP32, sensor integration, Fusion 360 design) with real-time dashboards, and clean UI/branding using Figma. Passionate about turning ideas into practical, scalable solutions."
};

export const projectsData = [
  {
    id: "datanix",
    title: "Datanix",
    subtitle: "AI-Assisted Secure Data Sanitization Platform",
    category: "cybersecurity",
    categories: ["cybersecurity", "ai-ml"],
    categoryLabel: "Cybersecurity & Data Privacy",
    year: "In Progress",
    badgeColor: "cyan",
    icon: "fa-hard-drive",
    highlight: "NIST SP 800-88 & DoD 5220.22-M Aligned Cryptographic Wipe",
    shortDesc: "AI-powered secure data sanitization platform permanently erasing sensitive data from files, folders, and storage devices using Kali Linux wipe utilities (shred, dd, nvme-cli) and Autopsy forensic validation.",
    fullDesc: "Developed an AI-powered secure data sanitization platform that helps users identify, analyze, and permanently erase sensitive data from files, folders, and storage devices. Integrated Kali Linux secure wipe utilities to perform NIST SP 800-88 compliant sanitization and prevent forensic recovery.",
    problemStatement: "Organizations and individuals frequently discard old storage media containing unencrypted or soft-deleted sensitive data, leaving them vulnerable to forensic carving and regulatory compliance breaches (GDPR, HIPAA).",
    proposedSolution: "A unified platform integrating automated risk-based file classification with certified multi-pass hardware wiping algorithms (DoD 5220.22-M, NIST SP 800-88 Rev. 1 Clear/Purge) and tamper-evident audit certificate generation.",
    architecture: "Frontend built in Electron.js and React/Vite communicating with a secure Python/Flask engine that orchestrates low-level Linux disk commands (nvme-cli, shred, blkdiscard) through an isolated privileged service layer.",
    bullets: [
      "AI-assisted file classification and risk assessment for informed data disposal decisions.",
      "Integrated Kali Linux low-level wipe utilities (shred, dd, nvme-cli, ATA Secure Erase).",
      "Tamper-proof PDF & JSON sanitization certificate generation with SHA-256 audit logs.",
      "Forensic verification testing performed using Autopsy to validate non-recoverability."
    ],
    realWorldApplications: "Enterprise IT asset decommissioning, defense electronics disposal, healthcare data sanitization compliance, and secure personal drive wipes before resale.",
    status: "Active Development (Benchmarking pass speeds)",
    techs: ["Python", "Flask", "Electron.js", "JavaScript", "MongoDB", "Kali Linux", "Autopsy", "NIST SP 800-88", "DoD 5220.22-M"],
    hasLiveSim: true,
    githubUrl: "https://github.com/Krishnan1212006"
  },
  {
    id: "snid",
    title: "SNID / CyberResilience AI",
    subtitle: "Smart Network Intrusion Detector & Threat Telemetry",
    category: "cybersecurity",
    categories: ["cybersecurity", "ai-ml"],
    categoryLabel: "Cybersecurity & AI",
    year: "Active Development",
    badgeColor: "purple",
    icon: "fa-shield-halved",
    highlight: "LSTM/GRU Neural Pipeline, MITRE ATT&CK & Automated Quarantine",
    shortDesc: "AI-driven cybersecurity platform for network threat detection, vulnerability assessment, risk analysis, and future attack progression forecasting using LSTM/GRU models and Scapy packet telemetry.",
    fullDesc: "Smart Network Intrusion Detector (SNID) utilizing temporal machine learning pipelines to process PCAP and network-flow data, perform time-window aggregation, and predict multi-step network-state transitions aligned with the MITRE ATT&CK framework.",
    problemStatement: "Modern cyberattacks like zero-day lateral movement and multi-stage exfiltration bypass static signature firewalls, causing critical delays before security analysts can detect and isolate breach vectors.",
    proposedSolution: "A deep learning intrusion detection architecture leveraging bidirectional LSTM/GRU recurrent neural networks trained on packet time-series telemetry to catch anomalies in real time and trigger dynamic iptables quarantine.",
    architecture: "Ingress packet capture pipeline powered by Scapy and Zeek feeding into a PyTorch inference engine with SHAP explainability, backed by a FastAPI microservice and real-time React dashboard.",
    bullets: [
      "Processes live PCAP flows and aggregates temporal features across sliding 5-second windows.",
      "Maps attack patterns directly to MITRE ATT&CK Enterprise techniques (T1498, T1048, T1059).",
      "Integrates security assessment tools including Nmap, Nikto, Wapiti, SQLMap, and Gobuster.",
      "Real-time reactive quarantine dispatch executing automated iptables rules."
    ],
    realWorldApplications: "Corporate SOC monitoring, cloud perimeter intrusion prevention, zero-trust network policy enforcement, and critical infrastructure SCADA telemetry defense.",
    status: "Model Trained & Ingress Pipeline Active",
    techs: ["Python", "PyTorch (LSTM/GRU)", "FastAPI", "React.js", "Scapy", "Zeek", "Wazuh", "Nmap", "Wireshark", "SHAP"],
    hasLiveSim: true,
    githubUrl: "https://github.com/Krishnan1212006"
  },
  {
    id: "satlink",
    title: "SATLINK Ground Station",
    subtitle: "Cybersecure Satellite Communication Module",
    category: "iot",
    categories: ["iot"],
    categoryLabel: "Aerospace & Embedded IoT",
    year: "Hardware Prototype",
    badgeColor: "sky",
    icon: "fa-satellite-dish",
    highlight: "AES-256 Encrypted Orbital Telemetry & Packet Decoding",
    shortDesc: "Ground terminal transceiver decoding low-earth orbit satellite telemetry with automatic Doppler correction and cryptographic payload verification.",
    fullDesc: "Ground station telemetry terminal listening on UHF/VHF frequencies. Decodes orbital packet downlinks from CubeSats and low-earth orbit payloads. Integrates AES-256 cryptographic verification to defend satellite commanding against spoofing attacks.",
    problemStatement: "Unencrypted satellite commanding and telemetry links are vulnerable to ground-based RF spoofing, replay attacks, and unauthorized command injection in amateur and university CubeSat constellations.",
    proposedSolution: "A cybersecure ground terminal hardware transceiver featuring AES-256-GCM authenticated encryption for uplinks and real-time algorithmic Doppler frequency tracking for high-velocity LEO passes.",
    architecture: "ESP32-S3 microcontroller coupled with SX1278 LoRa RF front-end, high-gain directional antenna, GNSS coordinate sync, and Web-based ground control dashboard.",
    bullets: [
      "Decodes UHF/VHF downlink telemetry packets from low-earth orbit payloads.",
      "Implements real-time algorithmic Doppler shift compensation (+/- 2.4 kHz).",
      "Hardware-accelerated AES-256-GCM verification ensuring message authenticity and integrity.",
      "Visual ground track prediction and orbit pass calculator."
    ],
    realWorldApplications: "University CubeSat tracking, disaster relief satellite relay ground stations, and secure remote telemetry uplinks.",
    status: "Hardware Prototype Tested",
    techs: ["ESP32-S3", "LoRa SX1278", "AES-256-GCM", "C++", "Orbital Doppler Algorithm", "MQTT", "GNSS"],
    hasLiveSim: true,
    githubUrl: "https://github.com/Krishnan1212006"
  },
  {
    id: "safetrack",
    title: "SafeTrack SOS",
    subtitle: "Smart Emergency Alert & Location Tracking System",
    category: "iot",
    categories: ["iot"],
    categoryLabel: "Embedded IoT & Public Safety",
    year: "Hardware Built",
    badgeColor: "rose",
    icon: "fa-tower-broadcast",
    highlight: "Sub-3-Sec ESP32 Trigger, GPS Uplink & Police Dashboard",
    shortDesc: "IoT emergency alert system enabling instant SOS triggering via ESP32 hardware button or web interface, with real-time GPS coordinates, Google Maps links, and interactive police dashboard.",
    fullDesc: "Developed SafeTrack SOS, an IoT-based emergency alert system that enables instant SOS triggering via ESP32 hardware button or web interface. The system captures real-time location data, generates live Google Maps links, and displays alerts on a police dashboard using interactive mapping.",
    problemStatement: "During personal safety threats or medical emergencies, unlocking smartphones and typing locations takes too long and fails in low-connectivity situations.",
    proposedSolution: "A discrete wearable device with a tactile panic button that locks GPS coordinates, sends emergency cellular SMS alerts in under 3 seconds, and streams live coordinates to an interactive emergency dashboard.",
    architecture: "ESP32 micro-controller interfaced with NEO-6M GPS receiver and SIM800L GSM/GPRS module, powered by a TP4056 rechargeable Li-Po circuit in a custom 3D-modeled casing.",
    bullets: [
      "Instant SOS triggering via hardware button debounce or encrypted web API.",
      "Sub-3-second automated SMS dispatch with exact latitude/longitude Google Maps link.",
      "Interactive emergency dashboard built for first responders.",
      "LoRa RF fallback communication when cellular networks are congested or unavailable."
    ],
    realWorldApplications: "Women and child safety, solitary worker monitoring, elderly medical emergency detection, and remote trekker tracking.",
    status: "Completed & Deployed Prototype",
    techs: ["ESP32", "NEO-6M GPS", "SIM800L GSM", "LoRa", "Embedded C", "Python", "Google Maps API", "Fusion 360"],
    hasLiveSim: true,
    githubUrl: "https://github.com/Krishnan1212006"
  },
  {
    id: "vulnai",
    title: "VulnAI",
    subtitle: "AI-Based Website Vulnerability Detection",
    category: "ai-ml",
    categories: ["cybersecurity", "ai-ml", "web"],
    categoryLabel: "AI & Web Security",
    year: "Active Development",
    badgeColor: "amber",
    icon: "fa-bug",
    highlight: "Automated OWASP Top 10 Scanner & ML Threat Heuristics",
    shortDesc: "AI-assisted web application vulnerability assessment engine identifying SQL injections, Cross-Site Scripting (XSS), insecure headers, and exposed endpoints.",
    fullDesc: "VulnAI scans web targets using heuristic machine learning models combined with automated security probes to identify vulnerabilities listed in OWASP Top 10 with actionable mitigation recommendations.",
    problemStatement: "Small organizations often lack dedicated cybersecurity auditing teams, allowing standard web vulnerabilities like SQL injection and blind XSS to go unnoticed until exploited by threat actors.",
    proposedSolution: "An automated vulnerability assessment scanner that crawls web domains, evaluates DOM injections and response headers, and scores threat severity using ML classification models.",
    architecture: "Python asynchronous crawling engine utilizing BeautifulSoup and Scrapy, integrated with Nmap and OWASP ZAP CLI, outputting structured risk matrices to a React frontend.",
    bullets: [
      "Automated discovery of SQL injection, reflected XSS, and security misconfigurations.",
      "ML-based confidence scoring to filter out benign scanner false positives.",
      "Generates executive-ready PDF audit reports with remediation instructions.",
      "API-driven scanning pipeline suitable for CI/CD DevSecOps integration."
    ],
    realWorldApplications: "Web application penetration testing, continuous DevSecOps pipeline verification, and automated vulnerability scanning for educational institutions.",
    status: "In Development",
    techs: ["Python", "React.js", "OWASP ZAP", "Nmap", "Scikit-learn", "FastAPI", "SQLMap", "BeautifulSoup"],
    hasLiveSim: false,
    githubUrl: "https://github.com/Krishnan1212006"
  },
  {
    id: "campusid",
    title: "CampusID+",
    subtitle: "Student Career & Identity Platform",
    category: "web",
    categories: ["web"],
    categoryLabel: "Web Development",
    year: "Completed Project",
    badgeColor: "teal",
    icon: "fa-id-card",
    highlight: "Role-Based Access Control, Digital Profiles & Placement Portal",
    shortDesc: "Comprehensive student digital identity and career platform built during Hackup Technology internship with secure role-based access control.",
    fullDesc: "Engineered CampusID+ as part of the advanced internship at Hackup Technology Pvt. Ltd. The system provides secure digital verification of academic credentials, student achievements, and direct placement application tracking.",
    problemStatement: "Traditional university placement tracking relies on fragmented paper records and insecure spreadsheets, resulting in data inaccuracies and credential forgery risks.",
    proposedSolution: "A secure cloud-native web portal featuring encrypted credentials, role-based access control (Student, Recruiter, Admin), and automated resume matching.",
    architecture: "React.js dynamic frontend with responsive Tailwind CSS components connected to a Supabase backend with row-level security policies (RLS).",
    bullets: [
      "Role-Based Access Control (RBAC) ensuring data confidentiality across student and recruiter tiers.",
      "Verified academic portfolio generation with downloadable PDF profiles.",
      "Automated job eligibility filtering based on CGPA and department criteria.",
      "Direct integration with institutional databases via secure REST APIs."
    ],
    realWorldApplications: "College placement cell operations, student career portfolio showcases, and academic record verification.",
    status: "Completed (Hackup Tech Project)",
    techs: ["React.js", "Supabase", "JavaScript", "HTML5", "CSS3", "Tailwind CSS", "Git"],
    hasLiveSim: false,
    githubUrl: "https://github.com/Krishnan1212006"
  },
  {
    id: "holoapp",
    title: "HoloApp",
    subtitle: "Holographic Media Display Application",
    category: "web",
    categories: ["web", "iot"],
    categoryLabel: "Creative Tech & Frontend",
    year: "Project",
    badgeColor: "pink",
    icon: "fa-cube",
    highlight: "Quad-Viewport Pyramidal Hologram Projection",
    shortDesc: "Interactive web media application splitting 3D visual assets and animations into four synchronized mirrored quadrants for pyramidal prism holographic display.",
    fullDesc: "HoloApp renders 3D models and interactive media onto a calibrated quad-viewport layout specifically designed for physical four-sided reflection pyramids, producing the visual illusion of a floating 3D hologram.",
    problemStatement: "Traditional digital showcases are flat and 2D, lacking the engaging, tangible immersion necessary for compelling public technology demonstrations.",
    proposedSolution: "A lightweight web app that dynamically renders any 3D asset or video in four calibrated orthogonal orientations (Top, Bottom, Left, Right) to project seamlessly into holographic glass pyramids.",
    architecture: "Built using HTML5 Canvas, modern CSS 3D transforms, and WebGL rendering running natively in mobile and desktop browsers.",
    bullets: [
      "Real-time synchronized four-way viewport rendering for reflection prisms.",
      "Interactive 3D model rotation and lighting control directly via touch or mouse.",
      "Optimized for high-contrast presentation on OLED and mobile screens.",
      "Zero external plugin requirement; operates entirely inside modern browsers."
    ],
    realWorldApplications: "Museum exhibit kiosks, tech fest demonstrations, interactive educational biology/engineering models, and retail product displays.",
    status: "Completed",
    techs: ["JavaScript", "HTML5 Canvas", "CSS3 3D Transforms", "Figma", "UI/UX Design"],
    hasLiveSim: false,
    githubUrl: "https://github.com/Krishnan1212006"
  },
  {
    id: "obstacle",
    title: "Autonomous Obstacle Rover",
    subtitle: "Arduino Ultrasonic Radar Rover",
    category: "iot",
    categories: ["iot"],
    categoryLabel: "Robotics & Embedded",
    year: "1st Prize Demo Day",
    badgeColor: "amber",
    icon: "fa-robot",
    highlight: "Real-Time Ultrasonic Path Planning & Servo Radar",
    shortDesc: "Self-driving robotic vehicle with HC-SR04 ultrasonic radar scanning, servo sweep mapping, and dynamic trajectory replanning via Arduino Uno and L298N driver.",
    fullDesc: "Autonomous mobile rover navigating unstructured environments without external GPS. Utilizes ultrasonic distance ranging mapped across a 180-degree servo sweep to calculate shortest unobstructed passage vectors via Arduino Uno and L298N motor driver. Awarded First Prize at Demo Day Project Competition.",
    problemStatement: "Mobile robots operating in unknown indoor environments need collision avoidance without expensive multi-thousand-dollar LiDAR equipment.",
    proposedSolution: "An intelligent ultrasonic radar module that scans the frontal hemisphere in 15-degree increments using a servo motor to calculate real-time collision-free vectors.",
    architecture: "Arduino Uno ATmega328P executing embedded C navigation state machines, controlling twin DC geared motors through an L298N dual H-bridge driver.",
    bullets: [
      "Autonomous obstacle detection using HC-SR04 ultrasonic sensor with 180-degree servo sweep.",
      "Dynamic obstacle avoidance algorithm executing real-time trajectory replanning.",
      "Power management regulation for twin DC drive motors and logic boards.",
      "Awarded 1st Prize at Demo Day Project Competition at Akshaya College of Engineering."
    ],
    realWorldApplications: "Automated guided vehicles (AGVs) in warehouses, educational robotics platforms, and hazardous area inspection rovers.",
    status: "Hardware Verified (1st Prize Awarded)",
    techs: ["Arduino Uno", "HC-SR04 Ultrasonic", "SG90 Servo", "L298N H-Bridge", "Embedded C"],
    hasLiveSim: false,
    githubUrl: "https://github.com/Krishnan1212006"
  },
  {
    id: "design",
    title: "Graphic Design & Fusion 360",
    subtitle: "UI/UX Interfaces, Visual Identity & 3D Hardware Casings",
    category: "web",
    categories: ["web", "iot"],
    categoryLabel: "Design & Hardware CAD",
    year: "2nd Prize Logo Lingo",
    badgeColor: "pink",
    icon: "fa-palette",
    highlight: "Modern UI/UX Systems & Fusion 360 Hardware Enclosures",
    shortDesc: "Designed modern, accessible user interfaces, mobile app prototypes, digital marketing materials, and 3D hardware casings using Figma, Adobe Suite, and Autodesk Fusion 360.",
    fullDesc: "Comprehensive digital design portfolio spanning web and mobile interface design in Figma, branding identity systems, vector illustrations, and custom 3D enclosure modeling in Autodesk Fusion 360 for ESP32 and Arduino IoT prototypes.",
    problemStatement: "Hardware engineering projects often fail to achieve real-world adoption due to unpolished visual presentation and unhoused fragile circuit boards.",
    proposedSolution: "An integrated approach pairing clean user-centered Figma UI/UX prototypes with precision Autodesk Fusion 360 parametric 3D enclosures for robust physical prototyping.",
    architecture: "Figma design systems and component libraries paired with Autodesk Fusion 360 parametric CAD modeling for 3D-printable electronic casings.",
    bullets: [
      "Designed user interfaces, mobile app prototypes, and brand identities tested with real users.",
      "Modelled custom 3D enclosures for ESP32 boards with precise port and antenna cutouts.",
      "Awarded 2nd Prize in Logo Lingo Graphic Design Competition at PSG College of Arts & Science.",
      "Created technical diagrams, vector iconography, and typography hierarchies."
    ],
    realWorldApplications: "Product development sprints, custom electronics enclosure fabrication, and commercial UI/UX design systems.",
    status: "Completed Portfolio",
    techs: ["Figma", "Autodesk Fusion 360", "Adobe Photoshop", "Adobe Illustrator", "Canva", "UI/UX Systems"],
    hasLiveSim: false,
    githubUrl: "https://github.com/Krishnan1212006"
  }
];

export const experienceData = [
  {
    role: "Ethical Hacking & Cybersecurity Intern",
    company: "Hackup Technology Pvt. Ltd.",
    location: "Coimbatore, Tamil Nadu",
    period: "17 June 2026 – 17 July 2026",
    badge: "INTERNSHIP III",
    desc: "Advanced internship covering web application penetration testing, vulnerability assessment, IoT security, network hardening, and incident handling procedures.",
    bullets: [
      "Gained practical exposure to ethical hacking techniques and cybersecurity tools.",
      "Worked on web application penetration testing and vulnerability assessment.",
      "Explored IoT security, network hardening, and security best practices.",
      "Studied incident handling procedures and vulnerability identification.",
      "Completed a Campus ID + Project as part of the internship."
    ],
    tools: ["Ethical Hacking", "Web Pentesting", "Vulnerability Assessment", "IoT Security", "Network Hardening", "Incident Handling"]
  },
  {
    role: "Ethical Hacking & Cybersecurity Intern",
    company: "Hackup Technology Pvt. Ltd.",
    location: "Coimbatore, Tamil Nadu",
    period: "18 December 2025 – 18 January 2026",
    badge: "INTERNSHIP II",
    desc: "Focused on data recovery tools, cybersecurity methodologies, and IoT-based SOS emergency response implementations.",
    bullets: [
      "Gained hands-on exposure to data recovery tools, cybersecurity tools, and techniques.",
      "Worked on an IoT-based SOS Emergency Response System project.",
      "Explored the application of IoT technologies in emergency response and security solutions."
    ],
    tools: ["Data Recovery Tools", "Cybersecurity Tools", "IoT SOS System", "Forensics", "Emergency Tech"]
  },
  {
    role: "Ethical Hacking & Cybersecurity Intern",
    company: "Hackup Technology Pvt. Ltd.",
    location: "Coimbatore, Tamil Nadu",
    period: "23 June 2025 – 23 July 2025",
    badge: "INTERNSHIP I",
    desc: "Intensive one-month foundation internship focused on network scanning, penetration testing, and security analysis.",
    bullets: [
      "Completed an intensive one-month internship focused on cybersecurity and ethical hacking.",
      "Performed network scanning and vulnerability assessments using Nmap.",
      "Practised penetration testing through Hack The Box and TryHackMe platforms.",
      "Conducted web application security testing using Burp Suite and network traffic analysis using Wireshark.",
      "Gained practical knowledge of identifying, analysing, and mitigating security threats."
    ],
    tools: ["Nmap", "Burp Suite", "Wireshark", "Hack The Box", "TryHackMe", "Security Analysis"]
  }
];

export const skillsData = [
  {
    category: "Programming",
    icon: "fa-code",
    color: "cyan",
    skills: ["C", "Python"]
  },
  {
    category: "Web Development",
    icon: "fa-globe",
    color: "teal",
    skills: ["HTML", "CSS", "React js"]
  },
  {
    category: "Cybersecurity",
    icon: "fa-shield-virus",
    color: "purple",
    skills: [
      "Nmap",
      "Burp Suite",
      "Wireshark",
      "Penetration Testing (Hack The Box, TryHackMe)",
      "Data Recovery Tools",
      "CyberTools",
      "Cybersecurity with IOT",
      "Zeek",
      "Wazuh"
    ]
  },
  {
    category: "Databases",
    icon: "fa-database",
    color: "amber",
    skills: ["MongoDB", "MySQL", "Supabase"]
  },
  {
    category: "Hardware & IoT",
    icon: "fa-microchip",
    color: "rose",
    skills: [
      "ESP32",
      "Sensor Integration",
      "Fusion 360 3D Design",
      "GPS Modules (NEO-6M)",
      "GSM Modules (SIM800L)",
      "Gyroscopic Modules",
      "LoRa Modules",
      "OLED Displays",
      "TP4056 Charging"
    ]
  },
  {
    category: "Tools & Design",
    icon: "fa-palette",
    color: "pink",
    skills: ["Figma", "Adobe Photoshop", "Adobe Illustrator", "Canva", "Git", "GitHub"]
  },
  {
    category: "Operating Systems",
    icon: "fa-terminal",
    color: "sky",
    skills: ["Linux", "Ubuntu", "Windows"]
  },
  {
    category: "Soft Skills",
    icon: "fa-users",
    color: "emerald",
    skills: ["Problem Solving", "Quick Learning", "Teamwork", "Public Speaking"]
  }
];

export const educationData = [
  {
    degree: "B.E. Computer Science and Engineering",
    institution: "Akshaya College of Engineering and Technology",
    location: "Coimbatore, Tamil Nadu",
    period: "2023–2027",
    score: "7.27 CGPA",
    highlight: "Specializing in Cybersecurity, IoT Hardware Integration, and Modern Frontend Engineering."
  },
  {
    degree: "HSC (Higher Secondary Certificate)",
    institution: "Sri Sai Matriculation Higher Secondary School",
    location: "Tirupur, Tamil Nadu",
    period: "2021–2023",
    score: "71%",
    highlight: "Major: Mathematics, Physics, Chemistry, Computer Science."
  },
  {
    degree: "SSLC (Secondary School Leaving Certificate)",
    institution: "Sri Sai Matriculation Higher Secondary School",
    location: "Tirupur, Tamil Nadu",
    period: "2020–2021",
    score: "70.6%",
    highlight: "Secondary School Examination with honors."
  }
];

export const certificationsData = [
  {
    title: "Diploma in Computer Hardware Servicing",
    issuer: "Mohan Institute of Technology",
    code: "MIT-CHS",
    date: "Jul 2023"
  },
  {
    title: "Graphic Designing Certification",
    issuer: "Fortune Innovative",
    code: "FI-GD",
    date: "Oct 2023"
  }
];

export const awardsData = [
  {
    title: "First Prize – Dream 2 Reality Competition",
    organizer: "Akshaya College of Engineering and Technology",
    date: "Sep 2026",
    icon: "fa-trophy",
    color: "amber",
    badge: "1st Prize"
  },
  {
    title: "First Prize – Tech Day Project Competition",
    organizer: "Akshaya College of Engineering and Technology",
    date: "Mar 2026",
    icon: "fa-medal",
    color: "amber",
    badge: "1st Prize"
  },
  {
    title: "First Prize – Paper Presentation Competition",
    organizer: "Hindustan College of Engineering",
    date: "Mar 2026",
    icon: "fa-award",
    color: "amber",
    badge: "1st Prize"
  },
  {
    title: "Second Prize – Logo Lingo Graphic Design Competition",
    organizer: "PSG College of Arts & Science",
    date: "Mar 2025",
    icon: "fa-palette",
    color: "pink",
    badge: "2nd Prize"
  },
  {
    title: "First Prize – Demo Day Project Competition",
    organizer: "Akshaya College of Engineering and Technology",
    date: "Nov 2023",
    icon: "fa-trophy",
    color: "amber",
    badge: "1st Prize"
  },
  {
    title: "Participant – C-DAC BOSS OS National Bug Bounty Challenge 2026",
    organizer: "VIT Chennai Hub",
    date: "2026",
    icon: "fa-bug",
    color: "purple",
    badge: "National Participant"
  }
];
