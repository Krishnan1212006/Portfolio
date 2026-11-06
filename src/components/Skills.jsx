import React from 'react';
import { useTheme } from '../context/ThemeContext';

const SKILLS_DATA = [
  {
    category: 'Cybersecurity',
    icon: 'fa-shield-halved',
    skills: ['Nmap', 'Burp Suite', 'OWASP ZAP', 'Wazuh', 'Zeek', 'Kali Linux', 'Network Security', 'Vulnerability Assessment'],
  },
  {
    category: 'Programming',
    icon: 'fa-code',
    skills: ['Python', 'C', 'C++', 'Java', 'JavaScript'],
  },
  {
    category: 'Frontend',
    icon: 'fa-globe',
    skills: ['HTML', 'CSS', 'React.js', 'Flutter', 'Figma'],
  },
  {
    category: 'IoT & Embedded Systems',
    icon: 'fa-microchip',
    skills: ['ESP32', 'Embedded Systems', 'IoT Architecture', 'BLE', 'GNSS'],
  },
  {
    category: 'AI & Data',
    icon: 'fa-brain',
    skills: ['Python', 'NumPy', 'Pandas', 'Matplotlib', 'Scikit-learn'],
  },
  {
    category: 'Databases & Cloud',
    icon: 'fa-database',
    skills: ['MySQL', 'MongoDB', 'Supabase', 'Oracle Cloud Infrastructure'],
  },
  {
    category: 'Tools & Forensics',
    icon: 'fa-screwdriver-wrench',
    skills: ['Wireshark', 'Autopsy', 'Git & GitHub', 'Autodesk Fusion 360', 'Adobe Photoshop', 'Canva'],
  },
  {
    category: 'Professional Strengths',
    icon: 'fa-users',
    skills: ['Problem Solving', 'Quick Learning', 'Teamwork', 'Public Speaking', 'Technical Leadership'],
  },
];

export default function Skills() {
  const { isDark } = useTheme();

  const accent   = isDark ? '#D4AF37' : '#16834A';
  const bgCard   = isDark ? '#241132' : '#FFFFFF';
  const bgSec    = isDark ? 'rgba(84,36,119,0.15)' : 'rgba(22,131,74,0.08)';
  const border   = isDark ? 'rgba(212,175,55,0.2)' : 'rgba(22,131,74,0.2)';
  const textMain = isDark ? '#FFFFFF' : '#101B14';
  const textSec  = isDark ? '#C9BFD1' : '#53665A';

  return (
    <section id="skills" className="py-16 relative" style={{ borderTop: `1px solid ${accent}15` }}>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        <div className="max-w-2xl mb-12">
          <div className="section-label inline-flex items-center gap-2 mb-2">
            <i className="fa-solid fa-microchip"></i> Capabilities &amp; Stack
          </div>
          <h2 className="section-title text-3xl sm:text-4xl font-extrabold">
            Technical Skills &amp; Tooling
          </h2>
          <p className="text-sm mt-1" style={{ color: textSec }}>
            Core stack spanning programming, modern web frameworks, cybersecurity tooling, embedded IoT electronics, databases, and design systems.
          </p>
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5">
          {SKILLS_DATA.map((cat, idx) => (
            <div
              key={idx}
              className="themed-card rounded-2xl p-5 space-y-4"
              style={{ background: bgCard, border: `1px solid ${border}` }}
            >
              {/* Category header */}
              <div className="flex items-center gap-3">
                <div
                  className="w-10 h-10 rounded-xl flex items-center justify-center text-base"
                  style={{ background: bgSec, color: accent }}
                >
                  <i className={`fa-solid ${cat.icon}`}></i>
                </div>
                <h3
                  className="text-sm font-bold"
                  style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
                >
                  {cat.category}
                </h3>
              </div>

              {/* Skill chips */}
              <div className="flex flex-wrap gap-1.5">
                {cat.skills.map((skill, sIdx) => (
                  <span
                    key={sIdx}
                    className="skill-badge px-2.5 py-1 rounded-md text-xs"
                  >
                    {skill}
                  </span>
                ))}
              </div>
            </div>
          ))}
        </div>

      </div>
    </section>
  );
}
