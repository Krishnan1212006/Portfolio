import React, { useState } from 'react';
import { useTheme } from '../context/ThemeContext';
import { profileData, projectsData } from '../data/portfolioData';
import ProfileImageModal from './ProfileImageModal';

export default function Hero() {
  const { isDark } = useTheme();
  const [showModal, setShowModal] = useState(false);
  const [imgError, setImgError]   = useState(false);

  const accent      = isDark ? '#D4AF37' : '#16834A';
  const accentBright= isDark ? '#F4D477' : '#20A56B';
  const bgCard      = isDark ? '#241132' : '#FFFFFF';
  const bgStat      = isDark ? 'rgba(84,36,119,0.2)' : 'rgba(22,131,74,0.08)';
  const borderCard  = isDark ? 'rgba(212,175,55,0.3)' : 'rgba(22,131,74,0.3)';
  const textMain    = isDark ? '#FFFFFF' : '#101B14';
  const textSec     = isDark ? '#C9BFD1' : '#53665A';

  return (
    <>
      {showModal && <ProfileImageModal onClose={() => setShowModal(false)} />}

      <section id="home" className="relative pt-10 pb-20 lg:pt-16 lg:pb-28 overflow-hidden">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

          {/* ── Availability Badge ── */}
          <div
            className="inline-flex items-center gap-2.5 px-4 py-2 rounded-full text-xs sm:text-sm font-medium mb-8"
            style={{
              background: isDark ? 'rgba(84,36,119,0.3)' : 'rgba(22,131,74,0.1)',
              border: `1px solid ${accent}40`,
              color: accent,
              fontFamily: 'JetBrains Mono, monospace',
            }}
          >
            <span className="w-2 h-2 rounded-full animate-pulse" style={{ background: '#22c55e' }}></span>
            <span>Available for Cybersecurity, IoT &amp; Software Engineering Opportunities</span>
          </div>

          <div className="grid grid-cols-1 lg:grid-cols-12 gap-12 items-center">

            {/* ════ LEFT CONTENT ════ */}
            <div className="lg:col-span-7 space-y-6">

              <h1
                className="text-4xl sm:text-5xl lg:text-6xl font-extrabold tracking-tight leading-tight"
                style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
              >
                Building Intelligent Systems.{' '}
                <br />
                <span
                  style={{
                    background: isDark
                      ? 'linear-gradient(135deg, #D4AF37, #F4D477, #D4AF37)'
                      : 'linear-gradient(135deg, #16834A, #20A56B, #0D3925)',
                    WebkitBackgroundClip: 'text',
                    WebkitTextFillColor: 'transparent',
                    backgroundClip: 'text',
                  }}
                >
                  Securing the Digital Future.
                </span>
              </h1>

              <p className="text-lg text-base leading-relaxed max-w-2xl" style={{ color: textSec }}>
                Hi, I'm{' '}
                <strong style={{ color: textMain, fontWeight: 700 }}>Hari Krishnan.S</strong>
                , a Computer Science and Engineering student (CGPA:{' '}
                <span style={{ color: accent, fontFamily: 'JetBrains Mono, monospace' }}>7.27</span>
                ) at{' '}
                <span style={{ color: accent }}>Akshaya College of Engineering and Technology</span>
                , passionate about Cybersecurity, Embedded IoT and AI-powered technologies. I enjoy
                developing secure systems, intelligent applications and practical technology solutions.
              </p>

              {/* Action Buttons */}
              <div className="flex flex-wrap items-center gap-3 pt-2">
                <a
                  href="#projects"
                  className="btn-primary inline-flex items-center gap-2 px-6 py-3.5 rounded-xl text-sm"
                >
                  <i className="fa-solid fa-diagram-project text-sm"></i>
                  <span>Explore My Projects</span>
                </a>
                <a
                  href="#contact"
                  className="btn-secondary inline-flex items-center gap-2 px-6 py-3.5 rounded-xl text-sm"
                >
                  <i className="fa-solid fa-paper-plane text-sm" style={{ color: accent }}></i>
                  <span>Get in Touch</span>
                </a>
                <a
                  href={profileData.resumePdfUrl}
                  target="_blank"
                  rel="noopener noreferrer"
                  download="HariKrishnan_Resume.pdf"
                  className="btn-secondary inline-flex items-center gap-2 px-6 py-3.5 rounded-xl text-sm"
                >
                  <i className="fa-solid fa-cloud-arrow-down text-sm" style={{ color: accent }}></i>
                  <span>Download Resume</span>
                </a>
              </div>

              {/* Quick Contact Bar */}
              <div
                className="pt-5 flex flex-wrap items-center gap-5 text-sm border-t"
                style={{ borderColor: `${accent}25`, color: textSec }}
              >
                <a
                  href={`mailto:${profileData.email}`}
                  className="flex items-center gap-2 transition-colors hover:no-underline"
                  style={{ color: textSec }}
                  onMouseEnter={e => e.currentTarget.style.color = accent}
                  onMouseLeave={e => e.currentTarget.style.color = textSec}
                >
                  <i className="fa-solid fa-envelope" style={{ color: accent }}></i>
                  <span className="hidden sm:inline">{profileData.email}</span>
                  <span className="sm:hidden">Email</span>
                </a>
                <a
                  href={`tel:${profileData.phone.replace(/\s+/g, '')}`}
                  className="flex items-center gap-2 transition-colors"
                  style={{ color: textSec, fontFamily: 'JetBrains Mono, monospace' }}
                  onMouseEnter={e => e.currentTarget.style.color = accent}
                  onMouseLeave={e => e.currentTarget.style.color = textSec}
                >
                  <i className="fa-solid fa-phone" style={{ color: accent }}></i>
                  <span>{profileData.phone}</span>
                </a>
                <a
                  href={profileData.github}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="flex items-center gap-2 transition-colors"
                  style={{ color: textSec }}
                  onMouseEnter={e => e.currentTarget.style.color = accent}
                  onMouseLeave={e => e.currentTarget.style.color = textSec}
                >
                  <i className="fa-brands fa-github" style={{ color: textMain }}></i>
                  <span className="font-mono">{profileData.githubUsername}</span>
                </a>
                <a
                  href={profileData.linkedin}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="flex items-center gap-2 transition-colors"
                  style={{ color: textSec }}
                  onMouseEnter={e => e.currentTarget.style.color = accent}
                  onMouseLeave={e => e.currentTarget.style.color = textSec}
                >
                  <i className="fa-brands fa-linkedin" style={{ color: accent }}></i>
                  <span>LinkedIn</span>
                </a>
                <span className="flex items-center gap-2" style={{ color: textSec }}>
                  <i className="fa-solid fa-location-dot" style={{ color: isDark ? '#a855f7' : '#16834A' }}></i>
                  <span>{profileData.location}</span>
                </span>
              </div>
            </div>

            {/* ════ RIGHT PROFILE CARD ════ */}
            <div className="lg:col-span-5" id="about">
              <div
                className="themed-card rounded-3xl p-6 sm:p-8 space-y-5 relative overflow-hidden"
                style={{ background: bgCard, border: `1px solid ${borderCard}` }}
              >
                {/* Background glow */}
                <div
                  className="absolute -top-20 -right-20 w-48 h-48 rounded-full blur-3xl pointer-events-none"
                  style={{ background: isDark ? 'rgba(212,175,55,0.08)' : 'rgba(22,131,74,0.08)' }}
                />

                {/* Profile Image + Info */}
                <div className="flex items-center gap-4">
                  {/* Animated circular photo */}
                  <div
                    className="relative shrink-0 cursor-pointer"
                    onClick={() => setShowModal(true)}
                    title="Click to view HD photo"
                    style={{ width: 88, height: 88 }}
                  >
                    {/* Animated border ring */}
                    <div
                      className="absolute inset-0 rounded-full"
                      style={{
                        background: isDark
                          ? 'conic-gradient(from 0deg, #D4AF37, #542477, #F4D477, #542477, #D4AF37)'
                          : 'conic-gradient(from 0deg, #16834A, #20A56B, #0D3925, #20A56B, #16834A)',
                        animation: 'rotateBorder 3s linear infinite',
                        padding: '3px',
                        borderRadius: '50%',
                      }}
                    />
                    <div
                      className="absolute inset-[3px] rounded-full overflow-hidden"
                      style={{ background: bgCard }}
                    >
                      {!imgError ? (
                        <img
                          src="./images/hari-profile.jpg"
                          alt="Hari Krishnan.S"
                          className="w-full h-full object-cover object-[center_12%] hover:scale-105 transition-transform duration-300"
                          onError={() => {
                            const fallback = './assets/images/avatar.jpg';
                            const img = new Image();
                            img.onload = () => {
                              const el = document.getElementById('hero-profile-img');
                              if (el) el.src = fallback;
                            };
                            img.onerror = () => setImgError(true);
                            img.src = fallback;
                          }}
                          id="hero-profile-img"
                        />
                      ) : (
                        <div
                          className="w-full h-full flex items-center justify-center text-2xl font-bold"
                          style={{ color: accent, background: bgStat }}
                        >
                          HK
                        </div>
                      )}
                    </div>
                  </div>

                  {/* Name + details */}
                  <div className="flex-1 min-w-0">
                    <h3
                      className="text-xl font-bold tracking-wide"
                      style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
                    >
                      Hari Krishnan.S
                    </h3>
                    <p className="text-xs font-mono mt-0.5" style={{ color: accent }}>
                      Cybersecurity &amp; Embedded IoT
                    </p>
                    <p className="text-xs mt-0.5" style={{ color: textSec }}>
                      B.E. CSE • 7.27 CGPA • 2023–2027
                    </p>
                  </div>

                  {/* Verified badge */}
                  <span
                    className="shrink-0 px-2.5 py-1 rounded-full text-xs font-semibold"
                    style={{
                      background: isDark ? 'rgba(34,197,94,0.1)' : 'rgba(22,131,74,0.1)',
                      color: '#22c55e',
                      border: '1px solid rgba(34,197,94,0.3)',
                    }}
                  >
                    Verified
                  </span>
                </div>

                {/* Stats Grid */}
                <div className="grid grid-cols-2 gap-3">
                  {[
                    { val: `${projectsData.length}`, label: 'Featured Projects', sub: 'AI, IoT, Security, UI' },
                    { val: '4× 1st Prize', label: 'Competition Awards', sub: 'Dream 2 Reality & Tech Day' },
                    { val: '3× Intern', label: 'Hackup Technology', sub: 'Ethical Hacking & IoT' },
                    { val: 'NIST 800-88', label: 'Compliant Tooling', sub: 'Cryptographic Wiping' },
                  ].map((stat, i) => (
                    <div
                      key={i}
                      className="p-3.5 rounded-xl"
                      style={{ background: bgStat, border: `1px solid ${borderCard}` }}
                    >
                      <div
                        className="text-lg font-black"
                        style={{ color: accent, fontFamily: 'Space Grotesk, sans-serif' }}
                      >
                        {stat.val}
                      </div>
                      <div className="text-xs font-medium mt-0.5" style={{ color: textMain }}>
                        {stat.label}
                      </div>
                      <div className="text-[11px] mt-0.5" style={{ color: textSec }}>
                        {stat.sub}
                      </div>
                    </div>
                  ))}
                </div>

                {/* VIEW HD PHOTO button */}
                <button
                  onClick={() => setShowModal(true)}
                  className="w-full py-2.5 rounded-xl text-xs font-mono font-semibold tracking-widest transition-all"
                  style={{
                    background: isDark ? 'rgba(212,175,55,0.08)' : 'rgba(22,131,74,0.08)',
                    color: accent,
                    border: `1px solid ${accent}40`,
                  }}
                  onMouseEnter={e => { e.currentTarget.style.background = accent; e.currentTarget.style.color = isDark ? '#100718' : '#FFFFFF'; }}
                  onMouseLeave={e => { e.currentTarget.style.background = isDark ? 'rgba(212,175,55,0.08)' : 'rgba(22,131,74,0.08)'; e.currentTarget.style.color = accent; }}
                >
                  <i className="fa-solid fa-image mr-2"></i>
                  VIEW HD PHOTO
                </button>

                {/* Bio quote */}
                <div
                  className="p-3.5 rounded-xl text-xs sm:text-sm leading-relaxed italic"
                  style={{
                    background: isDark ? 'rgba(16,7,24,0.6)' : '#F5FAF6',
                    borderLeft: `3px solid ${accent}`,
                    color: textSec,
                  }}
                >
                  "{profileData.objective}"
                </div>
              </div>
            </div>

          </div>
        </div>
      </section>
    </>
  );
}
