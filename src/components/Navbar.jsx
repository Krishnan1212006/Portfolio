import React, { useState, useEffect } from 'react';
import { useTheme } from '../context/ThemeContext';
import { profileData } from '../data/portfolioData';

const NAV_LINKS = [
  { label: 'About',              href: '#about'          },
  { label: 'Projects',           href: '#projects'       },
  { label: 'Experience',         href: '#experience'     },
  { label: 'Skills',             href: '#skills'         },
  { label: 'Certifications',     href: '#certifications' },
  { label: 'Academics & Awards', href: '#awards'         },
  { label: 'Contact',            href: '#contact'        },
];

export default function Navbar() {
  const { isDark, toggleTheme } = useTheme();
  const [mobileOpen, setMobileOpen]   = useState(false);
  const [scrolled, setScrolled]       = useState(false);
  const [activeSection, setActiveSection] = useState('');
  const [imgError, setImgError]       = useState(false);

  // Scroll shrink effect
  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 20);
    window.addEventListener('scroll', onScroll, { passive: true });
    return () => window.removeEventListener('scroll', onScroll);
  }, []);

  // Active section scroll spy
  useEffect(() => {
    const ids = ['about', 'projects', 'experience', 'skills', 'certifications', 'awards', 'contact'];
    const observer = new IntersectionObserver(
      entries => {
        entries.forEach(entry => {
          if (entry.isIntersecting) setActiveSection(entry.target.id);
        });
      },
      { rootMargin: '-40% 0px -55% 0px' }
    );
    ids.forEach(id => {
      const el = document.getElementById(id);
      if (el) observer.observe(el);
    });
    return () => observer.disconnect();
  }, []);

  const accent      = isDark ? '#D4AF37' : '#16834A';
  const bgCard      = isDark ? '#1B0B29' : '#FFFFFF';
  const textMain    = isDark ? '#FFFFFF' : '#101B14';
  const textSec     = isDark ? '#C9BFD1' : '#53665A';

  const themeLabel = isDark ? '☾ DARK | GOLD' : '☀ LIGHT | GREEN';

  return (
    <header
      className="sticky top-0 z-50 glass-nav transition-all duration-300"
      style={{ boxShadow: scrolled ? `0 4px 24px rgba(0,0,0,0.3)` : 'none' }}
    >
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between" style={{ height: scrolled ? '64px' : '76px', transition: 'height 0.3s ease' }}>

          {/* ── Brand ── */}
          <a href="#home" className="flex items-center gap-3 group shrink-0">
            <div
              className="relative shrink-0"
              style={{ width: 40, height: 40 }}
            >
              <div
                className="w-10 h-10 rounded-full overflow-hidden"
                style={{ border: `2px solid ${accent}`, transition: 'border-color 0.4s ease' }}
              >
                {!imgError ? (
                  <img
                    src="./images/hari-profile.jpg"
                    alt="Hari Krishnan.S"
                    className="w-full h-full object-cover object-[center_12%]"
                    onError={() => {
                      // Try fallback to assets avatar if images folder had relative issue
                      const fallback = './assets/images/avatar.jpg';
                      const img = new Image();
                      img.onload = () => {
                        const el = document.getElementById('navbar-avatar-img');
                        if (el) el.src = fallback;
                      };
                      img.onerror = () => setImgError(true);
                      img.src = fallback;
                    }}
                    id="navbar-avatar-img"
                  />
                ) : (
                  <div className="w-full h-full flex items-center justify-center font-bold text-sm"
                    style={{ background: isDark ? '#241132' : '#E7F3E9', color: accent }}>
                    HK
                  </div>
                )}
              </div>
            </div>
            <div className="flex flex-col">
              <span
                className="font-heading font-bold text-xs sm:text-sm tracking-wider sm:tracking-widest transition-colors leading-tight"
                style={{ color: textMain, fontFamily: 'Space Grotesk, sans-serif' }}
              >
                HARI KRISHNAN.S
              </span>
              <span className="text-[10px] sm:text-xs font-mono leading-tight mt-0.5" style={{ color: textSec }}>
                CYBERSECURITY &amp; EMBEDDED IoT
              </span>
            </div>
          </a>

          {/* ── Desktop Nav Links ── */}
          <nav className="hidden xl:flex items-center gap-0.5 text-xs font-medium" style={{ color: textSec }}>
            {NAV_LINKS.map(link => {
              const sectionId = link.href.replace('#', '');
              const isActive  = activeSection === sectionId;
              return (
                <a
                  key={link.href}
                  href={link.href}
                  className="relative px-2.5 py-1.5 rounded-lg transition-colors nav-link whitespace-nowrap"
                  style={{
                    color: isActive ? accent : textSec,
                    fontFamily: 'Inter, sans-serif',
                  }}
                  onMouseEnter={e => e.currentTarget.style.color = accent}
                  onMouseLeave={e => e.currentTarget.style.color = isActive ? accent : textSec}
                >
                  {link.label}
                  {isActive && (
                    <span
                      className="absolute bottom-0 left-1/2 -translate-x-1/2 h-0.5 w-4 rounded-full"
                      style={{ background: accent }}
                    />
                  )}
                </a>
              );
            })}
          </nav>

          {/* ── Right CTAs ── */}
          <div className="hidden sm:flex items-center gap-2">
            {/* Theme Toggle Button */}
            <button
              onClick={toggleTheme}
              aria-label={`Toggle theme: currently ${isDark ? 'Dark Gold' : 'Light Green'}`}
              className="flex items-center gap-1.5 px-3 py-2 rounded-lg text-xs font-mono font-bold tracking-wider transition-all"
              style={{
                background: isDark ? 'rgba(212,175,55,0.15)' : 'rgba(22,131,74,0.12)',
                color: accent,
                border: `1px solid ${accent}50`,
                boxShadow: `0 0 12px ${accent}20`
              }}
            >
              {isDark ? (
                <>
                  <i className="fa-solid fa-moon text-xs"></i>
                  <span>DARK | GOLD</span>
                </>
              ) : (
                <>
                  <i className="fa-solid fa-sun text-xs"></i>
                  <span>LIGHT | GREEN</span>
                </>
              )}
            </button>

            {/* Resume Button */}
            <a
              href={profileData.resumePdfUrl}
              target="_blank"
              rel="noopener noreferrer"
              download="HariKrishnan_Resume.pdf"
              className="inline-flex items-center gap-1.5 px-3 py-2 rounded-lg text-xs font-semibold transition-all"
              style={{
                background: isDark ? '#241132' : '#FFFFFF',
                color: textSec,
                border: `1px solid ${isDark ? 'rgba(212,175,55,0.2)' : 'rgba(22,131,74,0.2)'}`,
              }}
            >
              <i className="fa-solid fa-file-pdf" style={{ color: accent }}></i>
              <span>Resume</span>
            </a>

            {/* Get in Touch */}
            <a
              href="#contact"
              className="btn-primary inline-flex items-center gap-1.5 px-4 py-2 rounded-lg text-sm"
            >
              <span>Get in Touch</span>
              <i className="fa-solid fa-arrow-right text-xs"></i>
            </a>
          </div>

          {/* ── Mobile Actions (Theme Toggle + Hamburger) ── */}
          <div className="flex xl:hidden items-center gap-2">
            <button
              onClick={toggleTheme}
              aria-label="Toggle theme"
              className="w-9 h-9 rounded-xl flex items-center justify-center transition-all"
              style={{
                background: isDark ? 'rgba(212,175,55,0.15)' : 'rgba(22,131,74,0.12)',
                color: accent,
                border: `1px solid ${accent}40`,
              }}
            >
              <i className={`fa-solid ${isDark ? 'fa-moon' : 'fa-sun'} text-sm`}></i>
            </button>
            <button
              className="w-9 h-9 rounded-xl flex items-center justify-center transition"
              onClick={() => setMobileOpen(v => !v)}
              aria-label="Toggle navigation"
              style={{
                color: textSec,
                background: isDark ? 'rgba(255,255,255,0.05)' : 'rgba(0,0,0,0.05)',
                border: `1px solid ${isDark ? 'rgba(255,255,255,0.1)' : 'rgba(0,0,0,0.1)'}`
              }}
            >
              <i className={`fa-solid ${mobileOpen ? 'fa-xmark' : 'fa-bars'} text-lg`}></i>
            </button>
          </div>
        </div>
      </div>

      {/* ── Mobile Drawer ── */}
      {mobileOpen && (
        <div
          className="mobile-nav-drawer xl:hidden px-4 pt-3 pb-6 space-y-1 shadow-2xl animate-in slide-in-from-top-2 duration-200"
          style={{ maxHeight: 'calc(100vh - 76px)', overflowY: 'auto' }}
        >
          {NAV_LINKS.map(link => {
            const sectionId = link.href.replace('#', '');
            const isActive  = activeSection === sectionId;
            return (
              <a
                key={link.href}
                href={link.href}
                onClick={() => setMobileOpen(false)}
                className="flex items-center justify-between px-4 py-2.5 rounded-xl text-sm font-medium transition-colors"
                style={{
                  color: isActive ? accent : textSec,
                  background: isActive ? (isDark ? 'rgba(212,175,55,0.08)' : 'rgba(22,131,74,0.08)') : 'transparent',
                  fontFamily: 'Inter, sans-serif'
                }}
              >
                <span>{link.label}</span>
                {isActive && <span className="w-1.5 h-1.5 rounded-full" style={{ background: accent }}></span>}
              </a>
            );
          })}
          <div className="pt-3 flex flex-col gap-2">
            <button
              onClick={() => { toggleTheme(); setMobileOpen(false); }}
              className="w-full py-2.5 rounded-xl text-sm font-mono font-bold flex items-center justify-center gap-2 transition"
              style={{
                background: isDark ? 'rgba(212,175,55,0.15)' : 'rgba(22,131,74,0.12)',
                color: accent,
                border: `1px solid ${accent}50`,
              }}
            >
              {isDark ? (
                <>
                  <i className="fa-solid fa-moon"></i>
                  <span>DARK | GOLD</span>
                </>
              ) : (
                <>
                  <i className="fa-solid fa-sun"></i>
                  <span>LIGHT | GREEN</span>
                </>
              )}
            </button>
            <a
              href={profileData.resumePdfUrl}
              target="_blank"
              rel="noopener noreferrer"
              download="HariKrishnan_Resume.pdf"
              onClick={() => setMobileOpen(false)}
              className="w-full text-center py-2.5 rounded-xl text-sm font-semibold transition"
              style={{ background: isDark ? '#241132' : '#F5FAF6', color: textSec, border: `1px solid ${accent}30` }}
            >
              <i className="fa-solid fa-file-pdf mr-2" style={{ color: accent }}></i>
              Download Resume (PDF)
            </a>
            <a
              href="#contact"
              onClick={() => setMobileOpen(false)}
              className="btn-primary w-full text-center py-2.5 rounded-xl text-sm font-bold shadow-md"
            >
              Get in Touch
            </a>
          </div>
        </div>
      )}
    </header>
  );
}
